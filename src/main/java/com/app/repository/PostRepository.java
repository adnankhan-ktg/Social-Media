package com.app.repository;

import com.app.model.entity.Post;
import com.app.model.interfacedto.PostDtoInterface;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findById(int id);

    @Query(value =
            "WITH LatestUserPosts AS (" +
                    "SELECT p.user_id, p.id AS post_id, p.content_type, p.caption, p.post_url, p.timestamp " +
                    "FROM post AS p " +
                    "WHERE p.user_id IN (SELECT followed_user_id FROM user_followings WHERE follower_user_id = :userId AND status = 'APPROVED') " +
                    "AND (p.id NOT IN (SELECT post_id FROM interaction_log WHERE user_id = :userId) OR p.id IS NULL) " +
                    "ORDER BY p.timestamp DESC " +
                    "LIMIT :limit), " +
                    "LatestBusinessPost AS (" +
                    "SELECT p.id AS post_id, p.content_type, p.caption, p.post_url, p.timestamp, bu.business_name AS business_name " +
                    "FROM post AS p " +
                    "INNER JOIN business_user bu ON bu.id = p.business_user_id " +
                    "WHERE p.user_type = 'BUSINESS' " +
                    "AND (p.id NOT IN (SELECT post_id FROM interaction_log WHERE user_id = :userId) OR p.id IS NULL) " +
                    "ORDER BY p.timestamp DESC " +
                    "LIMIT 1) " +
                    "SELECT CONCAT(f.first_name, ' ', f.last_name) AS following, lp.post_id AS postId, lp.content_type AS contentType, " +
                    "lp.caption AS caption, lp.post_url AS postUrl, lp.timestamp AS timestamp " +
                    "FROM user AS u " +
                    "INNER JOIN user_followings AS uf ON u.id = uf.follower_user_id " +
                    "INNER JOIN user AS f ON uf.followed_user_id = f.id " +
                    "LEFT JOIN LatestUserPosts AS lp ON f.id = lp.user_id " +
                    "WHERE lp.post_id IS NOT NULL " +
                    "UNION ALL " +
                    "SELECT lb.business_name AS following, lb.post_id AS postId, lb.content_type AS contentType, lb.caption AS caption, " +
                    "lb.post_url AS postUrl, lb.timestamp AS timestamp " +
                    "FROM LatestBusinessPost AS lb",
            nativeQuery = true)
    List<PostDtoInterface> loadLatestPost(@Param("userId") int userId, @Param("limit") int limit);


}
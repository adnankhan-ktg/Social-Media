package com.app.repository;

import com.app.model.entity.Post;
import com.app.model.interfacedto.PostDtoInterface;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Optional<Post> findById(int id);

    @Query(value = "WITH LatestPosts AS ("
            + "    SELECT"
            + "        p.user_id,"
            + "        p.id AS post_id,"
            + "        p.content_type,"
            + "        p.caption,"
            + "        p.post_url,"
            + "        p.timestamp"
            + "    FROM"
            + "        post AS p"
            + "    WHERE"
            + "        p.user_id IN (SELECT followed_user_id FROM user_followings WHERE follower_user_id = :userId)"
            + "    ORDER BY"
            + "        p.timestamp DESC)"
            + " SELECT"
            + "    concat(f.first_name, ' ', f.last_name) AS following,"
            + "    lp.post_id AS postId,"
            + "    lp.content_type AS contentType,"
            + "    lp.caption AS caption,"
            + "    lp.post_url AS postUrl,"
            + "    lp.timestamp AS timestamp"
            + " FROM"
            + "    user AS u"
            + " INNER JOIN"
            + "    user_followings AS uf"
            + "    ON u.id = uf.follower_user_id"
            + " INNER JOIN"
            + "    user AS f"
            + "    ON uf.followed_user_id = f.id"
            + " LEFT JOIN ("
            + "    SELECT"
            + "        user_id,"
            + "        post_id,"
            + "        content_type,"
            + "        caption,"
            + "        post_url,"
            + "        timestamp"
            + "    FROM LatestPosts"
            + "    LIMIT :limit"
            + ") AS lp "
            + "ON f.id = lp.user_id"
            + " LEFT JOIN"
            + "    interaction_log AS il"
            + "    ON lp.post_id = il.post_id AND il.user_id = :userId"
            + " WHERE"
            + "    (il.id IS NULL OR lp.post_id IS NOT NULL) AND"
            + "    (lp.post_id IS NOT NULL)"
            + " ORDER BY lp.timestamp DESC", nativeQuery = true)
    List<PostDtoInterface> loadLatestPost(int userId, int limit);
}
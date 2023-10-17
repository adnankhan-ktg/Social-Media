package com.app.repository;

import com.app.model.entity.Post;
import com.app.model.interfacedto.PostDtoInterface;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
            + "    AND ("
            + "        p.id NOT IN (SELECT post_id FROM interaction_log WHERE user_id = 2)"
            + "        OR p.id IS NULL)"
            + "    ORDER BY"
            + "        p.timestamp DESC"
            + "    LIMIT :limit)"
            + " SELECT"
            + "    CONCAT(f.first_name, ' ', f.last_name) AS following,"
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
            + " LEFT JOIN LatestPosts AS lp"
            + " ON f.id = lp.user_id"
            + " WHERE"
            + "    lp.post_id IS NOT NULL"
            + " ORDER BY lp.timestamp DESC", nativeQuery = true)
    List<PostDtoInterface> loadLatestPost(int userId, int limit);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO interaction_log(interaction_type,timestamp,post_id,user_id) "
            + "values(:interactionType, NOW(), :postId, :userId)", nativeQuery = true)
    void interactionLog(int userId, int postId, String interactionType);
}
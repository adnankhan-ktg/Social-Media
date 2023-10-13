package com.app.repository;


import com.app.model.entity.LikedPost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedPostRepository extends JpaRepository<LikedPost, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM liked_post WHERE post_id = :postId AND user_id = :userId", nativeQuery = true)
     void unlikePost(int postId, int userId);


    LikedPost findByPostIdAndUserId(int postId, int userId);
}

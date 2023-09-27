package com.app.repository;
import com.app.model.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "UPDATE posts SET post_url = :postUrl where postId = :postId",nativeQuery = true)
    @Modifying
    @Transactional
    public void updatePostUrl(String postUrl, int postId);
}
package com.app.repository;

import com.app.model.entity.Post;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Query(value = "UPDATE posts SET post_url = :postUrl where post_id = :postId", nativeQuery = true)
	@Modifying
	@Transactional
	public void updatePostUrl(String postUrl, int postId);

	List<Post> findByPostIdNotIn(List<Long> postIds);

	@Query(value = "SELECT * FROM posts WHERE post_id NOT IN :excludeIds ORDER BY RAND() LIMIT :limit", nativeQuery = true)
	List<Post> findRandomPostIds(int limit, List<Long> excludeIds);
	
	@Query(value = "SELECT * FROM posts ORDER BY RAND() LIMIT :limit", nativeQuery = true)
	List<Post> findRandomPostIds(int limit);
}
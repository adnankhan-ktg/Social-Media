package com.app.repository;

import com.app.model.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment,Integer> {

   List<PostComment> findByPostId(int id);
}

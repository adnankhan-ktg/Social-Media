package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.entity.UserInteraction;

@Repository
public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {

	List<UserInteraction> findByUserUserId(Long userId);

	List<UserInteraction> findByUserUserIdAndPostCategoryId(int userId, int id);

}
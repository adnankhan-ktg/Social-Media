package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.entity.UserInteraction;

@Repository
public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {
	 
	 List<UserInteraction> findByUserUserIdAndInteractionType(Long userId, String interactionType);
}
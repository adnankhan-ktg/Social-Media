package com.app.repository;

import com.app.model.entity.InteractionLog;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterestRepository extends JpaRepository<InteractionLog, Long> {
    
	public Optional<InteractionLog> findByUserUserId(int id);
}
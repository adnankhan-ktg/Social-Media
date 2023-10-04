package com.app.repository;

import com.app.model.entity.UserInterest;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
    
	public Optional<UserInterest> findByUserUserId(int id);
}
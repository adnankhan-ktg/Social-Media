package com.app.repository;

import java.util.List;

import com.app.model.entity.InteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInteractionRepository extends JpaRepository<InteractionLog, Long> {

	List<InteractionLog> findByUserId(int userId);
}
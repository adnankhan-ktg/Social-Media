package com.app.repository;

import com.app.model.entity.UserInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {
    // Custom queries if needed
}
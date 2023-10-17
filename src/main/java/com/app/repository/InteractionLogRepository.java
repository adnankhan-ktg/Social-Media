package com.app.repository;

import java.util.List;
import java.util.Optional;

import com.app.model.entity.InteractionLog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionLogRepository extends JpaRepository<InteractionLog, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO interaction_log(interaction_type,timestamp,post_id,user_id) "
            + "values(:interactionType, NOW(), :postId, :userId)", nativeQuery = true)
    void interactionLog(int userId, int postId, String interactionType);


    Optional<InteractionLog> findByUserIdAndPostId(int userId, int postId);
}
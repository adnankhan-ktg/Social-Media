package com.app.repository;

import com.app.model.entity.UserFollowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowingRepository extends JpaRepository<UserFollowing, Long> {
    // Custom queries if needed
}

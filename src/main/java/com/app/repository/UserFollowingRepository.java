package com.app.repository;

import com.app.model.entity.UserFollowings;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowingRepository extends JpaRepository<UserFollowings, Integer> {

    @Query(value = "SELECT COUNT(*) FROM user_following WHERE follower_user_id = :followerId "
            + " AND followed_user_id = :followedId", nativeQuery = true)
    long checkRelationship(int followedId, int followerId);

    @Query(value = "SELECT * FROM user_following WHERE followed_user_id = :userId AND status = 'PENDING'", nativeQuery = true)
    List<UserFollowings> getPendingFriendRequests(int userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_following SET status = :status WHERE id = :id", nativeQuery = true)
    void respondToFriendRequest(int id, String status);
}

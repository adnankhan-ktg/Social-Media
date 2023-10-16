package com.app.repository;

import com.app.model.dto.FollowersDto;
import com.app.model.entity.UserFollowings;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "SELECT new com.app.model.dto.FollowersDto(u.id, u.firstName, u.lastName, u.email)"
            + " FROM User u INNER JOIN UserFollowings uf ON"
            + " u.id = uf.followerUser.id WHERE uf.followedUser.id = :userId AND uf.status = 'APPROVED'")
    List<FollowersDto> getFollowersForUser(@Param("userId") int userId);

    @Query(value = "SELECT new com.app.model.dto.FollowersDto(u.id, u.firstName, u.lastName, u.email)"
            + " FROM User u INNER JOIN UserFollowings uf ON"
            + " u.id = uf.followedUser.id WHERE uf.followerUser.id = :userId AND status = 'APPROVED'")
    List<FollowersDto> getFollowingsForUser(@Param("userId") int userId);
}

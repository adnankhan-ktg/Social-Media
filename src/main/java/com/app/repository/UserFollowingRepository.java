package com.app.repository;

import com.app.model.entity.Follows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowingRepository extends JpaRepository<Follows, Integer> {

    @Query(value = "SELECT COUNT(*) FROM follows WHERE followed_user_id = :followerId "
            +" AND following_user_id = :followingId",nativeQuery = true)
    long checkRelationship(int followerId, int followingId);
}

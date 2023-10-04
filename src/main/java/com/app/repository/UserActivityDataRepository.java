package com.app.repository;

import com.app.model.entity.UserActivityData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityDataRepository extends JpaRepository<UserActivityData,Integer> {

		@Query(value = "SELECT u.user_id, uad.category_id " +
	            "FROM user_activity_data uad " +
	            "JOIN ( " +
	            "    SELECT user_id, category_id, COUNT(*) AS category_count " +
	            "    FROM user_activity_data " +
	            "    GROUP BY user_id, category_id " +
	            "    ORDER BY user_id, category_count DESC " +
	            ") AS u " +
	            "ON uad.user_id = u.user_id " +
	            "GROUP BY u.user_id", nativeQuery = true)
		List<Object[]> findMostFrequentCategoryForUsers();
}

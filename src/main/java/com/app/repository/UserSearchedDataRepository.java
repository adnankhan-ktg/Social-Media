package com.app.repository;

import com.app.model.entity.UserSearchedData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSearchedDataRepository extends JpaRepository<UserSearchedData,Integer> {

		@Query(value = "SELECT u.user_id, usd.category_id " +
	            "FROM user_searched_data usd " +
	            "JOIN ( " +
	            "    SELECT user_id, category_id, COUNT(*) AS category_count " +
	            "    FROM user_searched_data " +
	            "    GROUP BY user_id, category_id " +
	            "    ORDER BY user_id, category_count DESC " +
	            ") AS u " +
	            "ON usd.user_id = u.user_id " +
	            "GROUP BY u.user_id", nativeQuery = true)
		List<Object[]> findMostFrequentCategoryForUsers();
}

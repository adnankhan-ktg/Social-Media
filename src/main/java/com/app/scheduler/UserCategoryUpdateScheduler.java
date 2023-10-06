package com.app.scheduler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.model.entity.CategoryMst;
import com.app.model.entity.User;
import com.app.model.entity.InteractionLog;
import com.app.repository.PostCategoryMstRepository;
import com.app.repository.UserInterestRepository;
import com.app.repository.UserRepository;
import com.app.repository.UserSearchedDataRepository;

@Component
public class UserCategoryUpdateScheduler {

	@Autowired
	private UserSearchedDataRepository userSearchedDataRepository;

	@Autowired
	private UserInterestRepository userInterestRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostCategoryMstRepository postCategoryMstRepository;

	// Scheduled method that runs every day at 4 AM
	@Scheduled(cron = "0 0 4 * * ?")
    public void updateInterestedCategories() {
        List<Object[]> userCategoryCounts = userSearchedDataRepository.findMostFrequentCategoryForUsers();
        
        for (Object[] result : userCategoryCounts) {
            Long userId = (Long) result[0];
            Long categoryId = (Long) result[1];
            
            Optional<User> userObj = userRepository.findById(userId);
            Optional<CategoryMst> categoryObj = postCategoryMstRepository.findById(categoryId.intValue());
            
            // Check if the user already has an entry in user_interested_category
            Optional<InteractionLog> existingInterest = userInterestRepository.findByUserUserId(userId.intValue());
            
            if (existingInterest.isPresent()) {
                // Update the existing entry
            	
            	InteractionLog existingInterestObj = existingInterest.get();
            	
            	existingInterestObj.setCategory(categoryObj.get());
                userInterestRepository.save(existingInterestObj);
            } else {
                // Create a new entry
            	InteractionLog newInterest = new InteractionLog();
                newInterest.setUser(userObj.get());
                newInterest.setCategory(categoryObj.get());
                userInterestRepository.save(newInterest);
            }
        }
    }
}

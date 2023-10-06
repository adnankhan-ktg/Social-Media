//package com.app.serviceimpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.app.model.entity.UserActivityData;
//import com.app.repository.PostRepository;
//import com.app.repository.UserActivityDataRepository;
//import com.app.repository.UserInteractionRepository;
//import com.app.service.UserInteractionService;
//
//@Service
//public class InteractionServiceImpl implements UserInteractionService {
//
//	@Autowired
//	private UserInteractionRepository interactionRepository;
//
//	@Autowired
//	private UserActivityDataRepository userActivityDataRepository;
//
//	@Autowired
//	private PostRepository postRepository;
//
////	@Override
////	public UserInteraction likePost(UserInteraction interaction) {
////		UserInteraction userInteraction = interactionRepository.save(interaction);
////
////		if (userInteraction != null) {
////
////			UserActivityData userActivityData = new UserActivityData();
////			userActivityData.setActivityType("like");
////			userActivityData.setUser(userInteraction.getUser());
////			userActivityData.setCategory(
////					postRepository.findById(userInteraction.getPost().getPostId()).get().getCategory());
////
////			userActivityDataRepository.save(userActivityData);
////		}
////
////		return userInteraction;
////	}
//}

package com.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.entity.Post;
import com.app.model.entity.PostCategoryMst;
import com.app.model.entity.User;
import com.app.model.entity.UserInteraction;
import com.app.model.entity.UserInterest;
import com.app.model.entity.UserActivityData;
import com.app.repository.PostCategoryMstRepository;
import com.app.repository.PostRepository;
import com.app.repository.UserInteractionRepository;
import com.app.repository.UserInterestRepository;
import com.app.repository.UserRepository;
import com.app.repository.UserActivityDataRepository;
import com.app.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserInteractionRepository userInteractionRepository;

	@Autowired
	private UserActivityDataRepository dataRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInterestRepository userInterestRepository;

	@Autowired
	private PostCategoryMstRepository categoryMst;

	@Override
	public List<Post> getFeedUpdate(Long userId) {
		List<UserInteraction> likedInteractions = userInteractionRepository.findByUserUserId(userId);

		// Extract post IDs from liked interactions
		List<Long> excludeIds = likedInteractions.stream().map(interaction -> (long) interaction.getPost().getPostId())
				.collect(Collectors.toList());

		List<Post> randomPosts = new ArrayList<>();

		// Fetching user interested post category

		Optional<UserInterest> userInterest = userInterestRepository.findByUserUserId(userId.intValue());

		if (userInterest.isPresent()) {

			UserInterest interest = userInterest.get();
			if (excludeIds.isEmpty())
				randomPosts = postRepository.findRandomPostIds(2, interest.getCategory().getId());
			else
				randomPosts = postRepository.findRandomPostIds(2, excludeIds, interest.getCategory().getId());

		} else {

			if (excludeIds.isEmpty())
				randomPosts = postRepository.findRandomPostIds(2);
			else
				randomPosts = postRepository.findRandomPostIds(2, excludeIds);
		}

		List<UserInteraction> userInteractionsList = randomPosts.stream()
				.map(random -> new UserInteraction(random.getUser(), random, "fetched")).collect(Collectors.toList());

		this.userInteractionRepository.saveAll(userInteractionsList);

		return randomPosts;
	}

	@Override
	public List<Post> getSearchedFeedUpdate(int userId, String search) {
		UserActivityData userSearchedData = new UserActivityData();
		User user = this.userRepository.findByUserId(userId);
		PostCategoryMst postCategoryMst = this.categoryMst.findByName(search);
		userSearchedData.setUser(user);
		userSearchedData.setCategory(postCategoryMst);
		this.dataRepository.save(userSearchedData);

		List<UserInteraction> likedInteractions = userInteractionRepository.findByUserUserIdAndPostCategoryId(userId,
				postCategoryMst.getId());

		// Extract post IDs from liked interactions
		List<Long> excludeIds = likedInteractions.stream().map(interaction -> (long) interaction.getPost().getPostId())
				.collect(Collectors.toList());

		List<Post> randomPosts;

		if (excludeIds.isEmpty())
			randomPosts = postRepository.findRandomPostIds(2, postCategoryMst.getId());
		else
			randomPosts = postRepository.findRandomPostIds(2, excludeIds, postCategoryMst.getId());

		List<UserInteraction> userInteractionsList = randomPosts.stream()
				.map(random -> new UserInteraction(random.getUser(), random, "fetched")).collect(Collectors.toList());

		this.userInteractionRepository.saveAll(userInteractionsList);

		return randomPosts;
	}
}

package com.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.entity.Post;
import com.app.model.entity.UserInteraction;
import com.app.repository.PostRepository;
import com.app.repository.UserInteractionRepository;
import com.app.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserInteractionRepository userInteractionRepository;

	@Override
	public List<Post> getFeedUpdate(Long userId) {

		List<UserInteraction> likedInteractions = userInteractionRepository.findByUserIdAndInteractionType(userId,
				"like");

		List<Post> likedPosts = new ArrayList<>();
		for (UserInteraction interaction : likedInteractions) {
			likedPosts.add(interaction.getPost());
		}
		
		List<Long> excludeIds = new ArrayList<>();
		
		for(Post post : likedPosts) {
			excludeIds.add((long) post.getPostId());
		}
		
		List<Post> randomPost = postRepository.findRandomPostIds(10, excludeIds);
		
		return randomPost;
	}
}

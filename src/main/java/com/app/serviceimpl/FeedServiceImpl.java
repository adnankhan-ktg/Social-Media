package com.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.app.model.entity.InteractionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.model.entity.Post;
import com.app.repository.PostCategoryMstRepository;
import com.app.repository.PostRepository;
import com.app.repository.UserInteractionRepository;
import com.app.repository.UserInterestRepository;
import com.app.repository.UserRepository;
import com.app.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserInteractionRepository userInteractionRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInterestRepository userInterestRepository;

    @Autowired
    private PostCategoryMstRepository categoryMst;

    @Override
    public List<Post> getFeedUpdate(Long userId) {
        List<InteractionLog> likedInteractions = userInteractionRepository.findByUserUserId(userId);

        // Extract post IDs from liked interactions
        List<Long> excludeIds = likedInteractions.stream().map(interaction -> (long) interaction.getPost().getPostId())
                .collect(Collectors.toList());

        List<Post> randomPosts = new ArrayList<>();

        // Fetching user interested post category

        Optional<InteractionLog> interactionLog = userInterestRepository.findByUserUserId(userId.intValue());

        if (interactionLog.isPresent()) {

            InteractionLog interest = interactionLog.get();
            if (excludeIds.isEmpty())
                randomPosts = postRepository.findRandomPostIds(2, interest.getCategory().getId());
            else
                randomPosts = postRepository.findRandomPostIds(2, excludeIds, interest.getCategory().getId());
        }
        return randomPosts;
    }
}

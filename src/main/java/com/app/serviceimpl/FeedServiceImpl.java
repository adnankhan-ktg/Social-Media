package com.app.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.app.model.entity.*;
import com.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserInteractionRepository userInteractionRepository;

    @Autowired
    private UserSearchedDataRepository dataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostCategoryMstRepository categoryMst;

    @Override
    public List<Post> getFeedUpdate(Long userId) {

        List<UserInteraction> likedInteractions = userInteractionRepository.findByUserUserIdAndInteractionType(userId,
                "like");

        List<Post> likedPosts = new ArrayList<>();
        for (UserInteraction interaction : likedInteractions) {
            likedPosts.add(interaction.getPost());
        }

        List<Long> excludeIds = new ArrayList<>();

        for (Post post : likedPosts) {
            excludeIds.add((long) post.getPostId());
        }

        List<Post> randomPost = new ArrayList<>();

        if (excludeIds.isEmpty())
            randomPost = postRepository.findRandomPostIds(2);
        else
            randomPost = postRepository.findRandomPostIds(2, excludeIds);

        List<UserInteraction> userInteractionsList = new ArrayList<>();


        for (Post random : randomPost) {
            userInteractionsList.add(new UserInteraction(random.getUser(), random, "fetched"));
        }

        this.userInteractionRepository.saveAll(userInteractionsList);


        return randomPost;
    }

    @Override
    public List<Post> getSearchedFeedUpdate(int userId, String search) {
        UserSearchedData userSearchedData = new UserSearchedData();
        User user = this.userRepository.findByUserId(userId);
        PostCategoryMst postCategoryMst = this.categoryMst.findByName(search);
        userSearchedData.setUser(user);
        userSearchedData.setCategory(postCategoryMst);
        this.dataRepository.save(userSearchedData);



        List<UserInteraction> likedInteractions = userInteractionRepository.
                findByUserUserIdAndInteractionType(Long.parseLong(String.valueOf(userId)),
                "like");

        List<Post> likedPosts = new ArrayList<>();
        for (UserInteraction interaction : likedInteractions) {
            likedPosts.add(interaction.getPost());
        }

        List<Long> excludeIds = new ArrayList<>();

        for (Post post : likedPosts) {
            excludeIds.add((long) post.getPostId());
        }

        List<Post> randomPost = new ArrayList<>();

        if (excludeIds.isEmpty())
            randomPost = postRepository.findRandomPostIds(2);
        else
            randomPost = postRepository.findRandomPostIds(2, excludeIds);

        List<UserInteraction> userInteractionsList = new ArrayList<>();


        for (Post random : randomPost) {
            userInteractionsList.add(new UserInteraction(random.getUser(), random, "fetched"));
        }

        this.userInteractionRepository.saveAll(userInteractionsList);


        return randomPost;
    }
}

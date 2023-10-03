package com.app.serviceimpl;

import com.app.model.entity.User;
import com.app.model.entity.UserFollowing;
import com.app.model.request.UserFollowingRequest;
import com.app.repository.UserFollowingRepository;
import com.app.repository.UserRepository;
import com.app.service.UserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFollowServiceImpl implements UserFollowService {

    @Autowired
    private UserFollowingRepository userFollowRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserFollowing followUser(UserFollowingRequest userFollow) {

        UserFollowing following = new UserFollowing();

        User userFollower =  this.userRepository.findByUserId(userFollow.getFollowerUserId());

        User userFollowing = this.userRepository.findByUserId(userFollow.getFollowingUserId());

        following.setFollowingUser(userFollowing);

        following.setFollowerUser(userFollower);

        return userFollowRepository.save(following);
    }

}


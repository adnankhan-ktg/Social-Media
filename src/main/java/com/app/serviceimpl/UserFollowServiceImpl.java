package com.app.serviceimpl;

import com.app.model.entity.UserFollowing;
import com.app.repository.UserFollowingRepository;
import com.app.service.UserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFollowServiceImpl implements UserFollowService {

    @Autowired
    private UserFollowingRepository userFollowRepository;

    @Override
    public UserFollowing followUser(UserFollowing userFollow) {
        // Validation and follow user logic
        // You can implement other user-follow related methods here
        return userFollowRepository.save(userFollow);
    }

    // Implement other user-follow related methods
}


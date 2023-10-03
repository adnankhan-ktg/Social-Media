package com.app.service;

import com.app.model.entity.UserFollowing;
import com.app.model.request.UserFollowingRequest;

public interface UserFollowService {
    UserFollowing followUser(UserFollowingRequest userFollow);
    // Add other user-follow related methods
}

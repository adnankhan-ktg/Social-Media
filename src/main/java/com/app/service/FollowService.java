package com.app.service;

import com.app.model.payload.UserFollowingRequest;
import com.app.model.response.CommonResponse;

public interface FollowService {
    CommonResponse followRequest(UserFollowingRequest userFollow);

    CommonResponse getPendingFriendRequests(int userId);

    CommonResponse respondToFriendRequest(int requestId, String status);
}

package com.app.service;

import com.app.model.payload.UserFollowingRequest;
import com.app.model.response.CommonResponse;

public interface FollowService {
    CommonResponse followRequest(UserFollowingRequest userFollow);

    CommonResponse unFollowUser(UserFollowingRequest request);

    CommonResponse getPendingFriendRequests(int userId);

    CommonResponse respondToFriendRequest(int requestId, String status);

    CommonResponse getFollowersForUser(int userId);

    CommonResponse getFollowingsForUser(int userId);
}

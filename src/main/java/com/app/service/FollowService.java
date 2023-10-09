package com.app.service;

import com.app.model.payload.UserFollowingRequest;
import com.app.model.response.CommonResponse;

public interface FollowService {
    CommonResponse followUser(UserFollowingRequest userFollow);
}

package com.app.service;

import com.app.model.request.UserFollowingRequest;
import com.app.model.response.CommonResponse;

public interface FollowsService {
    CommonResponse followUser(UserFollowingRequest userFollow);
}

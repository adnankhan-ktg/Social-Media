package com.app.service;

import com.app.model.response.CommonResponse;

public interface FeedService {

      CommonResponse loadLatestFeed(int userId, int limit);
}

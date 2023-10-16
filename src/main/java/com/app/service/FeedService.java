package com.app.service;
import java.util.List;

import com.app.model.entity.Post;
import com.app.model.response.CommonResponse;

public interface FeedService {

      CommonResponse loadLatestFeed(int userId, int limit);
}

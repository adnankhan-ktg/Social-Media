package com.app.service;
import java.util.List;

import com.app.model.entity.Post;

public interface FeedService {
	List<Post> getFeedUpdate(Long userId);

	List<Post> getSearchedFeedUpdate(int userId, String search);
}

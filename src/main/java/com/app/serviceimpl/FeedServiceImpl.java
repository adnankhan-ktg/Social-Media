package com.app.serviceimpl;

import com.app.model.response.CommonResponse;
import org.springframework.stereotype.Service;
import com.app.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService {
    @Override
    public CommonResponse getFeedUpdate(Long userId) {
        return null;
    }
}

package com.app.serviceimpl;

import com.app.helper.CommonResHelper;
import com.app.model.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.app.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService {

    private static final Logger log = LoggerFactory.getLogger(FeedServiceImpl.class);

    @Override
    public CommonResponse loadLatestFeed(int userId) {
        log.info("FeedServiceImpl :: loadLatestFeed - START");
        CommonResponse response = new CommonResponse();
        try {

        } catch (Exception ex) {
            log.error("HashtagController :: loadLatestFeed - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("FeedServiceImpl :: loadLatestFeed - END");
        return response;
    }
}

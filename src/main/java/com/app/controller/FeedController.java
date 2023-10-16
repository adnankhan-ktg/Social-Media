package com.app.controller;

import java.util.List;

import com.app.helper.CommonResHelper;
import com.app.model.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.model.entity.Post;
import com.app.service.FeedService;

@RestController
@RequestMapping("/feed")
public class FeedController {

    private final static Logger log = LoggerFactory.getLogger(FeedController.class);

    @Autowired
    private FeedService feedService;


    @GetMapping("/latest")
    public CommonResponse loadLatestFeed(@RequestParam("userId") int userId,
                                         @RequestParam(value = "userId") int limit) {
        log.info("FeedController :: loadLatestFeed - START");
        CommonResponse response;
        try {
            response = this.feedService.loadLatestFeed(userId, limit);
        } catch (Exception ex) {
            log.error("HashtagController :: loadLatestFeed - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("FeedController :: loadLatestFeed - END");
        return response;
    }
}

package com.app.controller;

import com.app.helper.CommonResHelper;
import com.app.model.response.CommonResponse;
import com.app.service.HashtagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "hashtag")
public class HashtagController {

    private final static Logger log = LoggerFactory.getLogger(HashtagController.class);

    @Autowired
    private HashtagService hashtagService;

    @GetMapping
    public CommonResponse searchHashtag(@RequestParam("hashtagName") String hashtag) {
        log.info("HashtagController :: searchHashtag - START");
        CommonResponse response;
        try {
            response = this.hashtagService.searchHashtag(hashtag);
        } catch (Exception ex) {
            log.error("HashtagController :: searchHashtag - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("HashtagController :: searchHashtag - END");
        return response;
    }
}

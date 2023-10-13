package com.app.controller;

import java.util.List;

import com.app.model.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.model.entity.Post;
import com.app.service.FeedService;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/update/{userId}")
    public CommonResponse getFeedUpdate(@PathVariable Long userId) {
        return new CommonResponse();
    }
}

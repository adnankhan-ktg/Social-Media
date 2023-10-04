package com.app.controller;

import com.app.model.request.UserFollowingRequest;
import com.app.model.response.CommonResponse;
import com.app.service.FollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userfollow")
public class UserFollowController {

    @Autowired
    private FollowsService userFollowService;

    @PostMapping("/follow")
    public ResponseEntity<CommonResponse> followUser(@RequestBody UserFollowingRequest userFollow) {
        return new ResponseEntity<>(userFollowService.followUser(userFollow), HttpStatus.CREATED);
    }
}
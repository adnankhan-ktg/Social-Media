package com.app.controller;

import com.app.model.entity.UserFollowing;
import com.app.service.UserFollowService;
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
    private UserFollowService userFollowService;

    @PostMapping("/follow")
    public ResponseEntity<UserFollowing> followUser(@RequestBody UserFollowing userFollow) {
        UserFollowing followedUser = userFollowService.followUser(userFollow);
        return new ResponseEntity<>(followedUser, HttpStatus.CREATED);
    }

    // Add other user-follow related endpoints (e.g., unfollow, get followers, get following, etc.)
}
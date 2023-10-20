package com.app.controller;

import com.app.helper.CommonResHelper;
import com.app.model.enums.FollowStatus;
import com.app.model.payload.UserFollowingRequest;
import com.app.model.response.CommonResponse;
import com.app.service.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/follow")
public class UserFollowController {

    @Autowired
    private FollowService followService;

    private final static Logger log = LoggerFactory.getLogger(UserFollowController.class);

    @PostMapping("/request")
    public CommonResponse followUser(@RequestBody UserFollowingRequest userFollow) {
        log.info("UserFollowController :: followUser - START");
        CommonResponse response = new CommonResponse();

        try {
            if (userFollow.getFollowedId() == userFollow.getFollowerId()) {
                response.setMsg("followed and follower can't be same!");
                response.setStatusCode(-1090);
            } else {
                response = followService.followRequest(userFollow);
                log.info("UserFollowController :: followUser - Follow request sent successfully");
            }
        } catch (Exception ex) {
            log.error("UserFollowController :: followUser - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }

        log.info("UserFollowController :: followUser - END");
        return response;
    }

    @GetMapping("/pending/{userId}/friend-requests")
    public CommonResponse getPendingFriendRequests(@PathVariable("userId") int userId) {
        log.info("UserFollowController :: pendingFriendRequests - START");
        CommonResponse response;
        try {
            response = this.followService.getPendingFriendRequests(userId);
        } catch (Exception ex) {
            log.error("UserFollowController :: pendingFriendRequests - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }

        log.info("UserFollowController :: pendingFriendRequests - END");
        return response;
    }

    @PutMapping("/action/{requestId}/{status}")
    public CommonResponse respondToFriendRequest(@PathVariable("requestId") int id,
                                                 @PathVariable("status") String status) {
        log.info("UserFollowController :: responseToFriendRequest - START");
        CommonResponse response;
        try {
            if (!status.equalsIgnoreCase(FollowStatus.APPROVED.toString())
                    && status.equalsIgnoreCase(FollowStatus.REJECTED.toString())) {
                response = new CommonResponse();
                response.setMsg("Invalid status");
                response.setStatusCode(400);
            } else {
                response = this.followService.respondToFriendRequest(id, status);
            }
        } catch (Exception ex) {
            log.error("UserFollowController :: responseToFriendRequest - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("UserFollowController :: responseToFriendRequest - END");
        return response;
    }

    @GetMapping("/{userId}/followers")
    public CommonResponse getFollowersForUser(@PathVariable("userId") int userId) {
        log.info("UserFollowController :: getFollowersForUser - START");
        CommonResponse response;
        try {
            response = this.followService.getFollowersForUser(userId);
        } catch (Exception ex) {
            log.error("UserFollowController :: responseToFriendRequest - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("UserFollowController :: getFollowersForUser - END");
        return response;
    }

    @GetMapping("/{userId}/followings")
    public CommonResponse getFollowingsForUser(@PathVariable("userId") int userId) {
        log.info("UserFollowController :: getFollowingsForUser  - START");
        CommonResponse response;
        try {
            response = this.followService.getFollowingsForUser(userId);
        } catch (Exception ex) {
            log.error("UserFollowController :: getFollowingsForUser - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("UserFollowController :: getFollowingsForUser  - END");
        return response;
    }


    @DeleteMapping("/unfollowing")
    public CommonResponse unFollowUser(@RequestBody UserFollowingRequest request) {
        log.info("UserFollowController :: unFollowUser  - START");
        CommonResponse response;
        try {
            response = this.followService.unFollowUser(request);
        } catch (Exception ex) {
            log.error("UserFollowController :: unFollowUser - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("UserFollowController :: unFollowUser  - END");
        return response;
    }
}
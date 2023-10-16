package com.app.serviceimpl;

import com.app.helper.CommonResHelper;
import com.app.model.dto.FollowersDto;
import com.app.model.entity.UserFollowings;
import com.app.model.entity.User;
import com.app.model.enums.FollowStatus;
import com.app.model.payload.UserFollowingRequest;
import com.app.model.response.CommonResponse;
import com.app.repository.UserFollowingRepository;
import com.app.repository.UserRepository;
import com.app.service.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FollowsServiceImpl implements FollowService {

    @Autowired
    private UserFollowingRepository userFollowRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(FollowsServiceImpl.class);

    @Override
    public CommonResponse followRequest(UserFollowingRequest userFollow) {
        log.info("FollowServiceImpl :: followRequest - START");
        CommonResponse response = new CommonResponse();

        try {
            if (this.userFollowRepository.checkRelationship(userFollow.getFollowedId(), userFollow.getFollowerId()) > 0) {
                response.setMsg("Already Followed");
                response.setStatusCode(-1012);
            } else {

                UserFollowings following = new UserFollowings();

                Optional<User> followedUser = this.userRepository.findById(userFollow.getFollowedId());

                Optional<User> followerUser = this.userRepository.findById(userFollow.getFollowerId());


                if (followedUser.isEmpty() || followerUser.isEmpty()) {
                    response.setMsg("User does not exists!");
                    response.setStatusCode(404);
                } else {

                    following.setFollowerUser(followerUser.get());
                    following.setFollowedUser(followedUser.get());

                    following.setStatus(followedUser.get().getAccountType().equalsIgnoreCase("private") ? FollowStatus.PENDING : FollowStatus.APPROVED);

                    UserFollowings follows = userFollowRepository.save(following);

                    if (Objects.isNull(follows)) {
                        response.setStatusCode(500);
                        response.setMsg("Internal Server Error!");
                    } else {
                        response.setMsg("Successfully Followed!");
                        response.setStatusCode(200);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("FollowsServiceImpl :: followRequest - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("FollowServiceImpl :: followRequest - END");
        return response;
    }

    @Override
    public CommonResponse getPendingFriendRequests(int userId) {
        log.info("FollowsServiceImpl :: getPendingFriendRequests - START");
        CommonResponse response = new CommonResponse();
        try {
            List<UserFollowings> userFolllowingList = this.userFollowRepository.getPendingFriendRequests(userId);
            if (userFolllowingList.isEmpty()) {
                response.setStatusCode(404);
                response.setMsg("No pending friend requests found for this user");
            } else {
                response.setStatusCode(200);
                response.setMsg("Pending requests successfully loaded");
                response.setData(userFolllowingList);
            }
        } catch (Exception ex) {
            log.error("FollowsServiceImpl :: getPendingFriendRequests - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("FollowsServiceImpl :: getPendingFriendRequests - END");
        return response;
    }

    @Override
    public CommonResponse respondToFriendRequest(int requestId, String status) {
        log.info("FollowsServiceImpl :: responseToFriendRequest - START");
        CommonResponse response = new CommonResponse();
        try {
            this.userFollowRepository.respondToFriendRequest(requestId, status.toUpperCase());

            String finalStatus = status.equalsIgnoreCase("reject") ? "rejected" : "approved";

            response.setMsg("Friend request " + finalStatus + " successfully");
            response.setStatusCode(200);
        } catch (Exception ex) {
            log.error("FollowsServiceImpl :: responseToFriendRequest - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("FollowsServiceImpl :: responseToFriendRequest - START");
        return response;
    }

    @Override
    public CommonResponse getFollowersForUser(int userId) {
        log.info("FollowsServiceImpl :: getFollowersForUser - START");
        CommonResponse response = new CommonResponse();

        try {
            List<FollowersDto> followers = this.userFollowRepository.getFollowersForUser(userId);

            if (followers.isEmpty()) {
                response.setMsg("No followers found for this user");
                response.setStatusCode(404);
            } else {
                response.setStatusCode(200);
                response.setMsg("Followers have been successfully loaded");
                response.setData(followers);
            }
        } catch (Exception ex) {
            log.error("FollowsServiceImpl :: getFollowersForUser - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }

        log.info("FollowsServiceImpl :: getFollowersForUser - END");
        return response;
    }

    @Override
    public CommonResponse getFollowingsForUser(int userId) {
        log.info("FollowsServiceImpl :: getFollowingsForUser - START");
        CommonResponse response = new CommonResponse();
        try {
            List<FollowersDto> followings = this.userFollowRepository.getFollowingsForUser(userId);

            if (followings.isEmpty()) {
                response.setMsg("No followings found for this user");
                response.setStatusCode(404);
            } else {
                response.setStatusCode(200);
                response.setMsg("Followings have been successfully loaded");
                response.setData(followings);
            }
        } catch (Exception ex) {
            log.error("FollowsServiceImpl :: getFollowersForUser - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("FollowsServiceImpl :: getFollowingsForUser - END");
        return response;
    }
}


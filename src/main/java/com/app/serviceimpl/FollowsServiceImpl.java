package com.app.serviceimpl;

import com.app.model.entity.UserFollowings;
import com.app.model.entity.User;
import com.app.model.payload.UserFollowingRequest;
import com.app.model.response.CommonResponse;
import com.app.repository.UserFollowingRepository;
import com.app.repository.UserRepository;
import com.app.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;

@Service
public class FollowsServiceImpl implements FollowService {

    @Autowired
    private UserFollowingRepository userFollowRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommonResponse followUser(UserFollowingRequest userFollow) {

        CommonResponse res = new CommonResponse();

        if (this.userFollowRepository.checkRelationship(userFollow.getFollowerUserId()
                , userFollow.getFollowingUserId()) > 0) {
            res.setMsg("Already Followed");
            res.setStatusCode(-1012);
        } else {

            UserFollowings following = new UserFollowings();

            Optional<User> userFollowed = this.userRepository.findById(userFollow.getFollowerUserId());

            Optional<User> userFollowing = this.userRepository.findById(userFollow.getFollowingUserId());

            following.setFollowingUser(userFollowing.get());

            following.setFollowerUser(userFollowed.get());

            UserFollowings follows = userFollowRepository.save(following);

            if (Objects.isNull(follows)) {
                res.setStatusCode(500);
                res.setMsg("Internal Server Error!");
            } else {
                res.setMsg("Successfully Followed!");
                res.setStatusCode(200);
            }
        }
        return res;
    }
}


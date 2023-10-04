package com.app.serviceimpl;

import com.app.model.entity.Follows;
import com.app.model.entity.User;
import com.app.model.request.UserFollowingRequest;
import com.app.model.response.CommonResponse;
import com.app.repository.UserFollowingRepository;
import com.app.repository.UserRepository;
import com.app.service.FollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class FollowsServiceImpl implements FollowsService {

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

            Follows following = new Follows();

            User userFollowed = this.userRepository.findByUserId(userFollow.getFollowerUserId());

            User userFollowing = this.userRepository.findByUserId(userFollow.getFollowingUserId());

            following.setFollowingUser(userFollowing);

            following.setFollowedUser(userFollowed);

            Follows follows = userFollowRepository.save(following);

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


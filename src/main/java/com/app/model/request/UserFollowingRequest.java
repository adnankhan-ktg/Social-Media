package com.app.model.request;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class UserFollowingRequest {

    private int followerUserId;

    private int followingUserId;
}

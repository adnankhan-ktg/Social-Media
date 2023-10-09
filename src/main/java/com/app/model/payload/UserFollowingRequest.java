package com.app.model.payload;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFollowingRequest {

    private int followerUserId;

    private int followingUserId;
}

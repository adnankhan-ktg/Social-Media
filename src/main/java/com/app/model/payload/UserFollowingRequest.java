package com.app.model.payload;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFollowingRequest {

    private int followerId;

    private int followedId;
}

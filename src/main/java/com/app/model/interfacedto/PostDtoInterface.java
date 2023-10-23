package com.app.model.interfacedto;

import java.util.Date;

public interface PostDtoInterface {

    String getFollowing();

    int getPostId();

    String getContentType();

    String getCaption();

    String getPostUrl();

    Date getTimestamp();

    long getTotalLikes();

    long getTotalComments();
}

package com.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class LatestFeedDto {

    private String following;

    private int postId;

    private String contentType;

    private String caption;

    private String postUrl;

    private Date timestamp;

    private long totalLikes;

    private long totalComments;
}

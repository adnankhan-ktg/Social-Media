package com.app.service;

import com.app.model.payload.PostCommentRequest;
import com.app.model.response.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    CommonResponse createPost(String postDesc, MultipartFile post);

    CommonResponse doLike(int userId, int postId);

    CommonResponse doComment(PostCommentRequest postCommentRequest);

}

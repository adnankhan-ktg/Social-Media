package com.app.service;

import com.app.model.entity.Post;
import com.app.model.request.PostCommentRequest;
import com.app.model.response.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    CommonResponse createPost(String postDesc, MultipartFile post);

    CommonResponse commentOnPost(PostCommentRequest postCommentRequest);
}

package com.app.service;

import com.app.model.payload.PostCommentRequest;
import com.app.model.response.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    CommonResponse createPost(String postDesc, MultipartFile post);

    CommonResponse likeOrUnlikePost(int userId, int postId, String useCase);

    CommonResponse addComment(PostCommentRequest postCommentRequest);

    CommonResponse deleteCommentById(int id);

    CommonResponse getCommentsForPost(int postId);

    CommonResponse getLikesForPost(int postId);
}

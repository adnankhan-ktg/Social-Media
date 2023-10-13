package com.app.controller;

import com.app.helper.CommonResHelper;
import com.app.model.payload.PostCommentRequest;
import com.app.model.response.CommonResponse;
import com.app.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public CommonResponse createPost(@RequestParam String values, @RequestParam MultipartFile post) {
        log.info("PostController :: createPost - START");
        CommonResponse res;
        try {
            log.info("PostController :: createPost - Creating a new post");
            res = postService.createPost(values, post);
        } catch (Exception ex) {
            log.error("PostController :: createPost - Exception: {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }
        log.info("PostController :: createPost - END");
        return res;
    }

    @PostMapping("/likeOrUnlike")
    public CommonResponse likeOrUnlike(@RequestParam(name = "userId") int userId, @RequestParam("postId") int postId, @RequestParam(name = "case") String useCase) {
        log.info("PostController :: likeOrUnlike - START");
        CommonResponse res;
        try {
            log.info("PostController :: likeOrUnlike - Liking/Unliking a post");
            res = postService.likeOrUnlikePost(userId, postId, useCase);
        } catch (Exception ex) {
            log.error("PostController :: likeOrUnlike - Exception: {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }
        log.info("PostController :: likeOrUnlike - END");
        return res;
    }

    @PostMapping("/comment")
    public CommonResponse addComment(@RequestBody PostCommentRequest request) {
        log.info("PostController :: addComment - START");
        CommonResponse res;
        try {
            log.info("PostController :: addComment - Adding a new comment");
            res = postService.addComment(request);
        } catch (Exception ex) {
            log.error("PostController :: addComment - Exception: {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }
        log.info("PostController :: addComment - END");
        return res;
    }

    @DeleteMapping("/comment/{commentId}")
    public CommonResponse deleteComment(@PathVariable("commentId") int id) {
        log.info("PostController :: deleteComment - START");
        CommonResponse res;
        try {
            log.info("PostController :: deleteComment - Deleting comment with ID: {}", id);
            res = postService.deleteCommentById(id);
        } catch (Exception ex) {
            log.error("PostController :: deleteComment - Exception: {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }
        log.info("PostController :: deleteComment - END");
        return res;
    }

    @GetMapping("/{postId}/comments")
    public CommonResponse getCommentsForPost(@PathVariable("postId") int postId) {
        log.info("PostController :: getCommentsForPost - START");
        CommonResponse res;
        try {
            log.info("PostController :: getCommentsForPost - Fetching comments for post with ID: {}", postId);
            res = postService.getCommentsForPost(postId);
        } catch (Exception ex) {
            log.error("PostController :: getCommentsForPost - Exception: {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }
        log.info("PostController :: getCommentsForPost - END");
        return res;
    }

    @GetMapping("/{postId}/likes")
    public CommonResponse getLikesForPost(@PathVariable("postId") int postId) {
        log.info("PostController :: getLikesForPost - START");
        CommonResponse res = new CommonResponse();

        try {
            res = this.postService.getLikesForPost(postId);
        } catch (Exception ex) {
            log.error("PostController :: getLikesForPost - Exception: {}", ex.getMessage());
            res = CommonResHelper.internalServerError();
        }

        log.info("PostController :: getLikesForPost - END");
        return res;
    }
}
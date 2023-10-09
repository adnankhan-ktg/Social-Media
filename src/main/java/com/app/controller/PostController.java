package com.app.controller;

import com.app.model.payload.PostCommentRequest;
import com.app.model.response.CommonResponse;
import com.app.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
public class PostController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public CommonResponse createPost(@RequestParam String values, @RequestParam MultipartFile post) {
        return postService.createPost(values, post);
    }


    @PostMapping("/like")
    public CommonResponse doLike(@RequestParam(name = "userId") int userId,
                                 @RequestParam("/postId") int postId) {
        log.info("PostController :: doLike === START");
        return this.postService.doLike(userId,postId);
    }

    @PostMapping("/comment")
    public CommonResponse doComment(@RequestBody PostCommentRequest request) {
        log.info("PostController :: doComment === START");
        return this.postService.doComment(request);
    }
}
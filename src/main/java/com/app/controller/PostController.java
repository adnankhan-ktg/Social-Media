package com.app.controller;

import com.app.model.entity.Post;
import com.app.model.response.CommonResponse;
import com.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public CommonResponse createPost(@RequestParam String values, @RequestParam MultipartFile post) {
        return postService.createPost(values, post);
    }
}
package com.app.controller;

import com.app.model.entity.InteractionLog;
import com.app.model.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interactions")
public class InteractionController {

//    @Autowired
//    private UserInteractionService interactionService;

//    @PostMapping("/like")
//    public ResponseEntity<CommonResponse> likePost(@RequestBody UserInteraction interaction) {
//        UserInteraction likedInteraction = interactionService.likePost(interaction);
//        return new ResponseEntity<>(likedInteraction, HttpStatus.CREATED);
//    }
}

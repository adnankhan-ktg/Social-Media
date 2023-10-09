package com.app.controller;

import com.app.model.payload.SignUpPayload;
import com.app.model.response.CommonResponse;
import com.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public CommonResponse registerUser(@RequestBody SignUpPayload request) {
        log.info("UserController :: registerUser === START");
        return userService.signUp(request);
    }


    @GetMapping("/fetchAll")
    public CommonResponse fetchAllUser(){
        log.info("UserController :: fetchAllUser === START");
        return this.userService.fetchAllUser();
    }
}
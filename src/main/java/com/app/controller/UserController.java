package com.app.controller;

import com.app.model.request.UserRegisterRequest;
import com.app.model.response.CommonResponse;
import com.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public CommonResponse registerUser(@RequestBody UserRegisterRequest request) {
        log.info("UserController :: registerUser === START");
        return userService.registerUser(request);
    }


    @GetMapping("/fetchAll")
    public CommonResponse fetchAllUser(){
        return this.userService.fetchAllUser();
    }
}
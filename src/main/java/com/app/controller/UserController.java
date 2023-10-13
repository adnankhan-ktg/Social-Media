package com.app.controller;

import com.app.helper.CommonResHelper;
import com.app.model.payload.SignUpPayload;
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
    public CommonResponse registerUser(@RequestBody SignUpPayload request) {
        log.info("UserController :: registerUser - START");
        CommonResponse response;

        try {
            response = userService.signUp(request);
            log.info("UserController :: registerUser - User registered successfully");
        } catch (Exception ex) {
            log.error("UserController :: registerUser - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }

        log.info("UserController :: registerUser - END");
        return response;
    }

    @GetMapping("/all")
    public CommonResponse fetchAllUser() {
        log.info("UserController :: fetchAllUser - START");
        CommonResponse response;

        try {
            response = userService.fetchAllUser();
            log.info("UserController :: fetchAllUser - Fetched all users successfully");
        } catch (Exception ex) {
            log.error("UserController :: fetchAllUser - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }

        log.info("UserController :: fetchAllUser - END");
        return response;
    }
}
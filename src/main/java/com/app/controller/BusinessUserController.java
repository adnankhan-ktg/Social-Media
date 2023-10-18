package com.app.controller;


import com.app.helper.CommonResHelper;
import com.app.model.payload.BusinessUserSignUpPayload;
import com.app.model.response.CommonResponse;
import com.app.service.BusinessUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business/user")
public class BusinessUserController {

    private static final Logger log = LoggerFactory.getLogger(BusinessUserController.class);

    @Autowired
    private BusinessUserService businessUserService;

    @PostMapping("/register")
    public CommonResponse businessUserRegister(@RequestBody BusinessUserSignUpPayload payload) {
        log.info("BusinessUserController :: businessUserRegister - START");
        CommonResponse response;
        try {
            response = this.businessUserService.businessUserRegister(payload);
        } catch (Exception ex) {
            log.error("BusinessUserController :: businessUserRegister - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("BusinessUserController :: businessUserRegister - END");
        return response;
    }
}

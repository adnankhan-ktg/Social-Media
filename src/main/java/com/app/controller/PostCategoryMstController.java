package com.app.controller;

import com.app.helper.CommonResHelper;
import com.app.model.entity.CategoryMst;
import com.app.model.response.CommonResponse;
import com.app.service.PostCategoryMstService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class PostCategoryMstController {

    private final static Logger log = LoggerFactory.getLogger(PostCategoryMstController.class);


    @Autowired
    private PostCategoryMstService postCategoryMstService;

    @GetMapping("/fetchAll")
    public CommonResponse getCategoryMstData() {
        log.info("PostCategoryMstController :: getCategoryMstData - START");
        CommonResponse response;
        try {
          response = this.postCategoryMstService.getCategoryMstData();
        } catch (Exception ex) {
            log.error("PostCategoryMstController :: getCategoryMstData - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("PostCategoryMstController :: getCategoryMstData - END");
        return response;
    }
}

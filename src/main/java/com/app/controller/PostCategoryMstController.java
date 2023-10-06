package com.app.controller;

import com.app.model.entity.CategoryMst;
import com.app.service.PostCategoryMstService;
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

    @Autowired
    private PostCategoryMstService postCategoryMstService;

    @GetMapping("/fetchAll")
    public ResponseEntity<List<CategoryMst>> getCategoryMstData() {
        return new ResponseEntity<>(this.postCategoryMstService.getCategoryMstData(), HttpStatus.OK);

    }
}

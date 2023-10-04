package com.app.serviceimpl;

import com.app.model.entity.PostCategoryMst;
import com.app.repository.PostCategoryMstRepository;
import com.app.service.PostCategoryMstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCategoryMstServiceImpl implements PostCategoryMstService {

    @Autowired
    private PostCategoryMstRepository categoryMstRepository;

    @Override
    public List<PostCategoryMst> getCategoryMstData() {
        return this.categoryMstRepository.findAll();
    }
}

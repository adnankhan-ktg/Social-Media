package com.app.serviceimpl;

import com.app.helper.CommonResHelper;
import com.app.model.entity.CategoryMst;
import com.app.model.response.CommonResponse;
import com.app.repository.PostCategoryMstRepository;
import com.app.service.PostCategoryMstService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCategoryMstServiceImpl implements PostCategoryMstService {

    @Autowired
    private PostCategoryMstRepository categoryMstRepository;

    private static final Logger log = LoggerFactory.getLogger(PostCategoryMstService.class);

    @Override
    public CommonResponse getCategoryMstData() {
        log.info("PostCategoryMstServiceImpl :: getCategoryMstDate - START");
        CommonResponse response = new CommonResponse();
        try {
            List<CategoryMst> categoryMsts = this.categoryMstRepository.findAll();

            if (categoryMsts.isEmpty()) {
                response.setStatusCode(404);
                response.setMsg("No category found for now");
            } else {
                response.setData(categoryMsts);
                response.setMsg("Category have been successfully loaded");
                response.setStatusCode(200);
            }
        } catch (Exception ex) {
            log.error("PostCategoryMstServiceImpl :: getCategoryMstDate :: Exception = {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("PostCategoryMstServiceImpl :: getCategoryMstDate - END");
        return response;
    }
}

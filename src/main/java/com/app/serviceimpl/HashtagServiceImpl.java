package com.app.serviceimpl;

import com.app.helper.CommonResHelper;
import com.app.model.response.CommonResponse;
import com.app.service.HashtagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HashtagServiceImpl implements HashtagService {

    private final static Logger log = LoggerFactory.getLogger(HashtagServiceImpl.class);

    @Override
    public CommonResponse searchHashtag(String hashtagName) {
        log.info("HashtagController :: searchHashtag - START");
        CommonResponse response = new CommonResponse();
        try{

        }catch (Exception ex){
            log.error("HashtagController :: searchHashtag - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("HashtagController :: searchHashtag - END");
        return response;
    }
}

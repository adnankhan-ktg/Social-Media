package com.app.serviceimpl;

import com.app.helper.CommonResHelper;
import com.app.model.entity.BusinessUser;
import com.app.model.payload.BusinessUserSignUpPayload;
import com.app.model.response.CommonResponse;
import com.app.repository.BusinessUserRepository;
import com.app.service.BusinessUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class BusinessUserServiceImpl implements BusinessUserService {

    private static final Logger log = LoggerFactory.getLogger(BusinessUserServiceImpl.class);

    @Autowired
    private BusinessUserRepository businessUserRepository;

    @Override
    public CommonResponse businessUserRegister(BusinessUserSignUpPayload userSignUpPayload) {
        log.info("BusinessUserServiceImpl :: businessUserRegister - START");
        CommonResponse response = new CommonResponse();

        try {

            if (Objects.nonNull(this.businessUserRepository.findByEmail(userSignUpPayload.getEmail()))) {
                response.setStatusCode(-1010);
                response.setMsg("User has already registered, please click signup below!");
                return response;
            }

            BusinessUser businessUser = new BusinessUser();

            BeanUtils.copyProperties(userSignUpPayload, businessUser);

            BusinessUser savedUser = this.businessUserRepository.save(businessUser);
            if (Objects.isNull(savedUser)) {
                response = CommonResHelper.internalServerError();
            } else {
                response.setStatusCode(200);
                response.setMsg("Business user saved successfully!");
            }
        } catch (Exception ex) {
            log.error("FollowsServiceImpl :: businessUserRegister - Exception: {}", ex.getMessage());
            response = CommonResHelper.internalServerError();
        }
        log.info("BusinessUserServiceImpl :: businessUserRegister - END");
        return response;
    }
}

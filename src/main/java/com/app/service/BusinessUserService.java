package com.app.service;

import com.app.model.payload.BusinessUserSignUpPayload;
import com.app.model.response.CommonResponse;

public interface BusinessUserService {

    CommonResponse businessUserRegister(BusinessUserSignUpPayload userSignUpPayload);
}

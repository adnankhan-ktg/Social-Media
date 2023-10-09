package com.app.service;

import com.app.model.payload.SignUpPayload;
import com.app.model.response.CommonResponse;

public interface UserService {
    CommonResponse signUp(SignUpPayload user);

    CommonResponse fetchAllUser();
}

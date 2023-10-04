package com.app.service;

import com.app.model.entity.User;
import com.app.model.request.UserRegisterRequest;
import com.app.model.response.CommonResponse;

public interface UserService {
    CommonResponse registerUser(UserRegisterRequest user);

    CommonResponse fetchAllUser();
}

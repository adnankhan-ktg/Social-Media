package com.app.serviceimpl;

import com.app.controller.UserController;
import com.app.model.entity.User;
import com.app.model.payload.SignUpPayload;
import com.app.model.response.CommonResponse;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Override
    public CommonResponse signUp(SignUpPayload user) {
        log.info("UserServiceImpl :: signUp === START");

        User newUser = new User();
        CommonResponse res = new CommonResponse();

        try {

            if (Objects.nonNull(this.userRepository.findByEmail(user.getEmail()))) {
                res.setStatusCode(-1010);
                res.setMsg("User has already registered, please click signup below!");
                return res;
            }

            BeanUtils.copyProperties(user, newUser);

            User u = userRepository.save(newUser);

            if (Objects.nonNull(u)) {
                res.setMsg("User has been successfully inserted!");
                res.setStatusCode(200);
            } else {
                res.setMsg("Internal Server Error!");
                res.setStatusCode(500);
            }
        } catch (Exception ex) {
            log.error("UserServiceImpl::signUp::Exception ={}", ex.getMessage());
            res.setMsg("Internal Server Error!");
            res.setStatusCode(500);
        }
        return res;
    }

    @Override
    public CommonResponse fetchAllUser() {

        CommonResponse cmn = new CommonResponse();
        List<User> users = this.userRepository.findAll();

        cmn.setData(users);
        cmn.setStatusCode(200);
        cmn.setMsg("Fetched Users Successfully!");
        return cmn;
    }
}
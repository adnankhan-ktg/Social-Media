package com.app.serviceimpl;

import com.app.model.entity.User;
import com.app.model.request.UserRegisterRequest;
import com.app.model.response.CommonResponse;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommonResponse registerUser(UserRegisterRequest user) {

        User newUser = new User();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        CommonResponse res = new CommonResponse();

        try {

            if(Objects.nonNull(this.userRepository.findByEmail(user.getEmail())))
            {
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
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }
}
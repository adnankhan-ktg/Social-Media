package com.app.serviceimpl;

import com.app.model.entity.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        // Validation and registration logic
        // You can implement other user-related methods here
        return userRepository.save(user);
    }

    // Implement other user-related methods
}
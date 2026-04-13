package com.hospital.hospital_managment.Auth.service.impl;

import com.hospital.hospital_managment.Auth.dto.UserCreateRequest;
import com.hospital.hospital_managment.Auth.model.User;
import com.hospital.hospital_managment.Auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public User createUser(UserCreateRequest userCreateRequest){
        User user = new User();
//        user.setEmail(userCreateRequest);
        return user;
    }
}

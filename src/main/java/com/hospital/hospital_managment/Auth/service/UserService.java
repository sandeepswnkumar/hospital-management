package com.hospital.hospital_managment.Auth.service;

import com.hospital.hospital_managment.Auth.dto.UserCreateRequest;
import com.hospital.hospital_managment.Auth.dto.UserResponse;
import com.hospital.hospital_managment.Auth.model.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserCreateRequest createRequest);
    List<UserResponse> getAllUsers();
}

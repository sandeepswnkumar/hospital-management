package com.hospital.hospital_managment.Auth.service;

import com.hospital.hospital_managment.Auth.dto.UserCreateRequest;
import com.hospital.hospital_managment.Auth.model.User;

public interface UserService {
    User createUser(UserCreateRequest createRequest);
}

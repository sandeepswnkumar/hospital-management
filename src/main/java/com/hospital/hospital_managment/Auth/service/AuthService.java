package com.hospital.hospital_managment.Auth.service;

import com.hospital.hospital_managment.Auth.dto.UserLoginRequest;

public interface AuthService {
    public String adminLogin(UserLoginRequest userLoginRequest);
}

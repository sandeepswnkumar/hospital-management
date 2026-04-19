package com.hospital.hospital_managment.Auth.service;

import com.hospital.hospital_managment.Auth.dto.UserLoginRequest;
import com.hospital.hospital_managment.Auth.dto.UserLoginResponse;

public interface AuthService {
    UserLoginResponse adminLogin(UserLoginRequest userLoginRequest);
}

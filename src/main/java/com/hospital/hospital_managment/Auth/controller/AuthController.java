package com.hospital.hospital_managment.Auth.controller;

import com.hospital.hospital_managment.Auth.dto.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    public String adminLogin(UserLoginRequest userLoginRequest){

        return "";
    }



}

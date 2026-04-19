package com.hospital.hospital_managment.Auth.controller;

import com.hospital.hospital_managment.Auth.dto.UserLoginRequest;
import com.hospital.hospital_managment.Auth.dto.UserLoginResponse;
import com.hospital.hospital_managment.Auth.service.AuthService;
import com.hospital.hospital_managment.common.utils.ApiResponse;
import com.hospital.hospital_managment.common.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/admin-login")
    public ResponseEntity<ApiResponse<UserLoginResponse>> adminLogin(@RequestBody UserLoginRequest userLoginRequest){
        return ResponseUtil.success(authService.adminLogin(userLoginRequest), "Login Successfully");
    }



}

package com.hospital.hospital_managment.Auth.service.impl;

import com.hospital.hospital_managment.Auth.config.JwtTokenProvider;
import com.hospital.hospital_managment.Auth.dto.UserLoginRequest;
import com.hospital.hospital_managment.Auth.model.User;
import com.hospital.hospital_managment.Auth.repository.UserRepository;
import com.hospital.hospital_managment.Auth.service.AuthService;
import com.hospital.hospital_managment.Auth.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String adminLogin(UserLoginRequest userLoginRequest){
        if(!userRepository.existsByEmail(userLoginRequest.getEmail())){
            throw new RuntimeException("Invalid Credentials");
        }
        User user = userRepository.findByEmail(userLoginRequest.getEmail());
        if(!passwordService.matchPassword(userLoginRequest.getPassword(), user.getPasswordHash())){
            throw new RuntimeException("Invalid Credentials");
        }
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken();



        return "";
    }

}

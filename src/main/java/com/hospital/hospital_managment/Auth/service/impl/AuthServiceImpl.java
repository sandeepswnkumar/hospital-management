package com.hospital.hospital_managment.Auth.service.impl;

import com.hospital.hospital_managment.Auth.config.JwtTokenProvider;
import com.hospital.hospital_managment.Auth.dto.RefreshTokenRequest;
import com.hospital.hospital_managment.Auth.dto.UserLoginRequest;
import com.hospital.hospital_managment.Auth.dto.UserLoginResponse;
import com.hospital.hospital_managment.Auth.model.User;
import com.hospital.hospital_managment.Auth.repository.RefreshTokenRepository;
import com.hospital.hospital_managment.Auth.repository.UserRepository;
import com.hospital.hospital_managment.Auth.service.AuthService;
import com.hospital.hospital_managment.Auth.service.PasswordService;
import com.hospital.hospital_managment.Auth.service.TokenService;
import com.hospital.hospital_managment.Auth.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public UserLoginResponse adminLogin(UserLoginRequest userLoginRequest){
        if(!userRepository.existsByEmail(userLoginRequest.getEmail())){
            throw new RuntimeException("Invalid Email");
        }
        User user = userRepository.findByEmail(userLoginRequest.getEmail());
        if(!passwordService.matchPassword(userLoginRequest.getPassword(), user.getPasswordHash())){
            throw new RuntimeException("Invalid Password");
        }
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken();
        log.info("userid ==============" + user.getId());
        refreshTokenRepository.deleteByUser_Id(user.getId());
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setToken(refreshToken);
        refreshTokenRequest.setUser(user);
        tokenService.createRefreshToken(refreshTokenRequest);
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setAccessToken(accessToken);
        userLoginResponse.setRefreshToken(refreshToken);
        userLoginResponse.setUserResponse(userService.mapToUserResponseDto(user));
        return userLoginResponse;
    }
}

package com.hospital.hospital_managment.Auth.service.impl;

import com.hospital.hospital_managment.Auth.dto.RefreshTokenRequest;
import com.hospital.hospital_managment.Auth.dto.RefreshTokenResponse;
import com.hospital.hospital_managment.Auth.model.RefreshToken;
import com.hospital.hospital_managment.Auth.repository.RefreshTokenRepository;
import com.hospital.hospital_managment.Auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshTokenResponse createRefreshToken(RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenRequest.getToken());
        refreshToken.setUser(refreshTokenRequest.getUser());
        refreshToken.setExpiryDate(refreshTokenRequest.getExpiryDate());
        refreshToken.setIsRevoked(false);
        refreshToken.setCreatedAt(LocalDateTime.now());

        return null;
    }


    public RefreshTokenResponse mapToTokenResponse(RefreshToken refreshToken){
        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
//        refreshTokenResponse
        return refreshTokenResponse;
    }
}

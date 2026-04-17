package com.hospital.hospital_managment.Auth.service;

import com.hospital.hospital_managment.Auth.dto.RefreshTokenRequest;
import com.hospital.hospital_managment.Auth.dto.RefreshTokenResponse;

public interface TokenService {

    RefreshTokenResponse createRefreshToken(RefreshTokenRequest refreshTokenRequest);

}

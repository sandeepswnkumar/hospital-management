package com.hospital.hospital_managment.Auth.dto;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String accessToken;
    private String refreshToken;
    private UserResponse userResponse;
}

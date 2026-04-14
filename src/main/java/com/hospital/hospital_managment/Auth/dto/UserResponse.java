package com.hospital.hospital_managment.Auth.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String password;
    private UserDetailResponse userDetailResponse;
}

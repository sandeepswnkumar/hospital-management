package com.hospital.hospital_managment.Auth.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}

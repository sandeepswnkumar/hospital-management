package com.hospital.hospital_managment.Auth.service;

public interface PasswordService {
    String passwordEncode(String password);
    Boolean matchPassword(String rawPassword, String encodedPassword);

}

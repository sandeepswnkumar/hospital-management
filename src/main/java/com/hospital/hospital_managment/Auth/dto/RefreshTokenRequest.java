package com.hospital.hospital_managment.Auth.dto;

import com.hospital.hospital_managment.Auth.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RefreshTokenRequest {
//    id BIGSERIAL PRIMARY KEY,
//    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
//    token VARCHAR(500) NOT NULL,
//    expiry_date TIMESTAMP NOT NULL,
//    is_revoked BOOLEAN DEFAULT FALSE,
//    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    private User user;
    private String token;
    private LocalDateTime expiryDate;
    private Boolean isRevoked;





}

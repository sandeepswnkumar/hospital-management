package com.hospital.hospital_managment.Auth.config;

import com.hospital.hospital_managment.Auth.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

public class JwtTokenProvider {
    private final String jwtSecretKey = "PtdViDfjk1ms2GIArnDqfrNfarqVl6Zi";
    private final SecretKey key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
    private final long jwtExpirationMs = 15 * 60 * 1000;
    private final long refreshTokenExpirationMs = 7 * 24 * 60 * 60 * 1000;

    public String generateAccessToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(){
        return UUID.randomUUID().toString();
    }

}

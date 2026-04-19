package com.hospital.hospital_managment.Auth.config;

import com.hospital.hospital_managment.Auth.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
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

    public boolean validateToken(String jwtToken){
        try {
            return isTokenExpired(jwtToken);
        }catch (JwtException | IllegalArgumentException e){
            log.info("JWT Exceptions error " + e.getMessage());
            return false;
        }
    }

    public String getUserId(String jwtToken){
        return claimsJwtToken(jwtToken).getPayload().getSubject();
    }

    private Date getExpiration(String jwtToken){
        return claimsJwtToken(jwtToken).getPayload().getExpiration();
    }

    private boolean isTokenExpired(String jwtToken){
        return getExpiration(jwtToken).after(new Date());
    }


    private Jws<Claims> claimsJwtToken(String jwtToken){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwtToken);
    }

    public String generateRefreshToken(){
        return UUID.randomUUID().toString();
    }

}

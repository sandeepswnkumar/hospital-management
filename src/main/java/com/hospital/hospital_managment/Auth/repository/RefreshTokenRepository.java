package com.hospital.hospital_managment.Auth.repository;

import com.hospital.hospital_managment.Auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}

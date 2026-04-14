package com.hospital.hospital_managment.Auth.repository;

import com.hospital.hospital_managment.Auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
}

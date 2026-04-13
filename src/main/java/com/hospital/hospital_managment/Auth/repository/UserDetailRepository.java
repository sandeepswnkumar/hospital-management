package com.hospital.hospital_managment.Auth.repository;

import com.hospital.hospital_managment.Auth.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails, Long> {
}

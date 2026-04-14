package com.hospital.hospital_managment.common.master.repository;


import com.hospital.hospital_managment.common.master.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}

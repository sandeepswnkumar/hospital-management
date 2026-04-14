package com.hospital.hospital_managment.common.master.repository;

import com.hospital.hospital_managment.common.master.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}

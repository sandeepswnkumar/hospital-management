package com.hospital.hospital_managment.doctor.service;

import com.hospital.hospital_managment.Auth.dto.UserCreateRequest;
import com.hospital.hospital_managment.Auth.service.UserService;
import com.hospital.hospital_managment.doctor.dto.DoctorRequest;
import com.hospital.hospital_managment.doctor.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserService userService;

    public void createDoctor(DoctorRequest doctorRequest){


//        userService.createUser()
    }

    public UserCreateRequest mapToUserCreateRequest(DoctorRequest doctorRequest){
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setEmail(doctorRequest.getEmail());
        userCreateRequest.setEmail(doctorRequest.getEmail());
    }



}

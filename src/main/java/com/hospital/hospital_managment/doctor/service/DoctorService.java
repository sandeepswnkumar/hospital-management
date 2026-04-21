package com.hospital.hospital_managment.doctor.service;

import com.hospital.hospital_managment.Auth.dto.UserCreateRequest;
import com.hospital.hospital_managment.Auth.dto.UserDetailRequest;
import com.hospital.hospital_managment.Auth.dto.UserResponse;
import com.hospital.hospital_managment.Auth.model.User;
import com.hospital.hospital_managment.Auth.repository.UserRepository;
import com.hospital.hospital_managment.Auth.service.UserService;
import com.hospital.hospital_managment.common.utils.Helper;
import com.hospital.hospital_managment.doctor.dto.DoctorRequest;
import com.hospital.hospital_managment.doctor.dto.DoctorResponse;
import com.hospital.hospital_managment.doctor.model.Doctor;
import com.hospital.hospital_managment.doctor.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserService userService;
    private final Helper helper;
    private final UserRepository userRepository;

    @Transactional
    public DoctorResponse createDoctor(DoctorRequest doctorRequest){
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        UserDetailRequest userDetailRequest = getUserDetailRequest(doctorRequest);
        userCreateRequest.setEmail(doctorRequest.getEmail());
        userCreateRequest.setPassword(helper.generateRandomPassword());
        userCreateRequest.setUserDetailRequest(userDetailRequest);
        UserResponse userResponse = userService.createUser(userCreateRequest);
        User user = userRepository.findById(userResponse.getId()).orElseThrow(() -> new RuntimeException("User Not Found"));
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setDescription(doctorRequest.getDescription());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setConsultationFee(doctorRequest.getConsultationFee());
        doctor.setExperienceYears(doctorRequest.getExperienceYears());
        return mapToDoctorResponse(doctorRepository.save(doctor));

    }

    private static UserDetailRequest getUserDetailRequest(DoctorRequest doctorRequest) {
        UserDetailRequest userDetailRequest = new UserDetailRequest();
        userDetailRequest.setFirstName(doctorRequest.getFirstName());
        userDetailRequest.setMiddleName(doctorRequest.getMiddleName());
        userDetailRequest.setLastName(doctorRequest.getLastName());
        userDetailRequest.setGender(doctorRequest.getGender().toLowerCase());
        userDetailRequest.setAddress1(doctorRequest.getAddress1());
        userDetailRequest.setAddress2(doctorRequest.getAddress2());
        userDetailRequest.setCityId(doctorRequest.getCityId());
        userDetailRequest.setStateId(doctorRequest.getStateId());
        userDetailRequest.setCountryId(doctorRequest.getCountryId());
        return userDetailRequest;
    }


    public DoctorResponse mapToDoctorResponse(Doctor doctor){
        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setId(doctor.getId());
        doctorResponse.setUserResponse(userService.mapToUserResponseDto(doctor.getUser()));
        doctorResponse.setSpecialization(doctor.getSpecialization());
        doctorResponse.setDescription(doctor.getDescription());
        doctorResponse.setExperienceYears(doctor.getExperienceYears());
        doctorResponse.setConsultationFee(doctor.getConsultationFee());
        return doctorResponse;
    }


    public Page<Doctor> getAllDoctor(
            int page,
            int size,
            String sortBy,
            String sortDir,
            String search
    ){
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Doctor> specification = (root, query, cb) -> {
            if (search == null || search.isEmpty()) {
                return cb.conjunction();
            }
            return cb.or(
                cb.like(cb.lower(root.get("doctor_name")), "%" + search.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("specialization")), "%" + search.toLowerCase() + "%")
            );
        };
        return  doctorRepository.findAll(specification, pageable);
    }



}

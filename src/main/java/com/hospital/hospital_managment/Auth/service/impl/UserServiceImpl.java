package com.hospital.hospital_managment.Auth.service.impl;

import com.hospital.hospital_managment.Auth.dto.UserCreateRequest;
import com.hospital.hospital_managment.Auth.dto.UserDetailResponse;
import com.hospital.hospital_managment.Auth.dto.UserResponse;
import com.hospital.hospital_managment.Auth.model.User;
import com.hospital.hospital_managment.Auth.model.UserDetails;
import com.hospital.hospital_managment.Auth.repository.UserRepository;
import com.hospital.hospital_managment.Auth.service.PasswordService;
import com.hospital.hospital_managment.Auth.service.UserService;
import com.hospital.hospital_managment.common.master.Service.LocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordService passwordService;
    private final UserRepository userRepository;
    private final LocationService locationService;


    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest userCreateRequest){
        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        if (userCreateRequest.getUserDetailRequest() == null){
            throw new RuntimeException("User Details is required!");
        }
        User user = new User();
        user.setEmail(userCreateRequest.getEmail());
        user.setPasswordHash(passwordService.passwordEncode(userCreateRequest.getPassword()));
        UserDetails userDetails = getUserDetails(userCreateRequest, user);
        user.setUserDetails(userDetails);
        return mapToUserResponseDto(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponseDto).toList();
    }


    @Override
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return mapToUserResponseDto(user);
    }

    private static UserDetails getUserDetails(UserCreateRequest userCreateRequest, User user) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUser(user);
        userDetails.setFirstName(userCreateRequest.getUserDetailRequest().getFirstName());
        userDetails.setMiddleName(userCreateRequest.getUserDetailRequest().getMiddleName());
        userDetails.setLastName(userCreateRequest.getUserDetailRequest().getLastName());
        userDetails.setDateOfBirth(userCreateRequest.getUserDetailRequest().getDateOfBirth());
        userDetails.setGender(userCreateRequest.getUserDetailRequest().getGender());
        userDetails.setAddress1(userCreateRequest.getUserDetailRequest().getAddress1());
        userDetails.setAddress2(userCreateRequest.getUserDetailRequest().getAddress2());

        return userDetails;
    }



    public UserResponse mapToUserResponseDto(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setUserDetailResponse(mapToUserDetailsResponse(user.getUserDetails()));
        return userResponse;
    }

    public UserDetailResponse mapToUserDetailsResponse(UserDetails userDetails){
        UserDetailResponse userDetailResponse = new UserDetailResponse();
        userDetailResponse.setId(userDetails.getId());
        userDetailResponse.setFirstName(userDetails.getFirstName());
        userDetailResponse.setMiddleName(userDetails.getMiddleName());
        userDetailResponse.setLastName(userDetails.getLastName());
        userDetailResponse.setDateOfBirth(userDetails.getDateOfBirth());
        userDetailResponse.setGender(userDetails.getGender());
        userDetailResponse.setAddress1(userDetails.getAddress1());
        userDetailResponse.setAddress2(userDetails.getAddress2());
    //        userDetailResponse.setCity(locationService.mapToCity(userDetails.getCity()));
    //        userDetailResponse.setState(locationService.mapToState(userDetails.getState()));
    //        userDetailResponse.setCountry(locationService.mapToCountry(userDetails.getCountry()));
        return userDetailResponse;
    }




}

package com.manish.WorkHub.service.impl;


import com.manish.WorkHub.dto.LoadUserInfoRequest;
import com.manish.WorkHub.dto.RegisterRequest;
import com.manish.WorkHub.dto.ApiResponse;
import com.manish.WorkHub.exception.UserAlreadyExistsException;
import com.manish.WorkHub.exception.UserNotFoundException;
import com.manish.WorkHub.model.User;
import com.manish.WorkHub.repository.UserRepository;
import com.manish.WorkHub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse registerUser(RegisterRequest registerRequest) {
        if(!userRepository.findByEmail(registerRequest.getEmail()).isEmpty()){
            throw  new UserAlreadyExistsException("User Already Exists with this email id");
        }
        System.out.println(registerRequest.toString());
        User user=modelMapper.map(registerRequest,User.class);
        Object savedUser=userRepository.save(user);
        return modelMapper.map(savedUser, ApiResponse.class);
    }

    @Override
    public ApiResponse loadUserInfo(LoadUserInfoRequest loadUserInfoRequest) {

        User user=userRepository.findByEmail(loadUserInfoRequest.getEmail()).orElseThrow(()-> new UserNotFoundException("User doesn't exist with this email"));
        return modelMapper.map(user, ApiResponse.class);
    }

    @Override
    public List<ApiResponse> getAllUser() {
        List<User> users=userRepository.findAll();
        // Map each User to an ApiResponse using ModelMapper
        return users.stream()
                .map(user -> modelMapper.map(user, ApiResponse.class))
                .collect(Collectors.toList());
    }
}

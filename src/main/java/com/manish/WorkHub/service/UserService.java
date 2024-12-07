package com.manish.WorkHub.service;

import com.manish.WorkHub.dto.LoadUserInfoRequest;
import com.manish.WorkHub.dto.RegisterRequest;
import com.manish.WorkHub.dto.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    ApiResponse registerUser(RegisterRequest registerRequest);

    ApiResponse loadUserInfo(LoadUserInfoRequest loadUserInfoRequest);

    List<ApiResponse> getAllUser();

}

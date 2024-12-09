package com.manish.WorkHub.service;

import com.manish.WorkHub.dto.UserInfoRequest;
import com.manish.WorkHub.dto.RegisterRequest;
import com.manish.WorkHub.dto.ApiResponse;

import java.util.List;


public interface UserService {
    ApiResponse registerUser(RegisterRequest registerRequest);

    ApiResponse loadUserInfo(UserInfoRequest userInfoRequest);

    List<ApiResponse> getAllUser();

}

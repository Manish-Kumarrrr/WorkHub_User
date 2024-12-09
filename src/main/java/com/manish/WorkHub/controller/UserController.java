package com.manish.WorkHub.controller;

import com.manish.WorkHub.dto.UserInfoRequest;
import com.manish.WorkHub.dto.RegisterRequest;
import com.manish.WorkHub.dto.ApiResponse;
import com.manish.WorkHub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        return new ResponseEntity<>(userService.registerUser(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/info")
    public ResponseEntity<ApiResponse> getUserInfo(@RequestBody UserInfoRequest userInfoRequest){
        return new ResponseEntity<>(userService.loadUserInfo(userInfoRequest), HttpStatus.OK);
    }

    @GetMapping("/all")
    public  ResponseEntity<List<ApiResponse>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }

}

package com.manish.WorkHub.controller;

import com.manish.WorkHub.dto.LoadUserInfoRequest;
import com.manish.WorkHub.dto.RegisterRequest;
import com.manish.WorkHub.dto.ApiResponse;
import com.manish.WorkHub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        return new ResponseEntity<>(userService.registerUser(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/loadUserInfo")
    public ResponseEntity<ApiResponse> loadUserInfo(@RequestBody LoadUserInfoRequest loadUserInfoRequest){
        return new ResponseEntity<>(userService.loadUserInfo(loadUserInfoRequest), HttpStatus.OK);
    }

    @GetMapping("/allUser")
    public  ResponseEntity<List<ApiResponse>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }

}

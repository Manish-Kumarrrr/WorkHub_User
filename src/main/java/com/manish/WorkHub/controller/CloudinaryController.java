package com.manish.WorkHub.controller;


import com.manish.WorkHub.service.impl.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/cloudinary")
public class CloudinaryController {
    private final CloudinaryService cloudinaryService;

    @GetMapping("/generate")
    public ResponseEntity<?> generatePreSigned(){
        return new ResponseEntity<>(cloudinaryService.generateUploadSignature(), HttpStatus.CREATED);
    }


}

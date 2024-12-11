package com.manish.WorkHub.service;


public interface OTPService {

    public void generateAndSendOtp(String email);

    public boolean validateOtp(String email, String otp);

}
package com.manish.WorkHub.service.impl;

import com.manish.WorkHub.exception.UserNotFoundException;
import com.manish.WorkHub.model.User;
import com.manish.WorkHub.repository.UserRepository;
import com.manish.WorkHub.service.OTPService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;

    private final JavaMailSender mailSender;


    @Value("${otp.expire.time}")
    private long OTP_EXPIRATION_TIME;


    public void generateAndSendOtp(String email) {
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User doesn't exist with this email: " + email);
        }
        String otp = generateOtp();
        redisTemplate.opsForValue().set(email, otp, OTP_EXPIRATION_TIME, TimeUnit.SECONDS);
        sendEmail(email, otp, user.get());
    }

    public boolean validateOtp(String email, String otp) {
        String storedOtp = redisTemplate.opsForValue().get(email);
        System.out.println(storedOtp);
        return otp.equals(storedOtp);
    }

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private void sendEmail(String toEmail, String otp, User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("WorkHub: Your One-Time Password (OTP)");

        String emailBody = String.format(
                "Dear %s,\n\n" +
                        "We received a request to reset your password for your WorkHub account.\n\n" +
                        "Here is your One-Time Password (OTP): %s\n\n" +
                        "⚠️ Please note:\n" +
                        "- This OTP is valid for 5 minutes.\n" +
                        "- Do not share this code with anyone for your account's safety.\n\n" +
                        "If you did not request this, please ignore this email or contact our support team immediately.\n\n" +
                        "Best Regards,\n" +
                        "The WorkHub Team\n\n",
                user.getName(),otp
        );

        message.setText(emailBody);
        mailSender.send(message);
    }

}

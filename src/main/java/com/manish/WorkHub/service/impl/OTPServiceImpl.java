package com.manish.WorkHub.service.impl;

import com.manish.WorkHub.service.OTPService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {


    private RedisTemplate<String, String> redisTemplate;

    private JavaMailSender mailSender;

    @Value("${otp.expire.time}")
    private static long OTP_EXPIRATION_TIME;

    public void generateAndSendOtp(String email) {
        String otp = generateOtp();
        redisTemplate.opsForValue().set(email, otp, OTP_EXPIRATION_TIME, TimeUnit.SECONDS);
        sendEmail(email, otp);
    }

    public boolean validateOtp(String email, String otp) {
        String storedOtp = redisTemplate.opsForValue().get(email);
        return otp.equals(storedOtp);
    }

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private void sendEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("WorkHub: Your One-Time Password (OTP)");

        String emailBody = String.format(
                "Dear User,\n\n" +
                        "We received a request to reset your password for your WorkHub account.\n\n" +
                        "Here is your One-Time Password (OTP): %s\n\n" +
                        "⚠️ Please note:\n" +
                        "- This OTP is valid for 5 minutes.\n" +
                        "- Do not share this code with anyone for your account's safety.\n\n" +
                        "If you did not request this, please ignore this email or contact our support team immediately.\n\n" +
                        "Best Regards,\n" +
                        "The WorkHub Team\n\n" +
                        "🔗 Visit us: https://workhub.example.com\n" +
                        "📧 Support: support@workhub.example.com",
                otp
        );

        message.setText(emailBody);
        mailSender.send(message);
    }

}

package com.example.CivicConnect;

import java.util.List;

import org.springframework.stereotype.Service;

import sibApi.TransactionalEmailsApi;
import sibModel.*;

import sibApi.Configuration;
import sibApi.auth.ApiKeyAuth;
@Autowired
private JavaMailSender mailSender;

public void sendOtp(String toEmail, String otp) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(toEmail);
    message.setSubject("OTP Verification");
    message.setText("Your OTP is: " + otp);

    mailSender.send(message);
}

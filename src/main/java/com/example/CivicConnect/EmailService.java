package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

public void sendOtp(String toEmail, String otp) {
    try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("OTP Verification");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);

    } catch (Exception e) {
        e.printStackTrace(); // 👈 VERY IMPORTANT
        throw e;
    }
}

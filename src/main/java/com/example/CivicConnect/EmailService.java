package com.example.CivicConnect;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendOtp(String toEmail, String otp) {
        System.out.println("OTP for " + toEmail + " is: " + otp);
    }
}

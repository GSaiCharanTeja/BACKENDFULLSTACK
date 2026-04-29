package com.example.CivicConnect;

import org.springframework.stereotype.Component;

@Component
public class OTPGenerator {

    private int otp;

    public int generateOTP() {
        otp = (int)(Math.random() * 900000) + 100000;
        return otp;
    }

    public int getGeneratedOTP() {
        return otp;
    }
}

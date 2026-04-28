package com.example.CivicConnect;

import org.springframework.stereotype.Component;

@Component
public class OTPGenerator {

    private int generatedOTP;

    public int generateOTP() {
        generatedOTP = (int)(Math.random() * 900000) + 100000;
        return generatedOTP;
    }

    public int getGeneratedOTP() {
        return generatedOTP;
    }
}
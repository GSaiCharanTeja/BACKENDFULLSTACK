package com.example.CivicConnect;

import java.util.List;

import org.springframework.stereotype.Service;

import sibApi.TransactionalEmailsApi;
import sibModel.*;

import sibApi.Configuration;
import sibApi.auth.ApiKeyAuth;

@Service
public class EmailService {

    public void sendOtp(String toEmail, String otp) {

        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");

        apiKey.setApiKey(System.getenv("BREVO_API_KEY")); // 🔥 IMPORTANT

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();

        SendSmtpEmail email = new SendSmtpEmail();

        email.setSender(new SendSmtpEmailSender()
                .email("gavidisaicharanteja@gmail.com")
                .name("CivicConnect"));

        email.setTo(List.of(new SendSmtpEmailTo().email(toEmail)));

        email.setSubject("OTP Verification");
        email.setHtmlContent("<h3>Your OTP is: " + otp + "</h3>");

        try {
            apiInstance.sendTransacEmail(email);
            System.out.println("✅ EMAIL SENT");
        } catch (Exception e) {
            System.out.println("❌ EMAIL FAILED");
            e.printStackTrace();
        }
    }
}

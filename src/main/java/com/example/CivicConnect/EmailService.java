package com.example.CivicConnect;

import org.springframework.stereotype.Service;

import java.util.List;

import sibApi.ApiClient;
import sibApi.Configuration;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

@Service
public class EmailService {

    public void sendOtp(String toEmail, String otp) {

        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(System.getenv("BREVO_API_KEY"));

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();

        SendSmtpEmail email = new SendSmtpEmail();

        email.setSender(new SendSmtpEmailSender()
                .email("gavidisaicharanteja@gmail.com") // MUST BE VERIFIED
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

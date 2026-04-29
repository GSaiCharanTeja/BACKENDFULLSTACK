package com.example.CivicConnect;

import org.springframework.stereotype.Service;

import java.util.List;

import sibApi.*;
import sibModel.*;

@Service
public class EmailService {

    public void sendOtp(String toEmail, String otp) {

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");

        // ⚠️ DO NOT hardcode in real projects
        apiKey.setApiKey(System.getenv("BREVO_API_KEY"));

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();

        SendSmtpEmail email = new SendSmtpEmail();

        email.setSender(new SendSmtpEmailSender()
                .email("gavidisaicharanteja@gmail.com") // must be verified in Brevo
                .name("CivicConnect"));

        email.setTo(List.of(new SendSmtpEmailTo().email(toEmail)));

        email.setSubject("OTP Verification");
        email.setHtmlContent("<h3>Your OTP is: " + otp + "</h3>");

        try {
            apiInstance.sendTransacEmail(email);
            System.out.println("Email sent successfully ✅");
        } catch (Exception e) {
            System.out.println("Email failed ❌");
            e.printStackTrace();
        }
    }
}

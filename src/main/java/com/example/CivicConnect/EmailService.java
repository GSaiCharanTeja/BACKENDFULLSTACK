package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {

    public void sendOtp(String toEmail, String otp) {

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey("YOUR_BREVO_API_KEY");

        TransactionalEmailsApi apiInstance = new TransactionalEmailsApi();

        SendSmtpEmail email = new SendSmtpEmail();

        email.setSender(new SendSmtpEmailSender()
                .email("your_verified_email@gmail.com")
                .name("CivicConnect"));

        email.setTo(List.of(new SendSmtpEmailTo().email(toEmail)));

        email.setSubject("OTP Verification");
        email.setHtmlContent("<h3>Your OTP is: " + otp + "</h3>");

        try {
            apiInstance.sendTransacEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

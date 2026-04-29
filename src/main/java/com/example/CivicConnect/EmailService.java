package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    @Value("${BREVO_SENDER_EMAIL}")
    private String senderEmail;

    public boolean sendOtp(String toEmail, String otp) {
        try {
            String url = "https://api.brevo.com/v3/smtp/email";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("accept", "application/json");
            headers.set("api-key", apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String body = "{\n" +
                    "  \"sender\": {\"email\": \"" + senderEmail + "\"},\n" +
                    "  \"to\": [{\"email\": \"" + toEmail + "\"}],\n" +
                    "  \"subject\": \"OTP Verification\",\n" +
                    "  \"htmlContent\": \"<h3>Your OTP is: " + otp + "</h3>\"\n" +
                    "}";

            HttpEntity<String> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(url, request, String.class);

            System.out.println("Status: " + response.getStatusCode());
            return response.getStatusCode().is2xxSuccessful();

        } catch (Exception e) {
            System.out.println("❌ Email sending failed");
            e.printStackTrace();
            return false;
        }
    }
}

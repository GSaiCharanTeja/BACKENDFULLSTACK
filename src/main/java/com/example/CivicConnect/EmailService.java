package com.example.CivicConnect;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {

    private static final String API_KEY = "YOUR_BREVO_API_KEY";

    public void sendOtp(String toEmail, String otp) {

        try {
            String url = "https://api.brevo.com/v3/smtp/email";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("accept", "application/json");
            headers.set("api-key", API_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String body = "{\n" +
                    "  \"sender\": {\"email\": \"your@email.com\"},\n" +
                    "  \"to\": [{\"email\": \"" + toEmail + "\"}],\n" +
                    "  \"subject\": \"OTP Verification\",\n" +
                    "  \"htmlContent\": \"<h3>Your OTP is: " + otp + "</h3>\"\n" +
                    "}";

            HttpEntity<String> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(url, request, String.class);

            System.out.println("✅ Email sent successfully: " + response.getStatusCode());

        } catch (Exception e) {
            System.out.println("❌ Email sending failed");
            e.printStackTrace();
        }
    }
}

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

        // Build request safely
        Map<String, Object> requestBody = new HashMap<>();

        Map<String, String> sender = new HashMap<>();
        sender.put("email", senderEmail); // MUST be verified

        Map<String, String> to = new HashMap<>();
        to.put("email", toEmail);

        requestBody.put("sender", sender);
        requestBody.put("to", List.of(to));
        requestBody.put("subject", "OTP Verification");
        requestBody.put("htmlContent", "<h3>Your OTP is: " + otp + "</h3>");

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, request, String.class);

        System.out.println("Brevo Response: " + response.getBody());

        return response.getStatusCode().is2xxSuccessful();

    } catch (Exception e) {
        System.out.println("❌ Email sending failed");
        e.printStackTrace();
        return false;
    }
}
}

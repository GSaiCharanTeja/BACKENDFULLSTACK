package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class EmailService {

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    @Value("${BREVO_SENDER_EMAIL}")
    private String senderEmail;

    public boolean sendOtp(String toEmail, String otp) {

        // 🔥 Clean API key (handles Railway quotes issue)
        String cleanApiKey = apiKey.trim();
        if (cleanApiKey.startsWith("\"") && cleanApiKey.endsWith("\"")) {
            cleanApiKey = cleanApiKey.substring(1, cleanApiKey.length() - 1);
        }

        // 🔥 Safety check (important)
        if (!cleanApiKey.startsWith("xkeysib-")) {
            System.out.println("❌ Invalid API Key! Must start with xkeysib-");
            return false;
        }

        try {
            String url = "https://api.brevo.com/v3/smtp/email";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("accept", "application/json");
            headers.set("api-key", cleanApiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 🔥 Request body
            Map<String, Object> requestBody = new HashMap<>();

            Map<String, String> sender = new HashMap<>();
            sender.put("email", senderEmail);
            sender.put("name", "CivicConnect");

            Map<String, String> to = new HashMap<>();
            to.put("email", toEmail);

            requestBody.put("sender", sender);
            requestBody.put("to", List.of(to));
            requestBody.put("subject", "OTP Verification");
            requestBody.put("htmlContent", "<h3>Your OTP is: " + otp + "</h3>");

            HttpEntity<Map<String, Object>> request =
                    new HttpEntity<>(requestBody, headers);

            // 🔥 API Call
            ResponseEntity<String> response =
                    restTemplate.postForEntity(url, request, String.class);

            System.out.println("✅ Email Sent! Status: " + response.getStatusCode());
            return response.getStatusCode().is2xxSuccessful();

        } catch (HttpClientErrorException e) {

            System.out.println("❌ Brevo Error: " + e.getStatusCode());
            System.out.println("❌ Body: " + e.getResponseBodyAsString());
            return false;

        } catch (Exception e) {

            System.out.println("❌ General Error: " + e.getMessage());
            return false;
        }
    }
}

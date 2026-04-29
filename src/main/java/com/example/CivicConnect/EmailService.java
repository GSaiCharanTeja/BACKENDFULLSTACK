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

    @Value("${BREVO_API_KEY:}")
    private String apiKey;

    @Value("${BREVO_SENDER_EMAIL:}")
    private String senderEmail;

    public boolean sendOtp(String toEmail, String otp) {

        // 🔥 Safety checks
        if (apiKey == null || apiKey.isEmpty()) {
            System.out.println("❌ API KEY NOT FOUND");
            return false;
        }

        if (senderEmail == null || senderEmail.isEmpty()) {
            System.out.println("❌ SENDER EMAIL NOT FOUND");
            return false;
        }

        try {
            String url = "https://api.brevo.com/v3/smtp/email";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("accept", "application/json");
            headers.set("api-key", apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 🔥 Request body
            Map<String, Object> requestBody = new HashMap<>();

            Map<String, String> sender = new HashMap<>();
            sender.put("email", senderEmail);
            sender.put("name", "CivicConnect"); // 🔥 IMPORTANT

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

            System.out.println("✅ Status: " + response.getStatusCode());
            System.out.println("✅ Response: " + response.getBody());

            return response.getStatusCode().is2xxSuccessful();

        } catch (HttpClientErrorException e) {

            System.out.println("❌ Brevo Status: " + e.getStatusCode());
            System.out.println("❌ Brevo Error Body: " + e.getResponseBodyAsString());

            return false;

        } catch (Exception e) {

            System.out.println("❌ General Error: " + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }
}

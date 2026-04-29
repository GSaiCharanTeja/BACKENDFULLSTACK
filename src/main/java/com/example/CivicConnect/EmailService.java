package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class EmailService {

    @Value("${BREVO_API_KEY :}")
    private String apiKey;

    @Value("${BREVO_SENDER_EMAIL :}")
    private String senderEmail;

    public boolean sendOtp(String toEmail, String otp) {

    // 🔥 ADD HERE (first lines inside method)
    if (apiKey == null || apiKey.isEmpty()) {
        System.out.println("❌ API KEY NOT FOUND");
        return false;
    }

    if (senderEmail == null || senderEmail.isEmpty()) {
        System.out.println("❌ SENDER EMAIL NOT FOUND");
        return false;
    }
        try {
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

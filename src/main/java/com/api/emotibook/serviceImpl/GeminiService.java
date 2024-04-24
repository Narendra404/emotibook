package com.api.emotibook.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private static String apiKey;

    private final static String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent";

    public static String generateContent(String text) {

        // Create the request body
        String requestBody = String.format("{ \"contents\":[ { \"parts\":[{\"text\": \"%s\"}]}]}", text);

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // Set up the request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Set up RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send the request
        ResponseEntity<String> responseEntity = restTemplate.exchange(GEMINI_API_URL, HttpMethod.POST, requestEntity, String.class);
        System.out.println(responseEntity.getBody());

        // Return the response body
        return responseEntity.getBody();
    }
}

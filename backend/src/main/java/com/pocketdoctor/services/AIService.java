package com.pocketdoctor.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

@Service
public class AIService {

    //@Value("${gemini.api.key}")
    private String geminiApiKey;

    private final RestTemplate restTemplate;

    public AIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getGeminiPrice(String symbol) {
        String url = "https://api.gemini.com/v1/pubticker/" + symbol;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + geminiApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
package com.pocketdoctor.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@Service
public class VertexAIService {

    @Value("${google.cloud.project-id}")
    private String projectId;

    @Value("${google.cloud.location}")
    private String location;

    @Value("${google.cloud.credentials.path}")
    private String credentialsPath;

    private String getAccessToken() throws IOException {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(credentialsPath))
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/cloud-platform"));
        credentials.refreshIfExpired();
        return credentials.getAccessToken().getTokenValue();
    }

    public String generateText(String prompt) throws IOException {
        String url = String.format(
                "https://us-central1-aiplatform.googleapis.com/v1/projects/%s/locations/%s/publishers/google/models/text-bison-001:predict",
                projectId, location
        );

        // Prepare the payload
        JsonObject payload = new JsonObject();
        payload.addProperty("instances", "[{\"content\": \"" + prompt + "\"}]");

        // Set up the request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(payload.toString(), headers);

        // Make the API call
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // Parse the response
        JsonObject jsonResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();
        return jsonResponse.get("predictions").getAsJsonArray().get(0).getAsJsonObject().get("content").getAsString();
    }
}
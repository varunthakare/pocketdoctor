package com.pocketdoctor.Controller;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/chat")
public class ChatbotController {

    @Value("${vertexai.api.endpoint}")
    private String vertexAiEndpoint;

    @Value("${vertexai.project.id}")
    private String projectId;

    @Value("${google.credentials.file}")
    private String credentialsFilePath;

    private static final String MODEL_NAME = "chat-bison";

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody ChatRequest chatRequest) {
        try {
            // Load credentials
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(new FileInputStream(credentialsFilePath))
                    .createScoped("https://www.googleapis.com/auth/cloud-platform");

            credentials.refreshIfExpired();

            // Get access token
            String accessToken = credentials.getAccessToken().getTokenValue();

            // Build URL
            String url = String.format("%s/v1/projects/%s/locations/us-central1/publishers/google/models/%s:predict",
                    vertexAiEndpoint, projectId, MODEL_NAME);

            // Prepare the payload
            JsonObject payload = new JsonObject();
            payload.add("instances", JsonParser.parseString(
                    "[{\"text\": \"" + chatRequest.getMessage() + "\"}]"));

            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            headers.add("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(payload.toString(), headers);

            // Make the API call
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Parse the response
            JsonObject jsonResponse = JsonParser.parseString(response.getBody()).getAsJsonObject();
            String botReply = jsonResponse.get("predictions").getAsJsonArray().get(0).getAsString();

            return ResponseEntity.ok(botReply);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error reading credentials: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error communicating with Vertex AI: " + e.getMessage());
        }
    }

    // DTO for incoming requests
    public static class ChatRequest {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

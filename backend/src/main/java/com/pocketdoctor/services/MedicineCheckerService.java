package com.pocketdoctor.services;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MedicineCheckerService {

    private final RestTemplate restTemplate;

    public MedicineCheckerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getMedicineInformation(String medicineName) {
        String url = "https://api.fda.gov/drug/label.json?search=" + medicineName;
        String response = restTemplate.getForObject(url, String.class);
        return response;  // Process the response as needed
    }
}

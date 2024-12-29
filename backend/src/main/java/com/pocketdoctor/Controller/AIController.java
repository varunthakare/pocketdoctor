package com.pocketdoctor.Controller;


import com.pocketdoctor.services.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    @Autowired
    private AIService aiService;

    // Endpoint to call the Gemini API
    @GetMapping("/gemini/price")
    public String getGeminiPrice(@RequestParam String symbol) {
        return aiService.getGeminiPrice(symbol);
    }
}
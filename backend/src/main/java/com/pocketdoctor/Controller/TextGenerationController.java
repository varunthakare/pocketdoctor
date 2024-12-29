package com.pocketdoctor.Controller;



import com.pocketdoctor.services.VertexAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generate-text")
public class TextGenerationController {

    @Autowired
    private VertexAIService vertexAIService;

    @PostMapping
    public String generateText(@RequestBody String inputText) {
        try {
            return vertexAIService.generateText(inputText);
        } catch (Exception e) {
            return "Error generating text: " + e.getMessage();
        }
    }
}

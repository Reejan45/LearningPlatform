package com.example.learningplatform.controller;

import com.example.learningplatform.model.StudyMaterial;
import com.example.learningplatform.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getRecommendations(@PathVariable Long userId) {
        try {
            List<StudyMaterial> recommendations = recommendationService.getRecommendedMaterials(userId);

            if (recommendations.isEmpty()) {
                return ResponseEntity.ok("No recommendations available for the user.");
            }

            return ResponseEntity.ok(recommendations);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}

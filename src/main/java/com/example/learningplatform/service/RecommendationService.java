package com.example.learningplatform.service;

import com.example.learningplatform.model.StudyMaterial;
import com.example.learningplatform.model.User;
import com.example.learningplatform.model.UserProgress;
import com.example.learningplatform.repository.StudyMaterialRepository;
import com.example.learningplatform.repository.UserProgressRepository;
import com.example.learningplatform.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    private final UserProgressRepository userProgressRepository;
    private final StudyMaterialRepository studyMaterialRepository;
    private final UserService userService;

    public RecommendationService(UserProgressRepository userProgressRepository,
                                 StudyMaterialRepository studyMaterialRepository,
                                 UserService userService) {
        this.userProgressRepository = userProgressRepository;
        this.studyMaterialRepository = studyMaterialRepository;
        this.userService = userService;
    }

    public List<StudyMaterial> getRecommendedMaterials(Long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get all study materials the user has interacted with
        Set<Long> completedMaterialIds = userProgressRepository.findByUser(user).stream()
                .map(progress -> progress.getStudyMaterial().getId())
                .collect(Collectors.toSet());

        // Fetch study materials that the user hasn't interacted with
        return studyMaterialRepository.findAll().stream()
                .filter(material -> !completedMaterialIds.contains(material.getId()))
                .collect(Collectors.toList());
    }
}

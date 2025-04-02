package com.example.learningplatform.service;

import com.example.learningplatform.model.StudyMaterial;
import com.example.learningplatform.model.User;
import com.example.learningplatform.model.UserProgress;
import com.example.learningplatform.repository.StudyMaterialRepository;
import com.example.learningplatform.repository.UserProgressRepository;
import com.example.learningplatform.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProgressService {
    private final UserProgressRepository userProgressRepository;
    private final UserRepository userRepository;
    private final StudyMaterialRepository studyMaterialRepository;

    public UserProgressService(UserProgressRepository userProgressRepository,
                               UserRepository userRepository,
                               StudyMaterialRepository studyMaterialRepository) {
        this.userProgressRepository = userProgressRepository;
        this.userRepository = userRepository;
        this.studyMaterialRepository = studyMaterialRepository;
    }

    public UserProgress saveUserProgress(UserProgress userProgress) {
        return userProgressRepository.save(userProgress);
    }

    public List<UserProgress> getAllUserProgress() {
        return userProgressRepository.findAll();
    }

    public List<UserProgress> getUserProgressByUser(User user) {
        return userProgressRepository.findByUser(user);
    }

    public Optional<UserProgress> getUserProgressById(Long id) {
        return userProgressRepository.findById(id);
    }

    public void deleteUserProgress(Long id) {
        userProgressRepository.deleteById(id);
    }

    public List<UserProgress> addUserProgressForMultipleStudyMaterials(Long userId, List<Long> studyMaterialIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<StudyMaterial> studyMaterials = studyMaterialRepository.findAllById(studyMaterialIds);
        if (studyMaterials.isEmpty()) {
            throw new RuntimeException("No valid StudyMaterials found");
        }

        List<Long> existingMaterialIds = userProgressRepository.findByUser(user)
                .stream()
                .map(up -> up.getStudyMaterial().getId())
                .collect(Collectors.toList());

        // Filter out study materials that are already tracked
        List<UserProgress> newProgressList = studyMaterials.stream()
                .filter(sm -> !existingMaterialIds.contains(sm.getId())) // Avoid duplicates
                .map(sm -> {
                    UserProgress progress = new UserProgress();
                    progress.setUser(user);
                    progress.setStudyMaterial(sm);
                    progress.setProgPercent(0); // Initialize progress to 0
                    return progress;
                })
                .collect(Collectors.toList());

        return userProgressRepository.saveAll(newProgressList); // ✅ Save new progress
    }


    public UserProgress updateProgress(Long progressId, int progressPercentage) {
        UserProgress progress = userProgressRepository.findById(progressId)
                .orElseThrow(() -> new RuntimeException("User Progress not found"));

        progress.setProgPercent(progressPercentage);
        return userProgressRepository.save(progress);
    }

    public List<UserProgress> getUserProgressByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userProgressRepository.findByUser(user);
    }

}

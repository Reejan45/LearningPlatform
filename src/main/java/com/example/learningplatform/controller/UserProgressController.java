package com.example.learningplatform.controller;

import com.example.learningplatform.model.UserProgress;
import com.example.learningplatform.service.UserProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userprogress")
public class UserProgressController {
    private final UserProgressService userProgressService;

    public UserProgressController(UserProgressService userProgressService) {
        this.userProgressService = userProgressService;
    }

    // ✅ Fetch all user progress
    @GetMapping
    public ResponseEntity<List<UserProgress>> getAllUserProgress() {
        return ResponseEntity.ok(userProgressService.getAllUserProgress());
    }

    // ✅ Fetch user progress by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserProgress>> getUserProgressByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userProgressService.getUserProgressByUserId(userId));
    }

    // ✅ Fetch user progress by progress ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProgress> getUserProgressById(@PathVariable Long id) {
        return userProgressService.getUserProgressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Create a single user progress entry
    @PostMapping
    public ResponseEntity<UserProgress> createUserProgress(@RequestBody UserProgress userProgress) {
        return ResponseEntity.ok(userProgressService.saveUserProgress(userProgress));
    }

    // ✅ Add multiple study materials to a user's progress
    @PostMapping("/user/{userId}/materials")
    public ResponseEntity<List<UserProgress>> addUserProgressForMultipleStudyMaterials(
            @PathVariable Long userId, @RequestBody List<Long> studyMaterialIds) {
        return ResponseEntity.ok(userProgressService.addUserProgressForMultipleStudyMaterials(userId, studyMaterialIds));
    }

    @PatchMapping("/{progressId}")
    public ResponseEntity<UserProgress> updateUserProgress(
            @PathVariable Long progressId, @RequestBody int progressPercentage) {
        return ResponseEntity.ok(userProgressService.updateProgress(progressId, progressPercentage));
    }

    // ✅ Delete a user progress entry
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProgress(@PathVariable Long id) {
        userProgressService.deleteUserProgress(id);
        return ResponseEntity.noContent().build();
    }
}

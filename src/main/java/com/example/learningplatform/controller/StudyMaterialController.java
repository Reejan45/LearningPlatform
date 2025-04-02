package com.example.learningplatform.controller;

import com.example.learningplatform.model.StudyMaterial;
import com.example.learningplatform.service.StudyMaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studymaterials")
public class StudyMaterialController {
    private final StudyMaterialService studyMaterialService;

    public StudyMaterialController(StudyMaterialService studyMaterialService) {
        this.studyMaterialService = studyMaterialService;
    }

    @PostMapping
    public ResponseEntity<StudyMaterial> createStudyMaterial(@RequestBody StudyMaterial studyMaterial) {
        return ResponseEntity.ok(studyMaterialService.saveStudyMaterial(studyMaterial));
    }

    @GetMapping
    public ResponseEntity<List<StudyMaterial>> getAllStudyMaterials() {
        return ResponseEntity.ok(studyMaterialService.getAllStudyMaterials());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyMaterial> getStudyMaterialById(@PathVariable Long id) {
        return studyMaterialService.getStudyMaterialById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudyMaterial(@PathVariable Long id) {
        studyMaterialService.deleteStudyMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
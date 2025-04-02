package com.example.learningplatform.service;

import com.example.learningplatform.model.StudyMaterial;
import com.example.learningplatform.repository.StudyMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyMaterialService {
    private final StudyMaterialRepository studyMaterialRepository;

    public StudyMaterialService(StudyMaterialRepository studyMaterialRepository) {
        this.studyMaterialRepository = studyMaterialRepository;
    }

    public StudyMaterial saveStudyMaterial(StudyMaterial studyMaterial) {
        return studyMaterialRepository.save(studyMaterial);
    }

    public List<StudyMaterial> getAllStudyMaterials() {
        return studyMaterialRepository.findAll();
    }

    public Optional<StudyMaterial> getStudyMaterialById(Long id) {
        return studyMaterialRepository.findById(id);
    }

    public void deleteStudyMaterial(Long id) {
        studyMaterialRepository.deleteById(id);
    }
}

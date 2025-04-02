package com.example.learningplatform.repository;

import com.example.learningplatform.model.StudyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudyMaterialRepository extends JpaRepository<StudyMaterial, Long> {
    @Query("SELECT s FROM StudyMaterial s WHERE s.id NOT IN :completedMaterialIds")
    List<StudyMaterial> findUncompletedMaterials(@Param("completedMaterialIds") List<Long> completedMaterialIds);
}

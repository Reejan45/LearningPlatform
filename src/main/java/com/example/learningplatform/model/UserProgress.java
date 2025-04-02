package com.example.learningplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Creates a foreign key in the UserProgress table
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "study_material_id", nullable = false) // Creates a foreign key
    private StudyMaterial studyMaterial;

    private Integer progPercent;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public int getProgPercent() {
        return progPercent;
    }

    public void setProgPercent(int progPercent ) {
        this.progPercent = progPercent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StudyMaterial getStudyMaterial() {
        return studyMaterial;
    }

    public void setStudyMaterial(StudyMaterial studyMaterial) {
        this.studyMaterial = studyMaterial;
    }
    public UserProgress() {
        this.user = user;
        this.studyMaterial = studyMaterial;
        this.progPercent = progPercent;
    }


}

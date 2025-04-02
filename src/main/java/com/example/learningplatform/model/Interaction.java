package com.example.learningplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long studyMaterialId;
    private String type;
    private String comment;
    private Integer rating;
    private LocalDateTime timeStamp;

    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId;
    }
    public Long getStudyMaterialId() {
        return studyMaterialId;
    }
    public String getType() {
        return type;
    }
    public String getComment() {
        return comment;
    }
    public Integer getRating() {
        return rating;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}

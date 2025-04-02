package com.example.learningplatform.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Ensures email is unique
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<UserProgress> progressRecords;

    public List<UserProgress> getProgressRecords() {
        return progressRecords;
    }

    public void setProgressRecords(List<UserProgress> progressRecords) {
        this.progressRecords = progressRecords;
        if (progressRecords != null) {
            for (UserProgress progress : progressRecords) {
                progress.setUser(this); // Ensuring bidirectional consistency
            }
        }
    }

    public Long getId() {
        return id;
    }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}

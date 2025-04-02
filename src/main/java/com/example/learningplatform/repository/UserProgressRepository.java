package com.example.learningplatform.repository;

import com.example.learningplatform.model.User;
import com.example.learningplatform.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    List<UserProgress> findByUser(User user);

}

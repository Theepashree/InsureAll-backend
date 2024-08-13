package com.example.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.insurance.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}


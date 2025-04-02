package com.example.learningplatform.service;

import com.example.learningplatform.model.Interaction;
import com.example.learningplatform.repository.InteractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InteractionService {
    private final InteractionRepository interactionRepository;

    public InteractionService(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }
    public Interaction saveInteraction(Interaction interaction) {
        if (interaction.getUserId() == null || interaction.getStudyMaterialId() == null) {
        throw new IllegalArgumentException("User ID and Study Material ID are required.");
    }
        return interactionRepository.save(interaction);
    }
    public List<Interaction> getAllInteractions() {
        return interactionRepository.findAll();
    }

    public Optional<Interaction> getInteractionById(Long id) {
        return Optional.ofNullable(interactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Interaction not found for ID: " + id)));
    }

    public void deleteInteraction(Long id) {
        if (!interactionRepository.existsById(id)) {
            throw new IllegalArgumentException("Interaction with ID " + id + " not found.");
        }
        interactionRepository.deleteById(id);
    }
}

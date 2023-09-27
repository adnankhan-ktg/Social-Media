package com.app.serviceimpl;

import com.app.model.entity.UserInteraction;
import com.app.repository.UserInteractionRepository;
import com.app.service.UserInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteractionServiceImpl implements UserInteractionService {

    @Autowired
    private UserInteractionRepository interactionRepository;

    @Override
    public UserInteraction likePost(UserInteraction interaction) {
        // Validation and like post logic
        // You can implement other interaction-related methods here
        return interactionRepository.save(interaction);
    }

    // Implement other interaction-related methods
}
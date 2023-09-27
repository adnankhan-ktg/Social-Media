package com.app.service;

import com.app.model.entity.UserInteraction;

public interface UserInteractionService {
    UserInteraction likePost(UserInteraction interaction);
    // Add other interaction-related methods
}
package com.interview.notes.code.months.march24.test21;

import java.util.UUID;

public class UserSession {
    private User user;
    private String sessionId;

    public UserSession(User user) {
        this.user = user;
        this.sessionId = UUID.randomUUID().toString(); // Generate a unique session ID
    }

    // Getters
}
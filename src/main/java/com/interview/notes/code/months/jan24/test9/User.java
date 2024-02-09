package com.interview.notes.code.months.jan24.test9;

import java.util.Collections;
import java.util.List;

public final class User {
    private final String name;
    private final List<String> roles;

    public User(String name, List<String> roles) {
        this.name = name;
        // Defensively copy the list to ensure immutability
        this.roles = Collections.unmodifiableList(roles != null ? List.copyOf(roles) : Collections.emptyList());
    }

    public String getName() {
        return name;
    }

    public List<String> getRoles() {
        // Return an unmodifiable view of the roles list
        return roles;
    }
}

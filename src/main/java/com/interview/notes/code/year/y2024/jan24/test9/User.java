package com.interview.notes.code.year.y2024.jan24.test9;

import java.util.Collections;
import java.util.List;

public record User(String name, List<String> roles) {
    public User(String name, List<String> roles) {
        this.name = name;
        // Defensively copy the list to ensure immutability
        this.roles = Collections.unmodifiableList(roles != null ? List.copyOf(roles) : Collections.emptyList());
    }
}

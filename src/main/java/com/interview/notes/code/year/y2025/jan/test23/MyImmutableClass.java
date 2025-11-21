package com.interview.notes.code.year.y2025.jan.test23;

import java.util.List;

public record MyImmutableClass(int value, String name, List<String> items) {
    public MyImmutableClass(int value, String name, List<String> items) {
        this.value = value;
        this.name = name;
        this.items = List.copyOf(items); // Defensive copy
    }

    @Override
    public List<String> items() {
        return List.copyOf(items); // Return an immutable copy
    }

    // ... other methods (no setters!)
}
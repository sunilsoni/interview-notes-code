package com.interview.notes.code.year.y2025.jan.test23;

import java.util.List;

public final class MyImmutableClass {
    private final int value;
    private final String name;
    private final List<String> items;

    public MyImmutableClass(int value, String name, List<String> items) {
        this.value = value;
        this.name = name;
        this.items = List.copyOf(items); // Defensive copy
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public List<String> getItems() {
        return List.copyOf(items); // Return an immutable copy
    }

    // ... other methods (no setters!)
}
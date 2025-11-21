package com.interview.notes.code.year.y2024.april24.test3;

import java.util.HashMap;
import java.util.Map;

// Manages location strings and their corresponding unique integer IDs.
class LocationRegistry {
    private final Map<String, Integer> locationToId = new HashMap<>();
    private final Map<Integer, String> idToLocation = new HashMap<>();
    private int nextId = 0;

    // Assigns a unique integer ID to a location string.
    public int getIdForLocation(String location) {
        return locationToId.computeIfAbsent(location, loc -> {
            int id = nextId++;
            idToLocation.put(id, loc);
            return id;
        });
    }

    // Retrieves the location string based on its integer ID.
    public String getLocationFromId(int id) {
        return idToLocation.get(id);
    }
}
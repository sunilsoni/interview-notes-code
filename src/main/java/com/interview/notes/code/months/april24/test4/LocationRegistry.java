package com.interview.notes.code.months.april24.test4;

import java.util.HashMap;
import java.util.Map;

// Manages location strings and their corresponding unique integer IDs.
class LocationRegistry {
    private Map<String, Integer> locationToId = new HashMap<>();
    private Map<Integer, String> idToLocation = new HashMap<>();
    private int nextId = 1; // Starting IDs from 1 for clarity

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

    public Map<Integer, String> getAllLocations() {
        return new HashMap<>(idToLocation);
    }
}
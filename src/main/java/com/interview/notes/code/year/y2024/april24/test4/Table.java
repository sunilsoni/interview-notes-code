package com.interview.notes.code.year.y2024.april24.test4;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Table {
    LocationRegistry locationRegistry = new LocationRegistry();
    private Map<Integer, List<Record>> recordsById = new HashMap<>();

    public void insert(int id, float salary, String location) {
        int locationId = locationRegistry.getIdForLocation(location);
        Record newRecord = new Record(salary, locationId);
        recordsById.computeIfAbsent(id, k -> new ArrayList<>()).add(newRecord);
    }

    public List<String> getRecordsAsString(int id) {
        List<Record> records = recordsById.getOrDefault(id, new ArrayList<>());
        List<String> recordStrings = new ArrayList<>();
        for (Record record : records) {
            String location = locationRegistry.getLocationFromId(record.locationId);
            recordStrings.add("ID: " + id + ", Salary: " + record.salary + ", Location: " + location);
        }
        return recordStrings;
    }

    // Provides access to all locations for demonstration purposes.
    public Map<Integer, String> getAllLocations() {
        return locationRegistry.getAllLocations();
    }
}
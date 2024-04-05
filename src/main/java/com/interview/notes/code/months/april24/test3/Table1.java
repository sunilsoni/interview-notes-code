package com.interview.notes.code.months.april24.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Represents a single record in the table.
class Record1 {
    float salary; // Use primitive types for memory efficiency.
    String location; // Location can be interned to save memory.

    public Record1(float salary, String location) {
        this.salary = salary;
        this.location = location.intern(); // Interning to save memory.
    }
}

// The Table class manages the records.
public class Table1 {
    // Maps an ID to a list of records associated with that ID.
    private Map<Integer, List<Record1>> records;

    public Table1() {
        this.records = new HashMap<>();
    }

    // Inserts a new record into the table.
    public void insert(int id, float salary, String location) {
        // Create a new record object.
        Record1 record = new Record1(salary, location);

        // If the ID already exists, add to its list. Otherwise, create a new list.
        records.computeIfAbsent(id, k -> new ArrayList<>()).add(record);
    }

    // Retrieves all records for a given ID.
    public List<Record1> get(int id) {
        return records.getOrDefault(id, new ArrayList<>());
    }
}

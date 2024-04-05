package com.interview.notes.code.months.april24.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private Map<Integer, List<Record>> recordsById = new HashMap<>();
    private LocationRegistry locationRegistry = new LocationRegistry();

    public void insert(int id, float salary, String location) {
        int locationId = locationRegistry.getIdForLocation(location);
        Record newRecord = new Record(salary, locationId);
        recordsById.computeIfAbsent(id, k -> new ArrayList<>()).add(newRecord);
    }

    // Retrieve records and convert location IDs back to strings for presentation or processing.
    public List<String> getRecordsAsString(int id) {
        List<Record> records = recordsById.getOrDefault(id, new ArrayList<>());
        List<String> recordStrings = new ArrayList<>();
        for (Record record : records) {
            String location = locationRegistry.getLocationFromId(record.locationId);
            recordStrings.add("ID: " + id + ", Salary: " + record.salary + ", Location: " + location);
        }
        return recordStrings;
    }


    public static void main(String[] args) {
        // Create the table instance.
        Table table = new Table();

        // Insert some records into the table.
        // Note: In a real application, these strings could be read from a file, user input, etc.
        table.insert(1, 55000.00f, "Boston, MA");
        table.insert(2, 62000.00f, "New York, NY");
        table.insert(1, 57000.00f, "Boston, MA"); // Same location as above to demonstrate ID reuse.
        table.insert(3, 50000.00f, "Chicago, IL");

        // Retrieve and print records for ID 1.
        List<String> recordsForId1 = table.getRecordsAsString(1);
        System.out.println("Records for ID 1:");
        for (String record : recordsForId1) {
            System.out.println(record);
        }

        // Demonstrate retrieval of all unique locations stored (optional, for understanding).
//        System.out.println("\nAll unique locations:");
//        for (Map.Entry<Integer, String> entry : table.locationRegistry.idToLocation.entrySet()) {
//            System.out.printf("ID: %d, Location: %s\n", entry.getKey(), entry.getValue());
//        }
    }
}
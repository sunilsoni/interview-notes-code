package com.interview.notes.code.year.y2024.april24.test3;

import java.util.List;

public class MainApplication {

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

// Ensure the locationRegistry in the Table class is accessible for the demonstration.
// You might need to adjust the visibility of locationRegistry or provide a getter method for it.

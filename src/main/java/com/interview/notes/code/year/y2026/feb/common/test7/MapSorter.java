package com.interview.notes.code.year.y2026.feb.common.test7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSorter {

    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---");

        // --- Test Case 1: User provided example ---
        // Create the input map using Java 9+ Map.of for simplicity
        Map<String, Integer> input1 = new HashMap<>();
        input1.put("One", 1);
        input1.put("two", 2);
        input1.put("tree", 3);

        // Expected output order: tree=3, two=2, One=1
        // We use a LinkedHashMap for expectation because order matters
        Map<String, Integer> expected1 = new LinkedHashMap<>();
        expected1.put("tree", 3);
        expected1.put("two", 2);
        expected1.put("One", 1);

        runTest("User Example", input1, expected1);

        // --- Test Case 2: Empty Map (Edge Case) ---
        runTest("Empty Map", new HashMap<>(), new LinkedHashMap<>());

        // --- Test Case 3: Large Data Input (Performance Check) ---
        // Generate a large map with 100,000 entries
        Map<String, Integer> largeInput = new HashMap<>();
        // Filling map: "Key0":0, "Key1":1, ... "Key99999":99999
        // Logic: checking if the sort puts 99999 first and 0 last
        int size = 100_000;
        for (int i = 0; i < size; i++) {
            largeInput.put("Key" + i, i);
        }

        // Run the logic on large data
        long startTime = System.currentTimeMillis(); // Start timer
        Map<String, Integer> sortedLarge = sortMapByValueDesc(largeInput);
        long endTime = System.currentTimeMillis(); // End timer

        // Manual validation for large data
        // First entry value should be 99999, Last entry value should be 0
        var entries = new ArrayList<>(sortedLarge.entrySet());
        boolean largeTestPass = entries.get(0).getValue() == (size - 1) &&
                entries.get(entries.size() - 1).getValue() == 0;

        String status = largeTestPass ? "PASS" : "FAIL";
        System.out.println("Test Case: Large Data (" + size + " items) -> " + status);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

    // --- Core Solution Method ---
    public static Map<String, Integer> sortMapByValueDesc(Map<String, Integer> map) {
        // Return result immediately; 'var' infers the type automatically
        return map.entrySet() // Get a Set view of the mappings contained in this map
                .stream() // Convert the set of entries into a Stream to process them
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // Sort stream by Value in Descending (reversed) order
                .collect(Collectors.toMap( // Start collecting the sorted results back into a Map
                        Map.Entry::getKey, // Function to extract the Key for the new Map
                        Map.Entry::getValue, // Function to extract the Value for the new Map
                        (oldValue, newValue) -> oldValue, // Merge function (not needed here but required by syntax, handles duplicates)
                        LinkedHashMap::new // IMPORTANT: Use LinkedHashMap to preserve the sorted order we just created
                ));
    }

    // --- Helper Method for Testing ---
    public static void runTest(String testName, Map<String, Integer> input, Map<String, Integer> expected) {
        // Run the sort logic
        Map<String, Integer> actual = sortMapByValueDesc(input);

        // Check if actual result matches expected result
        // For LinkedHashMap, equals() checks contents AND order
        boolean isPass = actual.equals(expected);

        // Print simple PASS/FAIL status
        System.out.println("Test Case: " + testName + " -> " + (isPass ? "PASS" : "FAIL"));

        // If it failed, print details for debugging
        if (!isPass) {
            System.out.println("   Expected: " + expected);
            System.out.println("   Actual:   " + actual);
        }
    }
}
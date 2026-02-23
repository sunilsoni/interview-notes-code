package com.interview.notes.code.year.y2026.feb.Codility.test3;

import java.util.*;

public class UniqueValueMapTest { // The main public class to hold our custom structure and test methods

    public static void main(String[] args) { // Standard main method used instead of JUnit for testing

        int passed = 0; // Initializes a counter to track how many test cases pass

        int total = 0; // Initializes a counter to track the total number of test cases run

        total++; // Increments total counter for Test 1
        var map = new UniqueValueMap<String, String>(); // Uses Java 10+ 'var' keyword to create a new map instance
        boolean t1 = map.put("User1", "Email_A"); // Inserts a standard key and a unique value
        if (t1 && map.get("User1").contains("Email_A")) { System.out.println("Test 1 PASS: Normal Insert"); passed++; } else { System.out.println("Test 1 FAIL"); } // Checks if insert was successful and prints PASS/FAIL

        total++; // Increments total counter for Test 2
        boolean t2 = map.put("User1", "Email_B"); // Inserts the SAME key but a NEW unique value (Duplicate Key allowed)
        if (t2 && map.get("User1").size() == 2) { System.out.println("Test 2 PASS: Duplicate Key Allowed"); passed++; } else { System.out.println("Test 2 FAIL"); } // Verifies the key now holds exactly 2 values

        total++; // Increments total counter for Test 3
        boolean t3 = map.put("User2", "Email_A"); // Tries to insert a NEW key but an EXISTING value ('Email_A')
        if (!t3 && map.get("User2").isEmpty()) { System.out.println("Test 3 PASS: Duplicate Value Blocked"); passed++; } else { System.out.println("Test 3 FAIL"); } // Verifies the insert was blocked and User2 remains empty

        total++; // Increments total counter for Test 4 (Large Data)
        var largeMap = new UniqueValueMap<Integer, Integer>(); // Creates a new map for load testing with Integers
        for (int i = 0; i < 100_000; i++) { // Loops one hundred thousand times to simulate large data
            largeMap.put(1, i); // Puts the same key '1' repeatedly, but with strictly unique values (0 to 99,999)
        } // Closes the loop
        if (largeMap.get(1).size() == 100_000) { System.out.println("Test 4 PASS: Large Data (100k records)"); passed++; } else { System.out.println("Test 4 FAIL"); } // Validates the system easily handled 100k records under one key

        System.out.println("---"); // Prints a separator line for readability
        System.out.println("Total Passed: " + passed + "/" + total); // Prints the final test scorecard

    } // Closes the main method

    static class UniqueValueMap<K, V> { // Custom generic class where K is the Key type and V is the Value type

        private final Map<K, List<V>> map = new HashMap<>(); // Uses a HashMap to link one Key to a List of Values

        private final Set<V> uniqueValues = new HashSet<>(); // Uses a HashSet to guarantee every value is globally unique

        public boolean put(K key, V value) { // Method to add data; returns true on success, false on duplicate value

            if (!uniqueValues.add(value)) { // Tries to add value to Set; if it already exists, Set returns false

                return false; // Rejects the operation and returns false because the value is not unique

            } // Closes the if-statement block

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(value); // Java 8 feature: gets the key's list (or creates a new empty one if missing), then adds the value

            return true; // Returns true indicating the key and unique value were successfully stored

        } // Closes the put method

        public List<V> get(K key) { // Method to retrieve all values associated with a specific key

            return map.getOrDefault(key, List.of()); // Returns the list of values, or a Java 9+ immutable empty list if the key doesn't exist

        } // Closes the get method

        public List<V> getAllValuesUsingStream() { // Extra method demonstrating Java 8 Stream API usage

            return map.values().stream().flatMap(List::stream).toList(); // Streams the lists, flattens them into one stream, and collects to a List (Java 16+ toList)

        } // Closes the stream method

    } // Closes the custom data structure class
    
} // Closes the main test class
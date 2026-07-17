package com.interview.notes.code.year.y2026.july.common.test7;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// Main test class to run without JUnit
public class TimeMapTest {

    // Main method acting as our test runner
    public static void main(String[] args) {

        // 1. Run the standard test case from the problem description
        runTest("Standard Case", () -> {
            // Initialize the data structure
            var tm = new TimeMap();
            // Store "bar" at time 1
            tm.set("foo", "bar", 1);
            // Verify exact time match works
            boolean c1 = tm.get("foo", 1).equals("bar");
            // Verify fallback to previous time works (time 3 should fall back to 1)
            boolean c2 = tm.get("foo", 3).equals("bar");
            // Store new value at time 4
            tm.set("foo", "bar2", 4);
            // Verify fallback between time 4 and 5 works
            boolean c3 = tm.get("foo", 5).equals("bar2");
            // Return true only if all assertions are correct
            return c1 && c2 && c3;
        });

        // 2. Run edge case test
        runTest("Edge Case (No past values)", () -> {
            // Initialize the data structure
            var tm = new TimeMap();
            // Store data starting at time 10
            tm.set("a", "b", 10);
            // Request time 5; since it's before time 10, it should return an empty string
            return tm.get("a", 5).equals("");
        });

        // 3. Run large data scale test
        runTest("Large Data Load", () -> {
            // Initialize the data structure
            var tm = new TimeMap();
            // Loop 100,000 times to simulate high volume data insertion
            for (int i = 1; i <= 100000; i++) {
                // Insert increasing timestamps (2, 4, 6... 200000)
                tm.set("heavy", "val" + i, i * 2);
            }
            // Check a value right in the middle (time 10000 should exactly match val5000)
            boolean exactMatch = tm.get("heavy", 10000).equals("val5000");
            // Check an odd time (10001) which should correctly fall back to time 10000
            boolean floorMatch = tm.get("heavy", 10001).equals("val5000");
            // Return true if both massive lookups pass
            return exactMatch && floorMatch;
        });
    }

    // Helper method to execute test logic and print PASS/FAIL concisely
    private static void runTest(String testName, java.util.function.BooleanSupplier testLogic) {
        // Execute the provided lambda function which contains the test steps
        boolean passed = testLogic.getAsBoolean();
        // Print the test name and its result (PASS or FAIL) to the console
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
    }

    // Inner class representing our data structure
    static class TimeMap {
        // Declare a map that links a String key to a TreeMap (which keeps timestamps sorted)
        private final Map<String, TreeMap<Integer, String>> map;

        // Constructor to initialize our data structure when instantiated
        public TimeMap() {
            // Initialize the outer map as a HashMap for fast O(1) key lookups
            this.map = new HashMap<>();
        }

        // Method to store the key, value, and timestamp
        public void set(String key, String value, int timestamp) {
            // computeIfAbsent checks if the key exists; if not, creates a new TreeMap. Then we put the data in it.
            this.map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        // Method to retrieve the closest past value for a given timestamp
        public String get(String key, int timestamp) {
            // Use 'var' (Java 10+) to concisely grab the inner TreeMap for this specific key
            var tree = this.map.get(key);

            // If the key was never recorded in our map, return the required empty string
            if (tree == null) return "";

            // floorEntry finds the largest timestamp less than or equal to the target timestamp in O(log N) time
            var entry = tree.floorEntry(timestamp);

            // If entry is null (requested time is too early), return ""; otherwise return the found value
            return entry == null ? "" : entry.getValue();
        }
    }
}
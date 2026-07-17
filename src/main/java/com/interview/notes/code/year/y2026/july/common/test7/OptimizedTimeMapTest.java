package com.interview.notes.code.year.y2026.july.common.test7;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// Main test class to run without JUnit
public class OptimizedTimeMapTest {

    // Main method to verify both the O(1) and O(log N) paths work perfectly
    public static void main(String[] args) {

        runTest("99% Fast Path (Exact Match)", () -> {
            var tm = new TimeMap();
            tm.set("foo", "bar", 10);
            tm.set("foo", "bar2", 20);

            // This will hit the exactMap in O(1)
            return tm.get("foo", 20).equals("bar2");
        });

        runTest("1% Slow Path (Fallback Match)", () -> {
            var tm = new TimeMap();
            tm.set("foo", "bar", 10);
            tm.set("foo", "bar2", 20);

            // Exact timestamp 25 doesn't exist. This misses the exactMap and hits the treeMap in O(log N)
            return tm.get("foo", 25).equals("bar2");
        });

        runTest("Edge Case (No past values)", () -> {
            var tm = new TimeMap();
            tm.set("foo", "bar", 10);

            // Timestamp 5 is too early. Misses exact map, floorEntry returns null, returns ""
            return tm.get("foo", 5).equals("");
        });
    }

    // Helper method to execute test logic and print PASS/FAIL concisely
    private static void runTest(String testName, java.util.function.BooleanSupplier testLogic) {
        boolean passed = testLogic.getAsBoolean();
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
    }

    // Inner class representing our optimized data structure
    static class TimeMap {

        // FAST PATH: HashMap to handle the 99% of exact timestamp queries in O(1) time
        private final Map<String, Map<Integer, String>> exactMap;

        // SLOW PATH: TreeMap to handle the 1% of nearest-past fallback queries in O(log N) time
        private final Map<String, TreeMap<Integer, String>> treeMap;

        // Constructor to initialize both maps
        public TimeMap() {
            // Initialize both outer maps as HashMaps for O(1) key lookups
            this.exactMap = new HashMap<>();
            this.treeMap = new HashMap<>();
        }

        // Method to store the data in both maps simultaneously
        public void set(String key, String value, int timestamp) {
            // 1. Write to Fast Path: Create inner HashMap if missing, then store exact timestamp
            this.exactMap.computeIfAbsent(key, k -> new HashMap<>()).put(timestamp, value);

            // 2. Write to Slow Path: Create inner TreeMap if missing, then store for sorting
            this.treeMap.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
        }

        // Method to retrieve the value, heavily optimized for exact matches
        public String get(String key, int timestamp) {

            // --- 99% USE CASE: FAST PATH ---
            // Attempt to fetch the inner map of exact timestamps (use empty map if key doesn't exist to avoid NPE)
            var exactHits = this.exactMap.getOrDefault(key, Collections.emptyMap());
            // Try to get the exact timestamp in O(1) time
            String exactValue = exactHits.get(timestamp);

            // If the exact timestamp exists, we immediately return it (O(1) achieved!)
            if (exactValue != null) {
                return exactValue;
            }

            // --- 1% USE CASE: SLOW PATH ---
            // If we are here, it means the exact timestamp missed. We fall back to the TreeMap.
            var tree = this.treeMap.get(key);

            // If the key was never recorded at all, return empty string
            if (tree == null) return "";

            // Use binary search (floorEntry) to find the largest timestamp <= requested timestamp in O(log N)
            var entry = tree.floorEntry(timestamp);

            // Return the found value, or empty string if requested time is earlier than all records
            return entry == null ? "" : entry.getValue();
        }
    }
}
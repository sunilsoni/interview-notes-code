package com.interview.notes.code.year.y2024.dec24.oracle.test3;

/*
Implement a Map that remembers a time when you set a value.

Implement a Map that remembers a time when you set a value. put (String key, String value, Long timestamp)
get (String key, Long timestamp)
put("a", "apple", 2)
get("a", 0) →> null get ("a", 3) -> "apple"

if not avaiable then find nearest one object and return

 */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeAwareMap {
    private final Map<String, TreeMap<Long, TimestampedValue>> storage;

    public TimeAwareMap() {
        storage = new HashMap<>();
    }

    public static void main(String[] args) {
        TimeAwareMap map = new TimeAwareMap();
        boolean allTestsPassed = true;

        System.out.println("Starting TimeAwareMap Tests...\n");

        // Test Suite 1: Basic Operations
        try {
            System.out.println("Test Suite 1: Basic Operations");
            map.put("a", "apple", 2);
            allTestsPassed &= testCase(map.get("a", 0) == null,
                    "Test 1.1: Get value before timestamp");
            allTestsPassed &= testCase(map.get("a", 3).equals("apple"),
                    "Test 1.2: Get value after timestamp");
            allTestsPassed &= testCase(map.get("a", 2).equals("apple"),
                    "Test 1.3: Get value at exact timestamp");
        } catch (Exception e) {
            System.out.println("Test Suite 1 failed with exception: " + e.getMessage());
            allTestsPassed = false;
        }

        // Test Suite 2: Multiple Updates
        try {
            System.out.println("\nTest Suite 2: Multiple Updates");
            map.put("b", "banana", 1);
            map.put("b", "berry", 3);
            map.put("b", "blackberry", 5);

            allTestsPassed &= testCase(map.get("b", 0) == null,
                    "Test 2.1: Get before any value");
            allTestsPassed &= testCase(map.get("b", 2).equals("banana"),
                    "Test 2.2: Get first value");
            allTestsPassed &= testCase(map.get("b", 4).equals("berry"),
                    "Test 2.3: Get second value");
            allTestsPassed &= testCase(map.get("b", 6).equals("blackberry"),
                    "Test 2.4: Get latest value");
        } catch (Exception e) {
            System.out.println("Test Suite 2 failed with exception: " + e.getMessage());
            allTestsPassed = false;
        }

        // Test Suite 3: Edge Cases
        try {
            System.out.println("\nTest Suite 3: Edge Cases");
            allTestsPassed &= testCase(map.get("nonexistent", 1) == null,
                    "Test 3.1: Nonexistent key");
            allTestsPassed &= testCase(map.get(null, 1) == null,
                    "Test 3.2: Null key get");

            try {
                map.put(null, "value", 1);
                allTestsPassed = false;
                System.out.println("Test 3.3: Should throw exception for null key - FAIL");
            } catch (IllegalArgumentException e) {
                System.out.println("Test 3.3: Null key put - PASS");
            }

            try {
                map.put("key", null, 1);
                allTestsPassed = false;
                System.out.println("Test 3.4: Should throw exception for null value - FAIL");
            } catch (IllegalArgumentException e) {
                System.out.println("Test 3.4: Null value put - PASS");
            }
        } catch (Exception e) {
            System.out.println("Test Suite 3 failed with exception: " + e.getMessage());
            allTestsPassed = false;
        }

        // Test Suite 4: Performance Test with Large Dataset
        try {
            System.out.println("\nTest Suite 4: Performance Test");
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 100000; i++) {
                map.put("key" + i, "value" + i, i);
            }

            boolean largeDataTestPassed = true;
            for (int i = 0; i < 1000; i++) {
                int randomIndex = (int) (Math.random() * 100000);
                String expected = "value" + randomIndex;
                String actual = map.get("key" + randomIndex, randomIndex);
                if (!expected.equals(actual)) {
                    largeDataTestPassed = false;
                    break;
                }
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Performance Test completed in: " + (endTime - startTime) + "ms");
            allTestsPassed &= testCase(largeDataTestPassed,
                    "Test 4.1: Large dataset operations");
        } catch (Exception e) {
            System.out.println("Test Suite 4 failed with exception: " + e.getMessage());
            allTestsPassed = false;
        }

        // Final Results
        System.out.println("\nFinal Result: " +
                (allTestsPassed ? "ALL TESTS PASSED ✓" : "SOME TESTS FAILED ✗"));
    }

    private static boolean testCase(boolean condition, String testName) {
        System.out.println(testName + ": " + (condition ? "PASS" : "FAIL"));
        return condition;
    }

    public void put(String key, String value, long timestamp) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and value cannot be null");
        }

        TimestampedValue timestampedValue = new TimestampedValue(value, timestamp);
        storage.computeIfAbsent(key, k -> new TreeMap<>())
                .put(timestamp, timestampedValue);
    }

    public String get(String key, long timestamp) {
        if (key == null) {
            return null;
        }

        TreeMap<Long, TimestampedValue> timeline = storage.get(key);
        if (timeline == null) {
            return null;
        }

        Map.Entry<Long, TimestampedValue> entry = timeline.floorEntry(timestamp);
        return entry != null ? entry.getValue().value : null;
    }

    private record TimestampedValue(String value, long timestamp) {

        @Override
        public String toString() {
            return "TimestampedValue{value='" + value + "', timestamp=" + timestamp + "}";
        }
    }
}

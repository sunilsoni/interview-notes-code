package com.interview.notes.code.year.y2024.oct24.test16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Question: Design and implement a data structure called "TimeMap" with the following operations:
set(string key, string value, int timestamp): Stores the key-value pair at the given timestamp. get(string key, int timestamp): Returns the value associated with the key at the most recent timestamp less than or equal to the given timestamp. If there's no value for the key at or before the given timestamp, return an empty string.
Note: The timestamp is always strictly increasing for successive calls to set for the same key and there are no duplicate
key value pairs.
TimeMap timeMap = new TimeMap();
timeMap.set("'foo"
', "bar"
', 1);1
timeMap. get ("foo" timeMap get ("'foo"
: 3): / returns "bar™
// returns "bar"
timeMap.set("'foo"
', "bar2", 4);
timeMap. get("foo", 4); // returns "bar2" timeMap. get ("'foo"
, 5);
// returns "bar2"
timeMap.get ("foo", 0); // returns ""
 */

/**
 * TimeMap data structure that stores key-value pairs along with timestamps.
 * It allows retrieval of the value for a given key at the most recent timestamp
 * less than or equal to a specified timestamp.
 */
public class TimeMap {

    // HashMap to store the key to list of TimeValuePairs mapping
    private Map<String, List<TimeValuePair>> map;

    /**
     * Initialize the TimeMap data structure
     */
    public TimeMap() {
        map = new HashMap<>();
    }

    /**
     * Main method to run test cases and verify the correctness of the TimeMap implementation.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();

        // List of test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1
        testCases.add(new TestCase("Test Case 1: Basic set and get",
                () -> {
                    timeMap.set("foo", "bar", 1);
                },
                () -> timeMap.get("foo", 1).equals("bar")));

        // Test Case 2
        testCases.add(new TestCase("Test Case 2: Get with timestamp greater than set",
                () -> {
                    // Already set in Test Case 1
                },
                () -> timeMap.get("foo", 3).equals("bar")));

        // Test Case 3
        testCases.add(new TestCase("Test Case 3: Overwrite with new timestamp",
                () -> {
                    timeMap.set("foo", "bar2", 4);
                },
                () -> timeMap.get("foo", 4).equals("bar2")));

        // Test Case 4
        testCases.add(new TestCase("Test Case 4: Get with timestamp greater than latest set",
                () -> {
                    // Already set in Test Case 3
                },
                () -> timeMap.get("foo", 5).equals("bar2")));

        // Test Case 5
        testCases.add(new TestCase("Test Case 5: Get with timestamp earlier than any set",
                () -> {
                    // No operation needed
                },
                () -> timeMap.get("foo", 0).equals("")));

        // Test Case 6: Non-existent key
        testCases.add(new TestCase("Test Case 6: Get non-existent key",
                () -> {
                    // No operation needed
                },
                () -> timeMap.get("unknown", 1).equals("")));

        // Test Case 7: Multiple keys
        testCases.add(new TestCase("Test Case 7: Multiple keys handling",
                () -> {
                    timeMap.set("key1", "value1", 10);
                    timeMap.set("key2", "value2", 20);
                },
                () ->
                        timeMap.get("key1", 10).equals("value1") &&
                                timeMap.get("key2", 20).equals("value2") &&
                                timeMap.get("key1", 15).equals("value1") &&
                                timeMap.get("key2", 25).equals("value2")
        ));

        // Test Case 8: Large data input
        testCases.add(new TestCase("Test Case 8: Large data input",
                () -> {
                    // Adding 100,000 entries for key "large"
                    for (int i = 1; i <= 100000; i++) {
                        timeMap.set("large", "val" + i, i);
                    }
                },
                () -> {
                    // Perform some get operations
                    boolean pass = true;
                    pass &= timeMap.get("large", 50000).equals("val50000");
                    pass &= timeMap.get("large", 100000).equals("val100000");
                    pass &= timeMap.get("large", 100001).equals("val100000");
                    pass &= timeMap.get("large", 1).equals("val1");
                    pass &= timeMap.get("large", 0).equals("");
                    return pass;
                }));

        // Run all test cases
        for (TestCase testCase : testCases) {
            try {
                testCase.setup.run();
                boolean result = testCase.test.run();
                System.out.println(testCase.name + ": " + (result ? "PASS" : "FAIL"));
            } catch (Exception e) {
                System.out.println(testCase.name + ": FAIL (Exception occurred)");
                e.printStackTrace();
            }
        }
    }

    /**
     * Stores the key-value pair along with the given timestamp.
     *
     * @param key       The key to store
     * @param value     The value associated with the key
     * @param timestamp The timestamp at which the key-value pair is stored
     */
    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new TimeValuePair(timestamp, value));
    }

    /**
     * Retrieves the value associated with the key at the most recent timestamp
     * less than or equal to the provided timestamp.
     *
     * @param key       The key to retrieve
     * @param timestamp The timestamp at which to retrieve the value
     * @return The value associated with the key at the given timestamp, or an empty string if none exists
     */
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        List<TimeValuePair> list = map.get(key);
        int left = 0;
        int right = list.size() - 1;
        String result = "";

        // Binary search to find the rightmost timestamp <= given timestamp
        while (left <= right) {
            int mid = left + (right - left) / 2;
            TimeValuePair pair = list.get(mid);
            if (pair.timestamp == timestamp) {
                return pair.value;
            } else if (pair.timestamp < timestamp) {
                result = pair.value;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    /**
     * Functional interface for test conditions.
     */
    @FunctionalInterface
    private interface TestCondition {
        boolean run();
    }

    // Inner class to represent a pair of timestamp and value
    private static class TimeValuePair {
        int timestamp;
        String value;

        TimeValuePair(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    /**
     * Inner class to represent a test case.
     */
    private static class TestCase {
        String name;
        Runnable setup;
        TestCondition test;

        TestCase(String name, Runnable setup, TestCondition test) {
            this.name = name;
            this.setup = setup;
            this.test = test;
        }
    }
}
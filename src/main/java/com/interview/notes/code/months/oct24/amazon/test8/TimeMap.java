package com.interview.notes.code.months.oct24.amazon.test8;

import java.util.*;
import java.util.Map.Entry;

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

    // HashMap to store the key to TreeMap of timestamp-value mappings
    private Map<String, TreeMap<Integer, String>> map;

    /**
     * Initialize the TimeMap data structure
     */
    public TimeMap() {
        map = new HashMap<>();
        System.out.println("Initialized TimeMap data structure.");
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
                    timeMap.set("foo", "bar", 1);  // Set key "foo" with value "bar" at timestamp 1
                },
                () -> timeMap.get("foo", 1).equals("bar")));  // Get value for key "foo" at timestamp 1

        // Test Case 2
        testCases.add(new TestCase("Test Case 2: Get with timestamp greater than set",
                () -> {
                    // Already set in Test Case 1
                },
                () -> timeMap.get("foo", 3).equals("bar")));  // Get value for key "foo" at timestamp 3 (should return "bar")

        // Test Case 3
        testCases.add(new TestCase("Test Case 3: Overwrite with new timestamp",
                () -> {
                    timeMap.set("foo", "bar2", 4);  // Set key "foo" with value "bar2" at timestamp 4
                },
                () -> timeMap.get("foo", 4).equals("bar2")));  // Get value for key "foo" at timestamp 4 (should return "bar2")

        // Test Case 4
        testCases.add(new TestCase("Test Case 4: Get with timestamp greater than latest set",
                () -> {
                    // Already set in Test Case 3
                },
                () -> timeMap.get("foo", 5).equals("bar2")));  // Get value for key "foo" at timestamp 5 (should return "bar2")

        // Test Case 5
        testCases.add(new TestCase("Test Case 5: Get with timestamp earlier than any set",
                () -> {
                    // No operation needed
                },
                () -> timeMap.get("foo", 0).equals("")));  // Get value for key "foo" at timestamp 0 (should return empty string)

        // Test Case 6: Non-existent key
        testCases.add(new TestCase("Test Case 6: Get non-existent key",
                () -> {
                    // No operation needed
                },
                () -> timeMap.get("unknown", 1).equals("")));  // Get value for non-existent key "unknown" (should return empty string)

        // Test Case 7: Multiple keys
        testCases.add(new TestCase("Test Case 7: Multiple keys handling",
                () -> {
                    timeMap.set("key1", "value1", 10);  // Set key "key1" with value "value1" at timestamp 10
                    timeMap.set("key2", "value2", 20);  // Set key "key2" with value "value2" at timestamp 20
                },
                () ->
                        timeMap.get("key1", 10).equals("value1") &&  // Get value for key "key1" at timestamp 10
                                timeMap.get("key2", 20).equals("value2") &&  // Get value for key "key2" at timestamp 20
                                timeMap.get("key1", 15).equals("value1") &&  // Get value for key "key1" at timestamp 15
                                timeMap.get("key2", 25).equals("value2")     // Get value for key "key2" at timestamp 25
        ));

        // Test Case 8: Large data input
        testCases.add(new TestCase("Test Case 8: Large data input",
                () -> {
                    // Adding 100,000 entries for key "large"
                    for (int i = 1; i <= 100000; i++) {
                        timeMap.set("large", "val" + i, i);  // Set key "large" with value "val" + i at timestamp i
                    }
                },
                () -> {
                    // Perform some get operations to validate correctness
                    boolean pass = true;
                    pass &= timeMap.get("large", 50000).equals("val50000");  // Get value for key "large" at timestamp 50000
                    pass &= timeMap.get("large", 100000).equals("val100000");  // Get value for key "large" at timestamp 100000
                    pass &= timeMap.get("large", 100001).equals("val100000");  // Get value for key "large" at timestamp 100001 (should return the latest)
                    pass &= timeMap.get("large", 1).equals("val1");
                    return pass;
                }));

        // Test Case 9: Get last n values
        testCases.add(new TestCase("Test Case 9: Get last n values",
                () -> {
                    timeMap.set("foo", "bar3", 6);  // Set key "foo" with value "bar3" at timestamp 6
                },
                () -> {
                    List<String> lastValues = timeMap.getLastNValues("foo", 6, 2);  // Get last 2 values for key "foo" at timestamp 6
                    return lastValues.equals(Arrays.asList("bar3", "bar2"));
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
        System.out.println("Setting key: " + key + ", value: " + value + ", timestamp: " + timestamp);
        map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
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
        System.out.println("Getting value for key: " + key + ", timestamp: " + timestamp);
        // If the key does not exist in the map, return an empty string
        if (!map.containsKey(key)) {
            System.out.println("Key not found.");
            return "";
        }

        TreeMap<Integer, String> treeMap = map.get(key);
        Entry<Integer, String> entry = treeMap.floorEntry(timestamp);

        if (entry == null) {
            System.out.println("No suitable timestamp found.");
            return "";
        }

        System.out.println("Returning result: " + entry.getValue());
        return entry.getValue();
    }

    /**
     * Retrieves the last n values for a given key, up to a specified timestamp.
     *
     * @param key       The key to retrieve
     * @param timestamp The timestamp at which to start retrieval
     * @param n         The number of values to retrieve
     * @return A list of the last n values, or fewer if not enough values exist
     */
    public List<String> getLastNValues(String key, int timestamp, int n) {
        System.out.println("Getting last " + n + " values for key: " + key + ", timestamp: " + timestamp);
        List<String> result = new ArrayList<>();
        if (!map.containsKey(key)) {
            System.out.println("Key not found.");
            return result;
        }

        TreeMap<Integer, String> treeMap = map.get(key);
        NavigableMap<Integer, String> subMap = treeMap.headMap(timestamp, true).descendingMap();

        for (String value : subMap.values()) {
            if (n == 0) break;
            result.add(value);
            n--;
        }

        System.out.println("Returning last " + result.size() + " values: " + result);
        return result;
    }

    /**
     * Functional interface for test conditions.
     */
    @FunctionalInterface
    private interface TestCondition {
        boolean run();  // Method to run the test condition
    }

    /**
     * Inner class to represent a test case.
     */
    private static class TestCase {
        String name;  // Name of the test case
        Runnable setup;  // Setup code to run before the test
        TestCondition test;  // Condition to validate the test result

        TestCase(String name, Runnable setup, TestCondition test) {
            this.name = name;
            this.setup = setup;
            this.test = test;
        }
    }
}

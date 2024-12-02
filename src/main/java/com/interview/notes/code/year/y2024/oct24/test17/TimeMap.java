//package com.interview.notes.code.months.oct24.test17;
//
//import java.util.*;
//import java.util.Map.Entry;
//
/// **
// * TimeMap data structure that stores key-value pairs along with timestamps.
// * It allows retrieval of the value for a given key at the most recent timestamp
// * less than or equal to a specified timestamp.
// */
//public class TimeMap {
//
//    // Inner class to represent a pair of timestamp and value
//    private static class TimeValuePair {
//        int timestamp;
//        String value;
//
//        TimeValuePair(int timestamp, String value) {
//            this.timestamp = timestamp;
//            this.value = value;
//        }
//    }
//
//    // HashMap to store the key to list of TimeValuePairs mapping
//    private Map<String, List<TimeValuePair>> map;
//
//    /** Initialize the TimeMap data structure */
//    public TimeMap() {
//        map = new HashMap<>();
//        System.out.println("Initialized TimeMap data structure.");
//    }
//
//    /**
//     * Stores the key-value pair along with the given timestamp.
//     *
//     * @param key       The key to store
//     * @param value     The value associated with the key
//     * @param timestamp The timestamp at which the key-value pair is stored
//     */
//    public void set(String key, String value, int timestamp) {
//        System.out.println("Setting key: " + key + ", value: " + value + ", timestamp: " + timestamp);
//        // If the key does not exist, create a new list. Then add the new TimeValuePair to the list.
//        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new TimeValuePair(timestamp, value));
//    }
//
//    /**
//     * Retrieves the value associated with the key at the most recent timestamp
//     * less than or equal to the provided timestamp.
//     *
//     * @param key       The key to retrieve
//     * @param timestamp The timestamp at which to retrieve the value
//     * @return The value associated with the key at the given timestamp, or an empty string if none exists
//     */
//    public String get(String key, int timestamp) {
//        System.out.println("Getting value for key: " + key + ", timestamp: " + timestamp);
//        // If the key does not exist in the map, return an empty string
//        if (!map.containsKey(key)) {
//            System.out.println("Key not found.");
//            return "";
//        }
//
//        List<TimeValuePair> list = map.get(key);
//        int left = 0;
//        int right = list.size() - 1;
//        String result = "";
//
//        // Binary search to find the rightmost timestamp <= given timestamp
//        while (left <= right) {
//            int mid = left + (right - left) / 2;  // Calculate mid-point to avoid overflow
//            TimeValuePair pair = list.get(mid);
//            System.out.println("Checking mid index: " + mid + ", timestamp: " + pair.timestamp + ", value: " + pair.value);
//            if (pair.timestamp == timestamp) {
//                // If the timestamp matches exactly, return the value
//                System.out.println("Exact match found at mid index: " + mid);
//                return pair.value;
//            } else if (pair.timestamp < timestamp) {
//                // If the current timestamp is less than the given timestamp,
//                // store the value and move to the right half
//                result = pair.value;
//                left = mid + 1;
//                System.out.println("Moving right. New left index: " + left);
//            } else {
//                // If the current timestamp is greater, move to the left half
//                right = mid - 1;
//                System.out.println("Moving left. New right index: " + right);
//            }
//        }
//
//        // Return the found value or an empty string if no suitable timestamp was found
//        System.out.println("Returning result: " + result);
//        return result;
//    }
//
//    /**
//     * Main method to run test cases and verify the correctness of the TimeMap implementation.
//     *
//     * @param args Command-line arguments (not used)
//     */
//    public static void main(String[] args) {
//        TimeMap timeMap = new TimeMap();
//
//        // List of test cases
//        List<TestCase> testCases = new ArrayList<>();
//
//        // Test Case 1
//        testCases.add(new TestCase("Test Case 1: Basic set and get",
//                () -> {
//                    timeMap.set("foo", "bar", 1);  // Set key "foo" with value "bar" at timestamp 1
//                },
//                () -> timeMap.get("foo", 1).equals("bar")));  // Get value for key "foo" at timestamp 1
//
//        // Test Case 2
//        testCases.add(new TestCase("Test Case 2: Get with timestamp greater than set",
//                () -> {
//                    // Already set in Test Case 1
//                },
//                () -> timeMap.get("foo", 3).equals("bar")));  // Get value for key "foo" at timestamp 3 (should return "bar")
//
//        // Test Case 3
//        testCases.add(new TestCase("Test Case 3: Overwrite with new timestamp",
//                () -> {
//                    timeMap.set("foo", "bar2", 4);  // Set key "foo" with value "bar2" at timestamp 4
//                },
//                () -> timeMap.get("foo", 4).equals("bar2")));  // Get value for key "foo" at timestamp 4 (should return "bar2")
//
//        // Test Case 4
//        testCases.add(new TestCase("Test Case 4: Get with timestamp greater than latest set",
//                () -> {
//                    // Already set in Test Case 3
//                },
//                () -> timeMap.get("foo", 5).equals("bar2")));  // Get value for key "foo" at timestamp 5 (should return "bar2")
//
//        // Test Case 5
//        testCases.add(new TestCase("Test Case 5: Get with timestamp earlier than any set",
//                () -> {
//                    // No operation needed
//                },
//                () -> timeMap.get("foo", 0).equals("")));  // Get value for key "foo" at timestamp 0 (should return empty string)
//
//        // Test Case 6: Non-existent key
//        testCases.add(new TestCase("Test Case 6: Get non-existent key",
//                () -> {
//                    // No operation needed
//                },
//                () -> timeMap.get("unknown", 1).equals("")));  // Get value for non-existent key "unknown" (should return empty string)
//
//        // Test Case 7: Multiple keys
//        testCases.add(new TestCase("Test Case 7: Multiple keys handling",
//                () -> {
//                    timeMap.set("key1", "value1", 10);  // Set key "key1" with value "value1" at timestamp 10
//                    timeMap.set("key2", "value2", 20);  // Set key "key2" with value "value2" at timestamp 20
//                },
//                () ->
//                    timeMap.get("key1", 10).equals("value1") &&  // Get value for key "key1" at timestamp 10
//                    timeMap.get("key2", 20).equals("value2") &&  // Get value for key "key2" at timestamp 20
//                    timeMap.get("key1", 15).equals("value1") &&  // Get value for key "key1" at timestamp 15
//                    timeMap.get("key2", 25).equals("value2")     // Get value for key "key2" at timestamp 25
//        ));
//
//        // Test Case 8: Large data input
//        testCases.add(new TestCase("Test Case 8: Large data input",
//                () -> {
//                    // Adding 100,000 entries for key "large"
//                    for (int i = 1; i <= 100000; i++) {
//                        timeMap.set("large", "val" + i, i);  // Set key "large" with value "val" + i at timestamp i
//                    }
//                },
//                () -> {
//                    // Perform some get operations to validate correctness
//                    boolean pass = true;
//                    pass &= timeMap.get("large", 50000).equals("val50000");  // Get value for key "large" at timestamp 50000
//                    pass &= timeMap.get("large", 100000).equals("val100000");  // Get value for key "large" at timestamp 100000
//                    pass &= timeMap.get("large", 100001).equals("val100000");  // Get value for key "large" at timestamp 100001 (should return the latest)
//                    pass &= timeMap.get("large", 1).equals("val1");

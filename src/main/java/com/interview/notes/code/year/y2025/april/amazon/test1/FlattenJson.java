package com.interview.notes.code.year.y2025.april.amazon.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FlattenJson {

    /**
     * Flattens a nested Map (representing a JSON) using dot notation for nested keys.
     *
     * @param map    The input map to flatten.
     * @param prefix The current prefix for keys (empty string for top-level).
     * @return A flattened map with dot notation keys.
     */
    public static Map<String, String> flattenMap(Map<String, Object> map, String prefix) {
        Map<String, String> result = new HashMap<>();
        map.forEach((key, value) -> {
            String fullKey = prefix.isEmpty() ? key : prefix + "." + key;
            if (value instanceof Map) {
                // Recursive call for nested maps
                @SuppressWarnings("unchecked")
                Map<String, Object> nestedMap = (Map<String, Object>) value;
                result.putAll(flattenMap(nestedMap, fullKey));
            } else {
                // Convert the value to string, handling nulls appropriately
                result.put(fullKey, value == null ? "null" : value.toString());
            }
        });
        return result;
    }

    /**
     * Overloaded method to start flattening with an empty prefix.
     *
     * @param map The input map to flatten.
     * @return A flattened map with dot notation keys.
     */
    public static Map<String, String> flattenMap(Map<String, Object> map) {
        return flattenMap(map, "");
    }

    public static void main(String[] args) {
        // Test case 1: Example provided
        Map<String, Object> input1 = new HashMap<>();
        input1.put("firstName", "Bobby");
        input1.put("lastName", "Shaftoe");
        Map<String, Object> address = new HashMap<>();
        address.put("street", "123 manila road");
        address.put("suburb", "intramuros");
        input1.put("address", address);

        Map<String, String> expected1 = new HashMap<>();
        expected1.put("firstName", "Bobby");
        expected1.put("lastName", "Shaftoe");
        expected1.put("address.street", "123 manila road");
        expected1.put("address.suburb", "intramuros");

        runTest("Test Case 1", input1, expected1);

        // Test case 2: No nested map
        Map<String, Object> input2 = new HashMap<>();
        input2.put("name", "Alice");
        input2.put("age", 30);
        Map<String, String> expected2 = new HashMap<>();
        expected2.put("name", "Alice");
        expected2.put("age", "30");

        runTest("Test Case 2", input2, expected2);

        // Test case 3: Nested map with null value
        Map<String, Object> input3 = new HashMap<>();
        input3.put("username", "bob123");
        Map<String, Object> details = new HashMap<>();
        details.put("email", null);
        input3.put("details", details);
        Map<String, String> expected3 = new HashMap<>();
        expected3.put("username", "bob123");
        expected3.put("details.email", "null");

        runTest("Test Case 3", input3, expected3);

        // Test case 4: Large data input
        Map<String, Object> input4 = new HashMap<>();
        Map<String, Object> nested = new HashMap<>();
        // Simulate a larger nested map
        for (int i = 0; i < 1000; i++) {
            nested.put("key" + i, "value" + i);
        }
        input4.put("large", nested);
        // Create expected output for a few sample keys
        Map<String, String> expected4 = nested.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> "large." + e.getKey(),
                        e -> e.getValue().toString()
                ));

        runTest("Test Case 4 (Large Data)", input4, expected4);
    }

    /**
     * Runs a test case comparing the output of flattenMap with expected result.
     *
     * @param testName The name of the test case.
     * @param input    The input map.
     * @param expected The expected flattened map.
     */
    private static void runTest(String testName, Map<String, Object> input, Map<String, String> expected) {
        Map<String, String> output = flattenMap(input);
        boolean pass = output.equals(expected);

        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("Expected: " + expected);
            System.out.println("Got:      " + output);
        }
    }
}

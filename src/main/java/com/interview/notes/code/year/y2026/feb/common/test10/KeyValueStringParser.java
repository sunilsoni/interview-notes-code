package com.interview.notes.code.year.y2026.feb.common.test10;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyValueStringParser { // Custom class name based on problem

    // Method to parse and sort the string
    static Map<String, String> parseAndSort(String data) { // Method takes input string

        if (data == null || data.isBlank()) // Check if input is null or empty
            return Collections.emptyMap(); // Return empty map to avoid errors

        Map<String, String> map = Arrays.stream(data.split(";")) // Split by ';' and convert to stream
                .map(p -> p.split(":", 2)) // Split each pair into key and value (limit 2)
                .filter(arr -> arr.length == 2) // Ensure valid key:value format
                .collect(Collectors.toMap( // Collect into Map
                        arr -> arr[0].trim(), // Key is first part
                        arr -> arr[1].trim(), // Value is second part
                        (a, b) -> b // If duplicate key, keep latest value
                ));

        return map.entrySet().stream() // Convert map entries to stream
                .sorted(Map.Entry.comparingByValue()) // Sort entries by value alphabetically
                .collect(Collectors.toMap( // Collect sorted entries
                        Map.Entry::getKey, // Key remains same
                        Map.Entry::getValue, // Value remains same
                        (a, b) -> a, // Merge function (not used here)
                        LinkedHashMap::new // Preserve sorted order
                ));
    }

    // Simple test method
    static void test(String input, String expectedFirstKey) { // Test takes input and expected first key
        Map<String, String> result = parseAndSort(input); // Call parsing method
        String actualFirstKey = result.keySet().stream().findFirst().orElse(""); // Get first key after sorting
        System.out.println(actualFirstKey.equals(expectedFirstKey) ? "PASS" : "FAIL"); // Print result
    }

    public static void main(String[] args) { // Main method

        String data = "Name:JohnDoe;Age:29;Skills:Java,Python,Go;Location:NYC"; // Given input

        Map<String, String> sortedMap = parseAndSort(data); // Parse and sort

        sortedMap.forEach((k, v) -> System.out.println(k + " -> " + v)); // Print sorted result

        // Test case 1 (Alphabetical order: 29, Java..., JohnDoe, NYC)
        test(data, "Age"); // Age should come first

        // Test case 2 (Empty string)
        test("", ""); // Should PASS (empty)

        // Test case 3 (Large input simulation)
        StringBuilder large = new StringBuilder(); // Create large test
        for (int i = 1000; i >= 1; i--) // Reverse order
            large.append("Key").append(i).append(":Value").append(i).append(";"); // Add entries
        Map<String, String> largeResult = parseAndSort(large.toString()); // Parse large input
        System.out.println(largeResult.size() == 1000 ? "PASS" : "FAIL"); // Validate size
    }
}
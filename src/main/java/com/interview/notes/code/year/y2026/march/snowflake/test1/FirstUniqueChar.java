package com.interview.notes.code.year.y2026.march.snowflake.test1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstUniqueChar { // We define our main class to hold the logic and our custom testing framework.

    public static char findFirstUnique(String text) { // Method to find the non-repeating character; returns a char.
        if (text == null || text.isEmpty()) return '_'; // Guard clause: prevents errors on empty inputs and returns '_' as a default.

        var counts = text.chars() // Use Java 'var' to keep code short; get an IntStream of characters to start processing.
            .mapToObj(c -> (char) c) // Convert the raw integer values from the stream into actual Character objects.
            .collect(Collectors.groupingBy( // Collect the characters into a Map by defining a grouping rule.
                Function.identity(), // Use the character itself as the key for our map.
                LinkedHashMap::new, // Force the stream to use LinkedHashMap so we don't lose the original string's order.
                Collectors.counting() // Count how many times each character appears and store that number as the value.
            )); // This finishes building our map of {character=count}.

        return counts.entrySet().stream() // Take the entries from our LinkedHashMap and turn them back into a Stream.
            .filter(entry -> entry.getValue() == 1) // Filter the stream to ONLY keep map entries where the count is exactly 1.
            .map(Map.Entry::getKey) // Extract just the character (the key) from those filtered entries.
            .findFirst() // Grab the very first character in this filtered stream (which is the first unique char).
            .orElse('_'); // If the stream is completely empty (no unique characters found), return '_' as a safe fallback.
    } // End of the logic method.

    public static void main(String[] args) { // Main method to execute our standalone, zero-dependency tests.
        test("aabbccddefaabbccddef", '_'); // Run your specific test case where ALL characters repeat; expected output is '_'.
        test("simple", 'i'); // Run a basic test case where the second character is the first unique one.
        test("teeter", 'r'); // Run a test case where the unique character is at the very end.
        test("", '_'); // Run an edge case test with an empty string to ensure it doesn't crash.
        
        var largeInput = "a".repeat(50000) + "b" + "c".repeat(50000); // Java 11+ feature: easily build a massive 100,001 length string.
        test(largeInput, 'b'); // Run the large data test case to prove our O(N) time complexity is fast.
    } // End of the main execution block.

    static void test(String input, char expected) { // Helper method to compare actual results with expectations and print them.
        char result = findFirstUnique(input); // Call our main logic method using the provided test input.
        boolean passed = (result == expected); // Check if the result our code calculated matches what we actually expected.
        String status = passed ? "PASS" : "FAIL"; // Create a simple "PASS" or "FAIL" string based on the boolean check.
        String preview = input.length() > 20 ? input.substring(0, 20) + "..." : input; // Truncate huge strings so the console output stays clean.
        System.out.println(status + " -> Input: " + preview + " | Expected: " + expected + " | Got: " + result); // Print the final formatted test report.
    } // End of the test helper method.
} // End of the class.
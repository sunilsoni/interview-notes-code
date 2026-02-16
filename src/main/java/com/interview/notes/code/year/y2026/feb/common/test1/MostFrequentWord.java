package com.interview.notes.code.year.y2026.feb.common.test1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostFrequentWord {

    /**
     * Finds the most frequent word in a paragraph that is not in the banned list.
     */
    public static String solve(String paragraph, String[] banned) {
        // Convert banned array to a Set for fast O(1) lookups to avoid slow array searches
        var bannedSet = new HashSet<>(Arrays.asList(banned));

        // 1. Normalize: Convert paragraph to lowercase to ensure case-insensitivity
        // 2. Clean: Replace all non-alphabetic characters ("[^a-z]") with spaces
        // 3. Split: Divide the string into an array of words based on whitespace
        // 4. Stream: Create a Stream<String> to process the words functionally
        return Arrays.stream(paragraph.toLowerCase().replaceAll("[^a-z]+", " ").split(" "))

                // Remove empty strings that might result from multiple spaces or punctuation
                .filter(word -> !word.isEmpty())

                // Exclude words that are present in the bannedSet
                .filter(word -> !bannedSet.contains(word))

                // Group words by themselves and count occurrences (Result: Map<String, Long>)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))

                // Convert the Map to a Set of Entries to process key-value pairs
                .entrySet().stream()

                // Find the entry with the maximum value (highest frequency count)
                .max(Map.Entry.comparingByValue())

                // Extract the word (key) from the map entry found
                .map(Map.Entry::getKey)

                // Return an empty string as a fallback if no word is found (though problem guarantees one)
                .orElse("");
    }

    // --- TEST METHOD ---
    public static void main(String[] args) {
        System.out.println("=== Starting Tests (Java 21) ===\n");

        // Test Case 1: Standard Example
        String p1 = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] b1 = {"hit"};
        runTest("1. Standard Case", p1, b1, "ball");

        // Test Case 2: Punctuation Heavy
        String p2 = "a, a, a, a, b,b,b,c, c";
        String[] b2 = {"a"};
        runTest("2. Punctuation Case", p2, b2, "b");

        // Test Case 3: Empty Banned List
        String p3 = "Test the system test the system test";
        String[] b3 = {};
        runTest("3. No Banned Words", p3, b3, "test");

        // Test Case 4: Large Data Input simulation
        // Creating a large string by repeating a pattern 100,000 times
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("apple banana orange apple "); // 'apple' appears twice per loop
        }
        String p4 = sb.toString();
        String[] b4 = {"banana"}; // Ban 'banana', so 'apple' should win
        runTest("4. Large Data Input", p4, b4, "apple");

        System.out.println("\n=== All Tests Completed ===");
    }

    /**
     * Helper method to run a test and print PASS/FAIL status.
     */
    private static void runTest(String testName, String p, String[] b, String expected) {
        long startTime = System.currentTimeMillis(); // Capture start time for performance check

        String result = solve(p, b); // Execute the logic

        long endTime = System.currentTimeMillis(); // Capture end time

        // Check if result matches expected value
        boolean pass = result.equals(expected);

        // Print formatted output using Java formatted strings
        System.out.printf("[%s] -> %s | Expected: %s, Got: %s | Time: %dms%n",
                pass ? "PASS" : "FAIL",
                testName,
                expected,
                result,
                (endTime - startTime)
        );
    }
}
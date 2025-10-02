package com.interview.notes.code.year.y2025.october.common.test1;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LongestCommonPrefixBruteForce {

    // Public static method that finds the longest common prefix using a brute-force vertical scan
    public static String longestCommonPrefixBruteForce(String[] strs) {
        // If the input array itself is null, there is no data to process, so return empty prefix
        if (strs == null) return "";

        // If the array has zero length, there are no strings, so the common prefix is empty
        if (strs.length == 0) return "";

        // If any string inside the array is null, we cannot safely call length/charAt; define result as empty prefix
        boolean hasNull = Arrays.stream(strs).anyMatch(Objects::isNull);
        if (hasNull) return "";

        // Compute the minimum string length among all strings;
        // the common prefix cannot be longer than the shortest string.
        // mapToInt(String::length) safely works as we have already filtered out nulls above.
        int minLen = Arrays.stream(strs).mapToInt(String::length).min().orElse(0);

        // If the shortest length is 0, that means at least one string is empty, so the common prefix is empty
        if (minLen == 0) return "";

        // Loop over each character index up to (but not including) minLen to compare vertically across all strings
        for (int i = 0; i < minLen; i++) {
            // Read the character from the first string at position i;
            // we will compare this character with the same position in all other strings
            char ch = strs[0].charAt(i);

            // Because we will use i inside a lambda, store it in a final/effectively final variable to use in allMatch
            final int idx = i;

            // Check if all strings have the exact same character ch at position idx.
            // If any string differs, allMatch returns false.
            boolean allAgree = Arrays.stream(strs).allMatch(s -> s.charAt(idx) == ch);

            // If not all strings matched this character at this position, return the prefix up to this index
            if (!allAgree) {
                // substring(0, i) returns the common prefix found so far
                return strs[0].substring(0, i);
            }
        }

        // If we exit the loop, it means all characters up to minLen matched,
        // so the longest common prefix is the first string's substring of length minLen
        return strs[0].substring(0, minLen);
    }

    // Helper method to run a single test case and print PASS/FAIL with details
    private static void runTest(String name, String[] input, String expected) {
        // Record start time in nanoseconds for performance measurement
        long startNs = System.nanoTime();

        // Compute actual result by calling the brute-force function
        String actual = longestCommonPrefixBruteForce(input);

        // Compute elapsed time in milliseconds for better readability
        long elapsedMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        // Check if the actual result equals the expected result
        boolean pass = Objects.equals(actual, expected);

        // Print the outcome in a clear PASS/FAIL format, along with timing and the result content
        System.out.println(
                (pass ? "PASS" : "FAIL") + " -> " + name +
                        " | Expected: \"" + expected + "\" | Got: \"" + actual + "\" | " + elapsedMs + " ms"
        );
    }

    // Main method to execute multiple test cases, including edge cases and large-data scenarios
    public static void main(String[] args) {
        // Print Java version to confirm runtime (useful when validating Java 8 environment)
        System.out.println("Java Version: " + System.getProperty("java.version"));

        // === Sample tests from the prompt ===

        // Example 1: Expected "fl" because "flower","flow","flight" share "fl"
        runTest("Example 1", new String[]{"flower", "flow", "flight"}, "fl");

        // Example 2: Expected "" because there is no common prefix among "dog","racecar","car"
        runTest("Example 2", new String[]{"dog", "racecar", "car"}, "");

        // === Additional edge cases ===

        // Single element: the common prefix of a single string should be the string itself
        runTest("Single String", new String[]{"alone"}, "alone");

        // Empty array: by definition return empty string
        runTest("Empty Array", new String[]{}, "");

        // Contains null item: we decide to return "" to avoid NullPointer and signal no usable common prefix
        runTest("Contains Null", new String[]{"abc", null, "abd"}, "");

        // Contains empty string: shortest length is 0, so prefix must be empty
        runTest("Contains Empty", new String[]{"", "a", "ab"}, "");

        // All identical strings: prefix is the whole string
        runTest("All Identical", new String[]{"same", "same", "same"}, "same");

        // Case-sensitive check: 'A' != 'a', so expect empty
        runTest("Case Sensitive", new String[]{"Apple", "app"}, "");

        // Mixed lengths with full match to shortest
        runTest("Shortest is Prefix", new String[]{"pre", "prefix", "presto"}, "pre");

        // === Large data tests ===

        // Large Positive: many strings sharing a substantial common prefix
        // We create many strings that begin with "commonprefix" and then add small variable suffixes.
        final int LARGE_N = 50_000;                   // Number of strings to test scalability without being too heavy
        final String basePrefix = "commonprefix";     // The shared prefix among all generated strings
        String[] largePos = new String[LARGE_N];      // Allocate array for the large positive test

        // Populate the array: each string is basePrefix + index, to ensure they all share the basePrefix
        for (int i = 0; i < LARGE_N; i++) {
            largePos[i] = basePrefix + i;            // Example: "commonprefix0", "commonprefix1", ...
        }

        // Expect the common prefix to be exactly the basePrefix
        runTest("Large Data - Positive", largePos, basePrefix);

        // Large Negative: strings do not share any first character (so prefix should be empty)
        String[] largeNeg = new String[LARGE_N];      // Allocate array for large negative test
        for (int i = 0; i < LARGE_N; i++) {
            // Alternate starting letters to break common prefix at first character
            // Example: "aX...", "bY...", "aZ...", "bW..."
            char start = (i % 2 == 0) ? 'a' : 'b';   // Choose 'a' or 'b' alternately to ensure mismatch at index 0
            largeNeg[i] = start + "var" + i;         // Build a short string with that starting char
        }

        // Expect empty prefix because first characters already mismatch
        runTest("Large Data - Negative", largeNeg, "");
    }
}
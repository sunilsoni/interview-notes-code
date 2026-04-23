package com.interview.notes.code.year.y2026.april.common.test10;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PatternMatcher {

    public static void main(String[] args) {
        // Define a pattern to search for within the strings
        String pattern = "abc"; 

        // Define a list of test strings, including some that match and some that don't
        List<String> inputs = Arrays.asList("abcdef", "ghi", "abc", "xyzabc", "test", "ABC");

        // Execute the search logic and store the results
        List<String> results = findMatches(inputs, pattern);

        // Run our simple manual test suite to verify the results
        runTests(results, pattern);
    }

    /**
     * Filters the input list to find strings containing the pattern.
     */
    public static List<String> findMatches(List<String> data, String p) {
        return data.stream() // Convert the list into a stream for functional processing
                   .filter(Objects::nonNull) // Remove null entries to prevent NullPointerException
                   .filter(s -> s.contains(p)) // Keep only strings that contain the specific pattern
                   .toList(); // Java 16+ feature: efficiently collect results into an unmodifiable list
    }

    /**
     * Simple testing method to verify PASS/FAIL without JUnit
     */
    public static void runTests(List<String> results, String pattern) {
        // Requirement: Handle large data/check logic. We verify if matches actually contain the pattern.
        boolean allPass = results.stream().allMatch(r -> r.contains(pattern));

        // Print result status
        if (allPass && !results.isEmpty()) {
            System.out.println("TEST STATUS: PASS"); // All filtered items match the pattern
            System.out.println("Matches found: " + results); // Display the actual matching strings
        } else if (results.isEmpty()) {
            System.out.println("TEST STATUS: PASS (No matches found for pattern: " + pattern + ")");
        } else {
            System.out.println("TEST STATUS: FAIL"); // Something went wrong in the filtering logic
        }
    }
}
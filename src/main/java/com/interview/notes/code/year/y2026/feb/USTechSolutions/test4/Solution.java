package com.interview.notes.code.year.y2026.feb.USTechSolutions.test4;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {

    /**
     * Finds the IP address with the most occurrences in the log.
     * Uses Java Streams for concise, efficient processing.
     */
    public static String findTopIpaddress(String[] lines) {
        // Handle edge case: if input is null or empty, return null immediately
        if (lines == null || lines.length == 0) return null;

        // Start a stream from the array of log lines
        return Arrays.stream(lines)
                // Extract just the IP part (0 to first space) to minimize memory usage
                .map(line -> line.substring(0, line.indexOf(' ')))
                // Group by the IP string itself and count occurrences of each
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                // Convert the map (IP -> Count) back to a stream of entries to find the max
                .entrySet().stream()
                // Compare entries by their value (the count) to find the highest one
                .max(Map.Entry.comparingByValue())
                // Map the result (Map.Entry) to just the key (the IP String)
                .map(Map.Entry::getKey)
                // Return null if the stream was somehow empty (handled by first check, but safe)
                .orElse(null);
    }

    /**
     * Main execution entry point.
     * Runs the test suite.
     */
    public static void main(String[] args) {
        // Run the tests and print a summary
        if (doTestsPass()) {
            System.out.println("\nAll tests passed successfully.");
        } else {
            System.out.println("\nSome tests failed.");
        }
    }

    /**
     * Custom test runner to verify logic without JUnit.
     * Returns true if all tests pass, false otherwise.
     */
    public static boolean doTestsPass() {
        boolean allPassed = true;

        // --- Test Case 1: Provided Example ---
        String[] lines1 = {
                "10.0.0.1 - frank [10/Dec/2000:12:34:56 -0500] \"GET /a.gif HTTP/1.0\" 200 234",
                "10.0.0.1 - frank [10/Dec/2000:12:34:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                "10.0.0.2 - nancy [10/Dec/2000:12:34:58 -0500] \"GET /c.gif HTTP/1.0\" 200 234"
        };
        // Expecting 10.0.0.1 because it appears twice
        allPassed &= runTest("Standard Case", lines1, "10.0.0.1");

        // --- Test Case 2: Edge Case (Single Line) ---
        String[] lines2 = {
                "192.168.1.1 - admin [10/Dec/2000:12:34:56 -0500] \"GET /a.gif HTTP/1.0\" 200 234"
        };
        // Expecting the only IP present
        allPassed &= runTest("Single Line Case", lines2, "192.168.1.1");

        // --- Test Case 3: Edge Case (Tie Breaker) ---
        // When counts are equal, our logic picks one deterministically based on map hashing
        String[] lines3 = {
                "1.1.1.1 - log [..] GET",
                "2.2.2.2 - log [..] GET"
        };
        String result3 = findTopIpaddress(lines3);
        // We just verify it returns a valid non-null string for ties
        boolean tiePass = result3.equals("1.1.1.1") || result3.equals("2.2.2.2");
        System.out.println("Test: Tie Case -> " + (tiePass ? "PASS" : "FAIL"));
        allPassed &= tiePass;

        // --- Test Case 4: Edge Case (Empty Input) ---
        allPassed &= runTest("Empty Input Case", new String[]{}, null);

        // --- Test Case 5: Large Data Input (Stress Test) ---
        // Simulating 1 million log lines to check performance
        System.out.println("Generating Large Data (1M lines)...");
        String[] largeData = new String[1_000_000];
        // Fill array: "10.0.0.1" appears 600k times, "10.0.0.2" appears 400k times
        for (int i = 0; i < 1_000_000; i++) {
            if (i < 600_000) largeData[i] = "10.0.0.1 - log data...";
            else largeData[i] = "10.0.0.2 - log data...";
        }
        long startTime = System.currentTimeMillis();
        // Expect 10.0.0.1 as it has 600k entries
        boolean largePass = runTest("Large Data Case", largeData, "10.0.0.1");
        long endTime = System.currentTimeMillis();
        System.out.println("Large Data processing time: " + (endTime - startTime) + "ms");
        allPassed &= largePass;

        return allPassed;
    }

    /**
     * Helper method to execute a single test and print result.
     */
    private static boolean runTest(String testName, String[] input, String expected) {
        // Run the solution
        String result = findTopIpaddress(input);

        // Check equality (handling nulls safely)
        boolean passed = Objects.equals(result, expected);

        // Print formatted output
        System.out.printf("Test: %-20s -> %s (Exp: %s, Got: %s)%n",
                testName,
                passed ? "PASS" : "FAIL",
                expected,
                result);

        return passed;
    }
}
package com.interview.notes.code.year.y2026.feb.USTechSolutions.test3;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {

    /**
     * Finds IP address(es) with the most occurrences.
     * If there is a tie, returns all of them separated by a comma.
     */
    public static String findTopIpaddress(String[] lines) {
        // Handle empty input immediately to avoid processing
        if (lines == null || lines.length == 0) return "";

        // Step 1: Count occurrences of each IP using Stream API
        // 'var' reduces verbosity (Java 10+ feature)
        var ipCounts = Arrays.stream(lines)
            // Extract IP (substring to first space) to avoid storing full log lines
            .map(line -> line.substring(0, line.indexOf(' ')))
            // Group by IP and count frequency -> Map<String, Long>
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Guard clause: if map is empty after processing
        if (ipCounts.isEmpty()) return "";

        // Step 2: Find the maximum frequency value in the map
        // We use Collections.max on the values collection directly
        long maxVal = Collections.max(ipCounts.values());

        // Step 3: Filter IPs that match the max count and join them
        return ipCounts.entrySet().stream()
            // Keep only entries where the count equals the maximum found
            .filter(entry -> entry.getValue() == maxVal)
            // Extract just the IP key from the map entry
            .map(Map.Entry::getKey)
            // Sort to ensure deterministic output (e.g., "1.1, 2.2" vs "2.2, 1.1")
            .sorted()
            // Join strings with a comma separator
            .collect(Collectors.joining(","));
    }

    /**
     * Main execution entry point.
     * Runs the test suite without external libraries.
     */
    public static void main(String[] args) {
        // Execute the custom test runner
        if (doTestsPass()) {
            System.out.println("All tests passed.");
        } else {
            System.out.println("Some tests failed.");
        }
    }

    /**
     * Custom test runner to verify logic.
     * Returns true if all tests pass.
     */
    public static boolean doTestsPass() {
        boolean allPassed = true;

        // --- Test 1: Standard Case (Single Winner) ---
        String[] lines1 = {
            "10.0.0.1 - frank [10/Dec/2000] GET 200",
            "10.0.0.1 - frank [10/Dec/2000] GET 200",
            "10.0.0.2 - nancy [10/Dec/2000] GET 200"
        };
        // Expect only 10.0.0.1
        allPassed &= runTest("Standard Case", lines1, "10.0.0.1");

        // --- Test 2: Tie Case (Multiple Winners) ---
        String[] lines2 = {
            "10.0.0.1 - log data...",
            "10.0.0.1 - log data...",
            "10.0.0.2 - log data...",
            "10.0.0.2 - log data..."
        };
        // Both IPs appear twice. Expect "10.0.0.1,10.0.0.2" (Sorted order)
        allPassed &= runTest("Tie Case", lines2, "10.0.0.1,10.0.0.2");

        // --- Test 3: Large Data (Performance & Tie Check) ---
        System.out.println("Generating Large Data (1M lines)...");
        String[] largeData = new String[1_000_000];
        // Create a tie with 500k each
        for (int i = 0; i < 1_000_000; i++) {
            if (i < 500_000) largeData[i] = "192.168.1.1 - log...";
            else largeData[i] = "10.0.0.5 - log...";
        }
        
        long start = System.currentTimeMillis();
        // Expect sorted comma separated string: "10.0.0.5,192.168.1.1"
        boolean largePass = runTest("Large Data Tie", largeData, "10.0.0.5,192.168.1.1");
        System.out.println("Large Data Time: " + (System.currentTimeMillis() - start) + "ms");
        allPassed &= largePass;

        return allPassed;
    }

    /**
     * Helper to run a test and print pass/fail status.
     */
    private static boolean runTest(String name, String[] input, String expected) {
        String result = findTopIpaddress(input);
        boolean pass = result.equals(expected);
        System.out.println("Test: " + name + " -> " + (pass ? "PASS" : "FAIL") + 
                           " [Expected: " + expected + ", Got: " + result + "]");
        return pass;
    }
}
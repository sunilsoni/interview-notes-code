package com.interview.notes.code.year.y2026.feb.GoldmanSachs.test3;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogAnalyzer {

    /**
     * Finds the most frequent IP address in the given log lines.
     * Uses Java Streams for concise, parallel-ready processing.
     */
    public static String findTopIpaddress(String[] lines) {
        // Use Optional to handle empty inputs safely without crashing
        return Arrays.stream(lines)                                     // Convert Array to Stream to process data flow
            .parallel()                                                 // Use parallel processing to handle LARGE data sets faster on multi-core CPUs
            .map(line -> line.substring(0, line.indexOf(' ')))          // Extract IP: Take substring from start (0) to first space. Faster than split()
            .collect(Collectors.groupingBy(ip -> ip, Collectors.counting())) // Map-Reduce: Group by IP and count occurrences. Result is Map<String, Long>
            .entrySet().stream()                                        // Convert the Map back to a Stream of Entries (Key-Value pairs)
            .max(Map.Entry.comparingByValue())                          // Find the Entry with the maximum Value (the count)
            .map(Map.Entry::getKey)                                     // Extract just the Key (the IP address) from the winning Entry
            .orElse("");                                                // Return empty string if input was empty (avoids NullPointer)
    }

    /**
     * Custom Main method for Testing (No JUnit).
     * Runs various scenarios including large data simulation.
     */
    public static void main(String[] args) {
        System.out.println("Running Tests...");

        // --- Test Case 1: The Example Case provided in the prompt ---
        String[] lines1 = {
            "10.0.0.1 - frank [10/Dec/2000:12:34:56 -0500] \"GET /a.gif HTTP/1.0\" 200 234",
            "10.0.0.1 - frank [10/Dec/2000:12:34:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
            "10.0.0.2 - nancy [10/Dec/2000:12:34:58 -0500] \"GET /c.gif HTTP/1.0\" 200 234"
        };
        runTest("Basic Example", lines1, "10.0.0.1");

        // --- Test Case 2: Tie Breaker (Logic usually picks first found or arbitrary in Maps) ---
        String[] lines2 = {
            "192.168.1.1 - - ...", 
            "192.168.1.2 - - ...", 
            "192.168.1.1 - - ..." 
        };
        runTest("Simple Count", lines2, "192.168.1.1");

        // --- Test Case 3: Empty Input ---
        String[] lines3 = {};
        runTest("Empty Input", lines3, "");

        // --- Test Case 4: Single Entry ---
        String[] lines4 = {"127.0.0.1 - - [Time] \"GET...\""};
        runTest("Single Entry", lines4, "127.0.0.1");

        // --- Test Case 5: Large Data Simulation (1 Million Lines) ---
        System.out.println("\nGenerating Large Data (1,000,000 lines)...");
        var largeData = generateLargeData(); // Use 'var' (Java 10+) for cleaner variable declaration
        
        long startTime = System.currentTimeMillis(); // Start timer
        String result = findTopIpaddress(largeData); // Run solution
        long endTime = System.currentTimeMillis();   // End timer
        
        // Validation for large data: "10.0.0.1" is hardcoded to be most frequent in generator
        boolean pass = result.equals("10.0.0.1"); 
        
        System.out.println("Test: Large Data Performance");
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

    // Helper method to run individual tests and print PASS/FAIL
    private static void runTest(String testName, String[] input, String expected) {
        String result = findTopIpaddress(input);             // Execute logic
        boolean passed = result.equals(expected);            // Check matches
        System.out.println("Test: " + testName + " | Status: " + (passed ? "PASS" : "FAIL") + 
                           " | Expected: " + expected + ", Got: " + result);
    }

    // Helper to generate massive dataset to prove efficiency
    private static String[] generateLargeData() {
        int size = 1_000_000;
        String[] logs = new String[size];
        
        // Fill array parallelly for speed
        IntStream.range(0, size).parallel().forEach(i -> {
            if (i % 3 == 0) logs[i] = "10.0.0.1 - - random log entry"; // This will be 33% (most frequent)
            else if (i % 3 == 1) logs[i] = "192.168.0.1 - - random log entry";
            else logs[i] = "172.16.0.1 - - random log entry";
        });
        return logs;
    }
}
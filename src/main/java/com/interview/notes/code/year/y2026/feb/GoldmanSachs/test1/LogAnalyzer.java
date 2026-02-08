package com.interview.notes.code.year.y2026.feb.GoldmanSachs.test1;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {

    /**
     * Finds the IP address(es) with the highest frequency.
     * Logic:
     * 1. Parse lines to extract IPs.
     * 2. Count occurrences of each IP.
     * 3. Find the maximum frequency count.
     * 4. Collect ALL IPs that match that maximum count (handling ties).
     */
    public static String findTopIpaddress(String[] lines) {
        if (lines == null || lines.length == 0) return ""; // Guard clause for safety

        // Step 1: Count occurrences of every IP in parallel (fast for large data)
        var ipCounts = Arrays.stream(lines)
            .parallel() // Utilize multi-core processing
            .map(line -> line.substring(0, line.indexOf(' '))) // Extract IP: fast substring before first space
            .collect(Collectors.groupingBy(ip -> ip, Collectors.counting())); // Map<String, Long> of IP -> Count

        // Step 2: Find the highest count among all IPs
        long maxCount = ipCounts.values().stream()
            .max(Long::compare) // Compare counts
            .orElse(0L); // Default to 0 if map is empty

        // Step 3: Retrieve all IPs that have that specific max count
        return ipCounts.entrySet().stream()
            .filter(entry -> entry.getValue() == maxCount) // Filter: Keep only the winners
            .map(Map.Entry::getKey) // Extract: Get the IP string
            .sorted() // Sort: Ensures deterministic output (e.g., "1.1.1.1,2.2.2.2")
            .collect(Collectors.joining(",")); // Join: Combine multiple winners with comma
    }

    /**
     * Test Runner: strictly checks Pass/Fail for all scenarios.
     */
    public static boolean doTestsPass() {
        boolean allPassed = true;

        // --- Test 1: Standard Case (from your screenshot) ---
        String[] standardInput = {
            "10.0.0.1 - frank [10/Dec/2000:12:34:56 -0500] \"GET /a.gif HTTP/1.0\" 200 234",
            "10.0.0.1 - frank [10/Dec/2000:12:34:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
            "10.0.0.2 - nancy [10/Dec/2000:12:34:58 -0500] \"GET /c.gif HTTP/1.0\" 200 234"
        };
        // Expect 10.0.0.1 because it appears twice
        if (!runTest("Standard Happy Path", standardInput, "10.0.0.1")) allPassed = false;


        // --- Test 2: Tie Scenario (The specific case you asked about) ---
        String[] tieInput = {
            "1.2.3.4 - log",
            "2.3.4.5 - log"
        };
        // Both appear once. Logic must return BOTH, sorted and comma-separated.
        if (!runTest("Tie Scenario (Multiple Winners)", tieInput, "1.2.3.4,2.3.4.5")) allPassed = false;


        // --- Test 3: Complex Tie (Multiple winners with count > 1) ---
        String[] complexTie = {
            "10.0.0.1 - log", "10.0.0.1 - log", // Count 2
            "192.168.1.1 - log",                // Count 1
            "10.0.0.2 - log", "10.0.0.2 - log"  // Count 2
        };
        // 10.0.0.1 and 10.0.0.2 are tied with 2. 192... is loser.
        if (!runTest("Complex Tie", complexTie, "10.0.0.1,10.0.0.2")) allPassed = false;


        // --- Test 4: Single Entry ---
        String[] singleInput = { "8.8.8.8 - log data" };
        if (!runTest("Single Element", singleInput, "8.8.8.8")) allPassed = false;


        // --- Test 5: Empty Input ---
        String[] emptyInput = {};
        if (!runTest("Empty Input", emptyInput, "")) allPassed = false;


        // --- Test 6: Large Data Performance ---
        System.out.println("\nGenerating Large Data (100,000 lines)...");
        String[] largeData = new String[100_000];
        // Generate: 10.0.0.1 (Evens) and 192.168.0.1 (Odds). Perfect Tie.
        Arrays.parallelSetAll(largeData, i -> (i % 2 == 0 ? "10.0.0.1" : "192.168.0.1") + " - data");
        
        long start = System.currentTimeMillis();
        String largeResult = findTopIpaddress(largeData);
        long end = System.currentTimeMillis();
        
        // We expect both because they are split 50/50 exactly
        boolean largePass = largeResult.equals("10.0.0.1,192.168.0.1");
        System.out.println("Test: Large Data (Tie) | Time: " + (end - start) + "ms");
        System.out.println("Result: " + (largePass ? "PASS" : "FAIL"));
        if (!largePass) allPassed = false;


        return allPassed;
    }

    // Helper: Prints inputs, expected vs actual, and PASS/FAIL status
    private static boolean runTest(String name, String[] input, String expected) {
        String result = findTopIpaddress(input);
        boolean pass = result.equals(expected);
        System.out.println("--------------------------------------------------");
        System.out.println("Case: " + name);
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + result);
        System.out.println("Status:   " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("\nAll Tests Passed");
        } else {
            System.out.println("\nSome Tests Failed");
        }
    }
}
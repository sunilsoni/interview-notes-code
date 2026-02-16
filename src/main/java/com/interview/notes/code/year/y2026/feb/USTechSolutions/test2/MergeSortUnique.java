package com.interview.notes.code.year.y2026.feb.USTechSolutions.test2;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MergeSortUnique {

    // Core logic method: Merges, removes duplicates, and sorts
    public static List<String> solve(List<String> list1, List<String> list2) {
        // Return result immediately using Stream pipeline for concise code
        return Stream.concat(list1.stream(), list2.stream()) // Combine both lists into a single stream of data
                .distinct() // Remove duplicate elements efficiently (keep only 1 of each)
                .sorted() // Sort the remaining elements in natural alphabetical order
                .toList(); // Collect results into an immutable List (Java 16+ feature)
    }

    // Main method: Entry point for execution and testing
    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---"); // Print start message

        // --- Test Case 1: Standard Overlap ---
        var l1 = List.of("apple", "banana", "orange"); // Create first immutable list using 'var'
        var l2 = List.of("banana", "grape", "apple"); // Create second list with duplicates (banana, apple)
        var expected1 = List.of("apple", "banana", "grape", "orange"); // Define expected sorted unique output
        runTest("Standard Overlap", l1, l2, expected1); // Run the test helper method

        // --- Test Case 2: No Overlap ---
        var l3 = List.of("a", "b"); // Create list with simple letters
        var l4 = List.of("c", "d"); // Create distinct list
        var expected2 = List.of("a", "b", "c", "d"); // Expected result is just combined sorted
        runTest("No Overlap", l3, l4, expected2); // Run test

        // --- Test Case 3: Empty Lists ---
        List<String> empty = List.of(); // Create an empty list
        runTest("Both Empty", empty, empty, empty); // Result should be empty
        runTest("One Empty", l3, empty, List.of("a", "b")); // Result should be original list sorted

        // --- Test Case 4: Unsorted Inputs with Duplicates inside same list ---
        var l5 = List.of("z", "a", "z"); // List has internal duplicates
        var l6 = List.of("b", "a"); // Second list adds more overlap
        var expected4 = List.of("a", "b", "z"); // 'z' and 'a' should appear once, sorted
        runTest("Internal Duplicates", l5, l6, expected4); // Run test

        // --- Test Case 5: Large Data Input (Stress Test) ---
        runLargeDataTest(); // Call specialized method for large data verification
    }

    // Helper method to run functional tests and print PASS/FAIL
    public static void runTest(String testName, List<String> input1, List<String> input2, List<String> expected) {
        try { // Start try block to handle potential errors safely
            long start = System.nanoTime(); // Record start time for performance check
            var actual = solve(input1, input2); // Execute the core logic
            long end = System.nanoTime(); // Record end time

            boolean passed = actual.equals(expected); // Check if actual result matches expected exactly
            String status = passed ? "PASS" : "FAIL"; // Determine status string

            // Print formatted result
            System.out.printf("[%s] %s (Time: %d ns)%n", status, testName, (end - start));

            if (!passed) { // If failed, print details for debugging
                System.out.println("   Expected: " + expected); // Show what we wanted
                System.out.println("   Actual:   " + actual); // Show what we got
            }
        } catch (Exception e) { // Catch unexpected errors
            System.out.println("[FAIL] " + testName + " with Exception: " + e.getMessage()); // Print error
        }
    }

    // Specialized test for Large Data (100,000+ items)
    public static void runLargeDataTest() {
        System.out.println("--- Running Large Data Test ---"); // Print section header

        // Generate large data: 0 to 9999 as strings
        List<String> large1 = IntStream.range(0, 10000).mapToObj(String::valueOf).toList(); // 10k items
        List<String> large2 = IntStream.range(5000, 15000).mapToObj(String::valueOf).toList(); // 10k items (overlap 5000-9999)

        long start = System.currentTimeMillis(); // Start timer (ms)
        var result = solve(large1, large2); // Run logic
        long end = System.currentTimeMillis(); // End timer

        // Logic check: Range 0 to 14999 implies 15000 unique items
        boolean sizeCorrect = result.size() == 15000; // Verify count
        boolean firstCorrect = result.get(0).equals("0"); // Verify sort (lexicographical "0" comes first)
        // Note: "10" comes before "2" in string sort. We just check basic sanity here.

        if (sizeCorrect && firstCorrect) { // If checks pass
            System.out.println("[PASS] Large Data (15k unique items processed in " + (end - start) + "ms)"); // Print pass
        } else { // If checks fail
            System.out.println("[FAIL] Large Data. Size: " + result.size()); // Print fail
        }
    }
}
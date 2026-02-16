package com.interview.notes.code.year.y2026.feb.USTechSolutions.test3;

import java.util.*;

public class CountryFormatter {

    // Main method to run tests and execute logic
    public static void main(String[] args) {

        // --- Test Case 1: Standard Data ---
        var input1 = Arrays.asList("India", "America", "Australia", "Japan"); // Create standard input list
        var expected1 = Arrays.asList("A-America", "A-Australia", "I-India", "J-Japan"); // Define expected output
        runTest("Standard Input", input1, expected1); // Run the test function

        // --- Test Case 2: Edge Case (Nulls and Empty Strings) ---
        var input2 = Arrays.asList("India", null, "", "Japan"); // Input with bad data
        var expected2 = Arrays.asList("I-India", "J-Japan"); // Expected result filtering bad data
        runTest("Null/Empty Handling", input2, expected2); // Run the test function

        // --- Test Case 3: Edge Case (Empty Input List) ---
        List<String> input3 = new ArrayList<>(); // Create empty list
        var expectedMain3 = new ArrayList<String>(); // Expect empty result
        runTest("Empty List", input3, expectedMain3); // Run the test function

        // --- Test Case 4: Large Data Performance ---
        // Generating a large list of 1 million "India" strings to test memory/speed
        var largeInput = Collections.nCopies(1_000_000, "India"); // Create huge list
        long startTime = System.currentTimeMillis(); // Start timer
        var result = formatCountries(largeInput); // Process large data
        long endTime = System.currentTimeMillis(); // End timer

        // Manually checking large data result size
        if (result.size() == 1_000_000) { // Check if all items processed
            System.out.println("Test Case: Large Data [PASS] - Time: " + (endTime - startTime) + "ms"); // Print pass
        } else {
            System.out.println("Test Case: Large Data [FAIL]"); // Print fail
        }
    }

    // Core Business Logic Method
    public static List<String> formatCountries(List<String> countries) {
        // Check if the main list is null to avoid NullPointerException immediately
        if (countries == null) return new ArrayList<>();

        // Start the Stream pipeline to process data
        return countries.stream()
                // Filter out null objects to prevent errors in later steps
                .filter(Objects::nonNull)
                // Filter out empty strings so we don't process blank names
                .filter(c -> !c.isEmpty())
                // Sort the countries alphabetically (Natural Order)
                .sorted()
                // Transform: Take 1st char, add hyphen, add original name (e.g., "I-India")
                .map(c -> c.charAt(0) + "-" + c)
                // Collect the stream results back into a concrete List
                .toList(); // Java 16+ feature (cleaner than Collectors.toList())
    }

    // Helper method to run tests and print PASS/FAIL status
    public static void runTest(String testName, List<String> input, List<String> expected) {
        // Call the main logic method with input
        var actual = formatCountries(input);

        // Compare the actual result with expected result
        if (actual.equals(expected)) {
            // If they match exactly, print PASS
            System.out.println("Test Case: " + testName + " [PASS]");
        } else {
            // If they don't match, print FAIL with details
            System.out.println("Test Case: " + testName + " [FAIL]");
            System.out.println("   Expected: " + expected); // Show what we wanted
            System.out.println("   Actual:   " + actual);   // Show what we got
        }
    }
}
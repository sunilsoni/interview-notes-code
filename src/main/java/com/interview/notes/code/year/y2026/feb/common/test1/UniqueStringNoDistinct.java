package com.interview.notes.code.year.y2026.feb.common.test1;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.IntStream;

public class UniqueStringNoDistinct {

    /**
     * Removes duplicates using a Set filter instead of .distinct().
     */
    public static String[] removeDuplicates(String[] input) {
        // Safety check: if input is null, return empty to prevent crash
        if (input == null) {
            // Return empty array immediately
            return new String[0];
        }

        // Create a Set to track items we have seen. 
        // We use LinkedHashSet to preserve the original order of elements.
        var seenItems = new LinkedHashSet<String>();

        // Start the stream processing
        return Arrays.stream(input)
            // Filter logic: seenItems.add(s) returns true if 's' is new, false if it exists
            // This manually filters out duplicates without using .distinct()
            .filter(s -> seenItems.add(s))
            // Convert the remaining elements back into a String array
            // String[]::new creates the array of the correct size automatically
            .toArray(String[]::new);
    }

    /**
     * Helper method to run tests and print PASS/FAIL status.
     */
    public static void runTest(String testName, String[] input, String[] expected) {
        // Run the removeDuplicates method and store result
        var actual = removeDuplicates(input);
        
        // Compare the actual result with the expected result deeply
        var isMatch = Arrays.equals(actual, expected);
        
        // Print the test name to console
        System.out.print("Test: " + testName + " -> ");
        
        // Logic to print PASS or FAIL
        if (isMatch) {
            // Test passed successfully
            System.out.println("PASS");
        } else {
            // Test failed, print details for debugging
            System.out.println("FAIL");
            // Show what we expected to get
            System.out.println("   Expected: " + Arrays.toString(expected));
            // Show what we actually got
            System.out.println("   Actual:   " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        // Start of test suite
        System.out.println("--- Starting Tests (Without .distinct()) ---");

        // Test 1: Standard case with duplicates
        // "Java" and "C++" are repeated
        runTest("Basic Duplicates", 
            new String[]{"Java", "Python", "Java", "C++", "C++"}, 
            new String[]{"Java", "Python", "C++"});

        // Test 2: Input already unique
        // Logic should simply return the same elements
        runTest("No Duplicates", 
            new String[]{"A", "B", "C"}, 
            new String[]{"A", "B", "C"});

        // Test 3: Empty input array
        // Should handle gracefully without error
        runTest("Empty Array", 
            new String[]{}, 
            new String[]{});

        // Test 4: Null input
        // Should trigger the null check at start of method
        runTest("Null Input", 
            null, 
            new String[]{});

        // Test 5: All elements are identical
        // Set should accept only the first one and reject the rest
        runTest("All Identical", 
            new String[]{"Data", "Data", "Data"}, 
            new String[]{"Data"});

        // Test 6: Large Data (Performance Check)
        System.out.print("Test: Large Data Input (100,000 items) -> ");
        
        // Generate 50,000 unique strings
        // "Item0", "Item1", etc.
        var largeInput = IntStream.range(0, 50000)
            .mapToObj(i -> "Item" + i)
            // Duplicate every item so we have 100,000 total items
            .flatMap(s -> Arrays.stream(new String[]{s, s}))
            // Convert stream to array for the test input
            .toArray(String[]::new);

        // Capture start time
        var start = System.currentTimeMillis();
        
        // Execute logic on large array
        var result = removeDuplicates(largeInput);
        
        // Capture end time
        var end = System.currentTimeMillis();

        // Validate result size (should be exactly 50,000 unique items)
        if (result.length == 50000) {
            System.out.println("PASS");
            // Show how fast it ran
            System.out.println("   Time taken: " + (end - start) + "ms");
        } else {
            // Fail if size is wrong
            System.out.println("FAIL (Size: " + result.length + ")");
        }
    }
}
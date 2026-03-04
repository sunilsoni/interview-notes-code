package com.interview.notes.code.year.y2026.march.common.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Solution { // Main class wrapper for the solution

    public static List<Long> maxMin(List<String> operations, List<Integer> x) { // Function signature as defined by the problem
        TreeMap<Integer, Integer> elements = new TreeMap<>(); // TreeMap maintains sorted order of unique keys; integer value tracks duplicates
        List<Long> products = new ArrayList<>(); // ArrayList to store the final sequence of min*max products
        
        for (int i = 0; i < operations.size(); i++) { // Iterate over the operations list using a standard index-based loop
            String op = operations.get(i); // Fetch the current operation string ("push" or "pop")
            Integer val = x.get(i); // Fetch the corresponding numerical value for this operation step
            
            if ("push".equals(op)) { // Evaluate if the current instruction is to add an element
                elements.merge(val, 1, Integer::sum); // Java 8+ map feature: adds the key with count 1, or adds 1 to existing count
            } else { // If the operation is not "push", it is guaranteed to be "pop" based on constraints
                if (elements.merge(val, -1, Integer::sum) == 0) { // Decrease frequency by 1; if the resulting frequency is exactly 0...
                    elements.remove(val); // ...remove the key entirely so it doesn't falsely act as a minimum or maximum
                }
            }
            
            long min = elements.firstKey(); // Retrieve the lowest key currently in the TreeMap (always exists due to problem constraints)
            long max = elements.lastKey(); // Retrieve the highest key currently in the TreeMap
            
            products.add(min * max); // Multiply the 64-bit numbers to prevent overflow, then append to result list
        }
        
        return products; // Return the fully populated list of product calculations
    }

    public static void main(String[] args) { // Main method entry point to run standard tests without JUnit
        
        // --- Test Case 1 (From Example 1) ---
        List<String> ops1 = Arrays.asList("push", "push", "push", "pop"); // Setup operation inputs for case 1
        List<Integer> x1 = Arrays.asList(1, 2, 3, 1); // Setup integer inputs for case 1
        List<Long> expected1 = Arrays.asList(1L, 2L, 3L, 6L); // Define the expected correct output
        runTest("Test Case 1", ops1, x1, expected1); // Execute test helper method
        
        // --- Test Case 2 (From Example 2) ---
        List<String> ops2 = Arrays.asList("push", "push", "pop"); // Setup operation inputs for case 2
        List<Integer> x2 = Arrays.asList(1, 1, 1); // Setup integer inputs for case 2 (tests duplicates)
        List<Long> expected2 = Arrays.asList(1L, 1L, 1L); // Define the expected correct output
        runTest("Test Case 2", ops2, x2, expected2); // Execute test helper method

        // --- Test Case 3 (Large Data Edge Case) ---
        int largeSize = 100000; // Define maximum constraint size to ensure performance holds up
        List<String> opsLarge = new ArrayList<>(largeSize); // Pre-allocate list capacity for speed
        List<Integer> xLarge = new ArrayList<>(largeSize); // Pre-allocate list capacity for speed
        for (int i = 0; i < largeSize; i++) { // Loop to generate mock large dataset
            opsLarge.add("push"); // Fill operations purely with pushes
            xLarge.add(1000000000); // Fill with maximum allowed integer values to test multiplication overflow limits
        }
        long startTime = System.currentTimeMillis(); // Track start time to measure execution speed
        List<Long> resultLarge = maxMin(opsLarge, xLarge); // Execute the algorithm against the large mock data
        long endTime = System.currentTimeMillis(); // Track end time
        
        // Validation for Large Data
        boolean largePass = resultLarge.size() == largeSize && resultLarge.get(largeSize - 1) == 1000000000000000000L; // Check size and that 10^9 * 10^9 equals 10^18
        System.out.println("Test Case 3 (Large Data) -> " + (largePass ? "PASS" : "FAIL") + " in " + (endTime - startTime) + "ms"); // Print large test results
    }

    private static void runTest(String testName, List<String> ops, List<Integer> x, List<Long> expected) { // Helper function to keep main method clean
        List<Long> actual = maxMin(ops, x); // Call the core logic
        if (actual.equals(expected)) { // Compare the generated list directly with the expected list
            System.out.println(testName + " -> PASS"); // Print success condition
        } else { // Handle failure condition
            System.out.println(testName + " -> FAIL"); // Print failure status
            System.out.println("   Expected: " + expected); // Print what the system wanted
            System.out.println("   Actual:   " + actual); // Print what the algorithm actually produced
        }
    }
}
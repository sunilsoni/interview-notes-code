package com.interview.notes.code.year.y2026.jan.common.test3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class SecondLargestSolver {

    public static void main(String[] args) {
        // --- Test Case 1: Standard Case ---
        int[] input1 = {12, 5, 34, 5};
        runTestCase(input1, 12, "Standard Case");

        // --- Test Case 2: Unsorted with Duplicates ---
        int[] input2 = {10, 5, 10, 2, 8};
        runTestCase(input2, 8, "Duplicates Case");

        // --- Test Case 3: Negative Numbers ---
        int[] input3 = {-10, -5, -2, -1};
        runTestCase(input3, -2, "Negative Numbers");

        // --- Test Case 4: Already Sorted ---
        int[] input4 = {1, 2, 3, 4, 5};
        runTestCase(input4, 4, "Sorted Ascending");

        // --- Test Case 5: Large Data (Simulation) ---
        // Generating 1 million numbers
        int[] largeInput = IntStream.range(0, 1_000_000).toArray(); 
        // Largest is 999999, 2nd Largest is 999998
        runTestCase(largeInput, 999_998, "Large Data Set");
        
        // --- Test Case 6: Edge Case (Too few elements) ---
        // This expects an exception or specific handling. 
        // For simplicity in this logic, we catch the exception to PASS.
        try {
            findSecondLargest(new int[]{1});
            System.out.println("Edge Case (<2 elements): FAIL (Should have thrown exception)");
        } catch (IllegalArgumentException e) {
            System.out.println("Edge Case (<2 elements): PASS");
        }
    }

    // Logic to run a single test case
    private static void runTestCase(int[] arr, int expected, String testName) {
        try {
            long startTime = System.currentTimeMillis(); // Start timer
            
            int actual = findSecondLargest(arr); // Execute logic
            
            long endTime = System.currentTimeMillis(); // End timer

            if (actual == expected) {
                System.out.println(testName + ": PASS (Time: " + (endTime - startTime) + "ms)");
            } else {
                System.out.println(testName + ": FAIL. Expected " + expected + " but got " + actual);
            }
        } catch (Exception e) {
            System.out.println(testName + ": FAIL due to exception: " + e.getMessage());
        }
    }

    // Core Solution Method
    private static int findSecondLargest(int[] arr) {
        return Arrays.stream(arr)           // 1. Create a stream from the int array
                .distinct()                 // 2. Remove duplicates (essential if max appears twice)
                .boxed()                    // 3. Convert int to Integer to use Comparator
                .sorted(Comparator.reverseOrder()) // 4. Sort descending (Largest to Smallest)
                .skip(1)                    // 5. Skip the first element (the largest)
                .findFirst()                // 6. Grab the next element (the 2nd largest)
                .orElseThrow(() ->          // 7. If no element exists (array too small), throw error
                     new IllegalArgumentException("Array needs at least 2 distinct numbers"));
    }
}
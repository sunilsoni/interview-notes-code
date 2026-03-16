package com.interview.notes.code.year.y2026.march.common.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ShiftMerger { // Defines the main class that contains our logic and tests

    public static int[][] merge(int[][] shifts) { // Method takes a 2D array of shifts and returns the merged 2D array
        if (shifts == null || shifts.length == 0) { // Checks if the input is empty or null to avoid crashing
            return new int[0][]; // Returns an empty 2D array since there is nothing to merge
        } // Closes the null/empty check block
        
        Arrays.sort(shifts, Comparator.comparingInt(a -> a[0])); // Sorts all shifts based on their starting times (index 0)
        
        var merged = new ArrayList<int[]>(); // Uses Java 'var' to create a dynamic list to hold our merged shifts
        var currentShift = shifts[0]; // Grabs the very first shift to start comparing against the rest
        merged.add(currentShift); // Adds this first shift to our results list (we will update it in place if needed)
        
        for (var nextShift : shifts) { // Loops through every single shift in our sorted array
            if (nextShift[0] <= currentShift[1]) { // Checks if the next shift starts before or at the same time the current one ends (Overlap)
                currentShift[1] = Math.max(currentShift[1], nextShift[1]); // Merges them by taking the latest end time of the two
            } else { // Runs if the next shift starts after the current one completely finishes (No overlap)
                currentShift = nextShift; // Updates our tracker to look at this new, separate shift
                merged.add(currentShift); // Adds the new distinct shift to our results list
            } // Closes the else block
        } // Closes the for loop
        
        return merged.toArray(int[][]::new); // Converts our dynamic ArrayList back into a standard 2D array using Java method references
    } // Closes the merge method

    public static void main(String[] args) { // Main method where execution begins; serves as our test runner
        System.out.println("Running Tests...\n"); // Prints a header to the console
        
        // Test Case 1: Provided Example
        var input1 = new int[][]{{2, 8}, {4, 5}, {3, 9}, {12, 15}}; // Defines the input shifts from the problem statement
        var expected1 = new int[][]{{2, 9}, {12, 15}}; // Defines the correct output we expect to see
        runTest("Test 1 (Standard Case)", input1, expected1); // Calls our custom helper method to run and verify the test

        // Test Case 2: Fully Enclosed Shifts
        var input2 = new int[][]{{1, 10}, {2, 3}, {4, 5}, {6, 7}}; // Defines a large shift that completely swallows the smaller ones
        var expected2 = new int[][]{{1, 10}}; // Expects a single massive shift
        runTest("Test 2 (Enclosed Overlaps)", input2, expected2); // Runs the test

        // Test Case 3: No Overlaps At All
        var input3 = new int[][]{{1, 2}, {3, 4}, {5, 6}}; // Defines shifts that are completely separated
        var expected3 = new int[][]{{1, 2}, {3, 4}, {5, 6}}; // Expects the exact same array back
        runTest("Test 3 (No Overlaps)", input3, expected3); // Runs the test

        // Test Case 4: Large Data Input (Stress Test)
        var largeInput = new int[100000][2]; // Creates a massive array of 100,000 shifts to test performance
        for (int i = 0; i < 100000; i++) { // Loops 100,000 times to fill the array with data
            largeInput[i] = new int[]{i, i + 2}; // Creates continuous overlapping shifts (0-2, 1-3, 2-4...)
        } // Closes the loop
        var largeOutput = merge(largeInput); // Processes the massive dataset through our method
        // If 100,000 interlocking shifts merge correctly, we should get exactly one shift from 0 to 100,001
        var passedLarge = largeOutput.length == 1 && largeOutput[0][0] == 0 && largeOutput[0][1] == 100001; // Evaluates if the result matches expectations
        System.out.println("Test 4 (Large Data 100k records): " + (passedLarge ? "PASS" : "FAIL")); // Prints pass or fail for the large test
    } // Closes the main method

    private static void runTest(String testName, int[][] input, int[][] expected) { // Helper method to keep test code clean and repetitive tasks short
        var result = merge(input); // Runs the input through our actual logic
        var passed = Arrays.deepEquals(result, expected); // Uses deepEquals to check if the 2D arrays match exactly
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL")); // Prints the test name and its pass/fail status
    } // Closes the test helper method
} // Closes the class
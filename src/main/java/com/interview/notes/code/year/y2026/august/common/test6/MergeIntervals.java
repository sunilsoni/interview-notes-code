package com.interview.notes.code.year.y2026.august.common.test6;

import java.util.ArrayList; // Required to instantiate a dynamically resizable array list to hold our merged outputs
import java.util.Arrays; // Required to use Arrays.sort() for sorting and Arrays.deepEquals() for verifying test results
import java.util.Comparator; // Required to define a custom sorting rule based on the start time of the intervals
import java.util.List; // Required as the standard interface type for our dynamic list variable

public class MergeIntervals { // Main class declaration to encapsulate the interval merging logic and the testing harness
    
    public static int[][] merge(int[][] intervals) { // Method signature accepting an unmerged 2D array and returning a merged 2D array
        if (intervals.length <= 1) { // Guard clause to handle edge cases where the input is empty or has only one interval
            return intervals; // Return the input immediately since no merging can possibly occur
        } // Closes the guard clause block
        
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // Sorts intervals by their starting element to ensure overlaps are adjacent
        
        List<int[]> merged = new ArrayList<>(); // Initializes an empty List to dynamically store the final merged integer arrays
        merged.add(intervals[0]); // Adds the first sorted interval into the list to serve as the initial baseline for comparison
        
        for (var interval : intervals) { // Iterates sequentially through all intervals using Java 10 'var' for cleaner syntax
            var last = merged.getLast(); // Retrieves the most recently added interval from our list using Java 21's getLast() feature
            
            if (interval[0] <= last[1]) { // Checks if the current interval's start time overlaps with or touches the previous interval's end time
                last[1] = Math.max(last[1], interval[1]); // Resolves the overlap by extending the previous interval's end to the maximum possible value
            } else { // Executes only if the current interval begins strictly after the previous interval ends (meaning no overlap)
                merged.add(interval); // Appends the current independent interval as a brand new, separate entry in our result list
            } // Closes the conditional overlap logic block
        } // Closes the iteration loop over the input array
        
        return merged.toArray(int[][]::new); // Converts the dynamic List back to a static 2D array using Java 11 array generator syntax and returns it
    } // Closes the merge method definition

    public static void main(String[] args) { // Standard entry point method to execute our custom test cases
        
        // Test case 1 based on Screenshot 2026-08-20 at 11.56.21 PM.jpg
        runTest("Test case 1", new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}, new int[][]{{1, 6}, {8, 10}, {15, 18}}); // Standard multi-merge test
        
        // Test case 2 based on Screenshot 2026-08-20 at 11.56.31 PM.jpg
        runTest("Test case 2", new int[][]{{1, 4}, {4, 5}}, new int[][]{{1, 5}}); // Tests adjacent bounds touching
        
        // Test case 3 based on Screenshot 2026-08-20 at 11.56.31 PM.jpg
        runTest("Test case 3", new int[][]{{1, 2}, {5, 6}}, new int[][]{{1, 2}, {5, 6}}); // Tests distinct non-overlapping bounds
        
        // Test case 4 based on Screenshot 2026-08-20 at 11.56.31 PM.jpg
        runTest("Test case 4", new int[][]{{1, 10}}, new int[][]{{1, 10}}); // Tests single element edge case
        
        // Test case 5 based on Screenshot 2026-08-20 at 11.56.40 PM.jpg
        runTest("Test case 5", new int[][]{{1, 10}, {2, 5}}, new int[][]{{1, 10}}); // Tests complete subsumption (one inside another)
        
        // Large data stress test for performance and memory verification
        int[][] largeInput = new int[100000][2]; // Initializes a massive array to hold 100,000 intervals
        for (int i = 0; i < 100000; i++) { // Loops 100,000 times to populate the large array
            largeInput[i] = new int[]{i, i + 2}; // Creates overlapping sequential intervals like [0,2], [1,3], [2,4]...
        } // Closes population loop
        int[][] expectedLarge = new int[][]{{0, 100001}}; // Predicts the final result: one giant merged interval from 0 to 100001
        runTest("Large Data Test", largeInput, expectedLarge); // Executes the stress test to verify O(N) performance
        
        System.out.println("\nDone"); // Prints completion marker mimicking the referenced screenshots
    } // Closes the main method
    
    // Testing helper method modeled verbatim from Screenshot 2026-08-20 at 11.56.46 PM.jpg
    private static void runTest(String testName, int[][] input, int[][] expected) { // Encapsulates the execution and validation of a single test case
        int[][] result = merge(input); // Passes the input array into our target logic and captures the output
        
        boolean passed = Arrays.deepEquals(result, expected); // Uses deepEquals to properly compare inner arrays for structural and value equality
        
        System.out.println((passed ? "PASS" : "FAIL") + " - " + testName); // Prints the binary pass/fail result cleanly to the console
        
        if (!passed) { // Checks if the test failed in order to provide debugging information
            System.out.println("Expected: " + Arrays.deepToString(expected)); // Prints the baseline expected result for manual comparison
            System.out.println("Actual  : " + Arrays.deepToString(result)); // Prints the incorrect generated result for manual comparison
        } // Closes the failure formatting block
    } // Closes the runTest helper method
} // Closes the main class
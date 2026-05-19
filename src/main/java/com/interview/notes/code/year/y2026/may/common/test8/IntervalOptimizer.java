package com.interview.notes.code.year.y2026.may.common.test8;

import java.util.Arrays; // Required to use the built-in array sorting methods
import java.util.Comparator; // Required to define custom sorting logic for our 2D array

public class IntervalOptimizer { // Main class to encapsulate our solution logic

    public static int eraseOverlapIntervals(int[][] intervals) { // Method taking a 2D array, returning the int count of removals
        if (intervals.length == 0) return 0; // Edge case check: if the array is empty, exactly 0 removals are needed
        
        // Sort intervals by their end time (index 1) to greedily leave maximum space for subsequent intervals
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1])); // Uses Java 8 lambda to extract the end time for comparison
        
        var removed = 0; // Uses Java local variable inference (var) to create an integer tracking how many we delete
        var end = intervals[0][1]; // Tracks the end time of the last "safe" interval, starting with the very first one
        
        for (var i = 1; i < intervals.length; i++) { // Loop through the remaining intervals, starting from the second item (index 1)
            if (intervals[i][0] < end) removed++; // If current start time is BEFORE previous end time, it overlaps; increment removal count
            else end = intervals[i][1]; // Otherwise, there is no overlap; update our 'end' tracker to this new interval's end time
        } // End of the checking loop
        
        return removed; // Return the final tally of intervals we had to remove
    } // End of the eraseOverlapIntervals method

    public static void main(String[] args) { // Main method used strictly for running our own tests without JUnit
        
        // Test Case 1: The primary example from the problem description
        test(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}, 1, "Example 1"); // Passes arrays and expects 1 removal
        
        // Test Case 2: Scenario where every single interval is exactly the same
        test(new int[][]{{1, 2}, {1, 2}, {1, 2}}, 2, "All Overlap"); // Needs to remove 2 out of 3 to leave 1 valid interval
        
        // Test Case 3: Scenario where intervals naturally do not overlap at all
        test(new int[][]{{1, 2}, {2, 3}}, 0, "No Overlap"); // Needs 0 removals since touching [2] is allowed
        
        // Test Case 4: Performance testing with a massive dataset to ensure time complexity is optimal
        var largeData = new int[100000][2]; // Allocate memory for one hundred thousand interval pairs
        for (var i = 0; i < 100000; i++) largeData[i] = new int[]{i, i + 2}; // Fill array with overlapping data like [0,2], [1,3], [2,4]
        test(largeData, 50000, "Large Data (100k elements)"); // Expect exactly half (50,000) to be removed to resolve staggered overlaps
        
    } // End of main test execution method

    private static void test(int[][] input, int expected, String testName) { // Helper method to handle test processing and console output
        var result = eraseOverlapIntervals(input); // Execute the algorithm with the provided test input
        var status = (result == expected) ? "PASS" : "FAIL"; // Use ternary operator to determine if the result matches expectations
        System.out.println(testName + " -> " + status + " (Expected: " + expected + ", Got: " + result + ")"); // Print formatted result
    } // End of test helper method

} // End of class
package com.interview.notes.code.year.y2025.november.wallmart.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LowestStartingStair {

    // Function to find the minimum starting stair where grasshopper never goes below stair 1
    public static int findLowestStartingStair(List<Integer> jumps) {
        
        // Initialize variable to track cumulative sum of all jumps encountered so far
        // This represents the net movement from starting position after each jump
        int cumulativeSum = 0;
        
        // Initialize variable to store the minimum cumulative sum encountered during iteration
        // This tells us the lowest point the grasshopper reaches during jumping sequence
        // We start with 0 because before any jump, cumulative sum is 0
        int minCumulativeSum = 0;
        
        // Iterate through each jump value in the jumps array using Stream API forEach method
        // For each jump, we calculate the running total to track position changes
        for (int jump : jumps) {
            // Add current jump value to cumulative sum to get new running total
            // Positive jump means move up, negative means move down
            cumulativeSum += jump;
            
            // Update minCumulativeSum if current cumulative sum is lower than previous minimum
            // This finds the lowest position reached at any point during all jumps
            minCumulativeSum = Math.min(minCumulativeSum, cumulativeSum);
        }
        
        // Calculate minimum starting stair using formula:
        // If starting at position S, final position = S + cumulativeSum
        // At any point, position = S + cumulativeSum_at_that_point
        // We need: S + minCumulativeSum >= 1 (never go below stair 1)
        // Therefore: S >= 1 - minCumulativeSum
        // Return the minimum S that satisfies this condition
        return 1 - minCumulativeSum;
    }

    // Main method to test all test cases with PASS/FAIL output
    public static void main(String[] args) {
        
        // Counter variables to track passing and failing tests
        // Used to generate final summary report at end of execution
        int passedCount = 0;
        int failedCount = 0;
        
        // ============== BASIC TEST CASES ==============
        
        // Test Case 1: Problem example with mixed positive and negative jumps
        // Starting at 6: 6 + 1 = 7, 7 + (-4) = 3, 3 + (-2) = 1, 1 + 3 = 4
        // Minimum reached is 1, so 6 is valid and is the lowest possible
        List<Integer> testCase1 = Arrays.asList(1, -4, -2, 3);
        int result1 = findLowestStartingStair(testCase1);
        boolean testPass1 = (result1 == 6);
        System.out.println("Test 1 - Mixed jumps: " + (testPass1 ? "PASS" : "FAIL") + 
                           " | Expected: 6, Got: " + result1);
        passedCount += testPass1 ? 1 : 0;
        failedCount += testPass1 ? 0 : 1;
        
        // Test Case 2: All positive jumps means no descent needed
        // Cumulative sum is always positive, so minimum starting stair is 1
        List<Integer> testCase2 = Arrays.asList(1, 2, 3);
        int result2 = findLowestStartingStair(testCase2);
        boolean testPass2 = (result2 == 1);
        System.out.println("Test 2 - All positive: " + (testPass2 ? "PASS" : "FAIL") + 
                           " | Expected: 1, Got: " + result2);
        passedCount += testPass2 ? 1 : 0;
        failedCount += testPass2 ? 0 : 1;
        
        // Test Case 3: Large negative jump first
        // After -10, need to be at least at 11 to not go below 1
        List<Integer> testCase3 = Arrays.asList(-10, 5);
        int result3 = findLowestStartingStair(testCase3);
        boolean testPass3 = (result3 == 11);
        System.out.println("Test 3 - Large negative: " + (testPass3 ? "PASS" : "FAIL") + 
                           " | Expected: 11, Got: " + result3);
        passedCount += testPass3 ? 1 : 0;
        failedCount += testPass3 ? 0 : 1;
        
        // Test Case 4: Single negative jump
        // Start at 6, jump -5 reaches stair 1, which is minimum allowed
        List<Integer> testCase4 = List.of(-5);
        int result4 = findLowestStartingStair(testCase4);
        boolean testPass4 = (result4 == 6);
        System.out.println("Test 4 - Single jump: " + (testPass4 ? "PASS" : "FAIL") + 
                           " | Expected: 6, Got: " + result4);
        passedCount += testPass4 ? 1 : 0;
        failedCount += testPass4 ? 0 : 1;
        
        // ============== EDGE CASES ==============
        
        // Test Case 5: Zero jump (no movement)
        // Position stays same, minimum starting stair is 1
        List<Integer> testCase5 = Arrays.asList(0, 0, 0);
        int result5 = findLowestStartingStair(testCase5);
        boolean testPass5 = (result5 == 1);
        System.out.println("Test 5 - Zero jumps: " + (testPass5 ? "PASS" : "FAIL") + 
                           " | Expected: 1, Got: " + result5);
        passedCount += testPass5 ? 1 : 0;
        failedCount += testPass5 ? 0 : 1;
        
        // Test Case 6: Extreme negative value
        // Jump of -100 means need to start at 101 to reach stair 1
        List<Integer> testCase6 = List.of(-100);
        int result6 = findLowestStartingStair(testCase6);
        boolean testPass6 = (result6 == 101);
        System.out.println("Test 6 - Extreme negative: " + (testPass6 ? "PASS" : "FAIL") + 
                           " | Expected: 101, Got: " + result6);
        passedCount += testPass6 ? 1 : 0;
        failedCount += testPass6 ? 0 : 1;
        
        // ============== LARGE DATA TEST CASES ==============
        
        // Test Case 7: Large array with many jumps (10,000 elements)
        // Generate alternating +1 and -2 to test algorithm with large dataset
        // This simulates realistic large data input to verify performance
        List<Integer> testCase7 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            // Add pattern of +1, -2, +1, -2, ... to create varied cumulative sums
            testCase7.add(i % 2 == 0 ? 1 : -2);
        }
        int result7 = findLowestStartingStair(testCase7);
        // With pattern +1,-2 repeated, minimum cumulative sum after each pair is -1
        // After 10000 elements (5000 pairs), minimum cumulative is approximately -5000
        boolean testPass7 = (result7 > 1);
        System.out.println("Test 7 - Large array (10K): " + (testPass7 ? "PASS" : "FAIL") + 
                           " | Result: " + result7);
        passedCount += testPass7 ? 1 : 0;
        failedCount += testPass7 ? 0 : 1;
        
        // Test Case 8: Very large array with extreme negative values (100,000 elements)
        // This tests memory efficiency and algorithm correctness with massive input
        List<Integer> testCase8 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            // Add repeating -1 to accumulate large negative sum
            testCase8.add(-1);
        }
        int result8 = findLowestStartingStair(testCase8);
        boolean testPass8 = (result8 == 100001);
        System.out.println("Test 8 - Very large array (100K): " + (testPass8 ? "PASS" : "FAIL") + 
                           " | Expected: 100001, Got: " + result8);
        passedCount += testPass8 ? 1 : 0;
        failedCount += testPass8 ? 0 : 1;
        
        // Test Case 9: Complex pattern with local minima
        // Tests if algorithm correctly identifies global minimum, not local minima
        List<Integer> testCase9 = Arrays.asList(5, -3, 2, -8, 1, -2);
        int result9 = findLowestStartingStair(testCase9);
        // Cumulative: 5, 2, 4, -4, -3, -5 -> minimum is -5
        // Starting stair needed: 1 - (-5) = 6
        boolean testPass9 = (result9 == 6);
        System.out.println("Test 9 - Complex pattern: " + (testPass9 ? "PASS" : "FAIL") + 
                           " | Expected: 6, Got: " + result9);
        passedCount += testPass9 ? 1 : 0;
        failedCount += testPass9 ? 0 : 1;
        
        // Test Case 10: Negative then recovery (tests global minimum detection)
        // Ensure we find lowest point in middle, not just final state
        List<Integer> testCase10 = Arrays.asList(-20, 15);
        int result10 = findLowestStartingStair(testCase10);
        // Minimum cumulative sum is -20 (after first jump)
        // Starting stair needed: 1 - (-20) = 21
        boolean testPass10 = (result10 == 21);
        System.out.println("Test 10 - Negative then recovery: " + (testPass10 ? "PASS" : "FAIL") + 
                           " | Expected: 21, Got: " + result10);
        passedCount += testPass10 ? 1 : 0;
        failedCount += testPass10 ? 0 : 1;
        
        // ============== FINAL TEST SUMMARY REPORT ==============
        
        // Print separator for clarity
        System.out.println("\n" + "=".repeat(50));
        
        // Print summary statistics showing pass/fail counts
        System.out.println("TEST SUMMARY REPORT");
        System.out.println("=".repeat(50));
        System.out.println("Total Tests Run: " + (passedCount + failedCount));
        System.out.println("Passed: " + passedCount);
        System.out.println("Failed: " + failedCount);
        System.out.println("Success Rate: " + 
                           String.format("%.1f%%", (passedCount * 100.0 / (passedCount + failedCount))));
        System.out.println("=".repeat(50));
        
        // Final status message indicating whether all tests passed
        if (failedCount == 0) {
            System.out.println("✓ ALL TESTS PASSED");
        } else {
            System.out.println("✗ SOME TESTS FAILED - Review output above");
        }
    }
}
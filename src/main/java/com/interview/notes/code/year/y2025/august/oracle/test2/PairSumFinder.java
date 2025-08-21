package com.interview.notes.code.year.y2025.august.oracle.test2;

import java.util.*;
import java.util.stream.*;

public class PairSumFinder {
    
    // Main method to find pairs that sum to target value using Java 8 Streams
    public static List<int[]> findPairsUsingStreams(int[] arr, int targetSum) {
        // Convert array to set for O(1) lookup time
        Set<Integer> numSet = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        
        // Use streams to process array and find pairs
        return Arrays.stream(arr)
            // Filter to avoid duplicate pairs (only take first number less than complement)
            .filter(num -> num <= targetSum - num)
            // Find pairs where complement exists in set
            .filter(num -> numSet.contains(targetSum - num) && num != targetSum - num)
            // Create pair arrays for matches
            .mapToObj(num -> new int[]{num, targetSum - num})
            // Collect results to list
            .collect(Collectors.toList());
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Basic case with multiple pairs
        runTest(
            new int[]{1, 2, 3, 4, 5, 6}, 
            7,
            "Basic case with multiple pairs"
        );

        // Test Case 2: No pairs exist
        runTest(
            new int[]{1, 2, 3, 4}, 
            10,
            "No pairs exist"
        );

        // Test Case 3: Single pair
        runTest(
            new int[]{1, 9}, 
            10,
            "Single pair"
        );

        // Test Case 4: Large array test
        runTest(
            generateLargeArray(100000), 
            199998,
            "Large array test"
        );
    }

    // Helper method to run tests and print results
    private static void runTest(int[] arr, int target, String testName) {
        System.out.println("\nRunning test: " + testName);
        long startTime = System.currentTimeMillis();
        
        List<int[]> pairs = findPairsUsingStreams(arr, target);
        
        long endTime = System.currentTimeMillis();
        
        // Print results
        System.out.println("Found " + pairs.size() + " pairs");
        pairs.forEach(pair -> 
            System.out.println("Pair: (" + pair[0] + "," + pair[1] + ")")
        );
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        
        // Verify pairs
        boolean allValid = pairs.stream()
            .allMatch(pair -> pair[0] + pair[1] == target);
        
        System.out.println("Test " + (allValid ? "PASSED" : "FAILED"));
    }

    // Helper method to generate large test array
    private static int[] generateLargeArray(int size) {
        return IntStream.range(0, size).toArray();
    }
}

package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.*;
import java.util.stream.*;

public class MissingNumbersFinder {

    // Method to find missing numbers in the range 0..n
    public static List<Integer> findMissingNumbers(int[] nums) {
        // Step 1: Find the maximum value 'n' in the array
        int max = Arrays.stream(nums).max().orElse(0); 
        // We use orElse(0) to handle the case of empty array
        
        // Step 2: Store all numbers in a Set for O(1) lookups
        Set<Integer> numSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        
        // Step 3: Generate numbers from 0 to max, filter those not in the set, collect into a list
        return IntStream.rangeClosed(0, max)
                .filter(i -> !numSet.contains(i))  // keep only missing numbers
                .boxed()                           // convert int to Integer
                .collect(Collectors.toList());     // collect result
    }

    // Simple test method to validate results
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {0, 2},           // Expected: [1]
            {2, 1, 0},        // Expected: []
            {5, 5, 2},        // Expected: [0, 1, 3, 4]
            {},               // Expected: []
            {0},              // Expected: []
            {3, 0, 1},        // Expected: [2]
            {10000}           // Large case: Expected: [0..9999]
        };

        List<List<Integer>> expectedResults = Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(),
            Arrays.asList(0, 1, 3, 4),
            Arrays.asList(),
            Arrays.asList(),
            Arrays.asList(2),
            IntStream.range(0, 10000).boxed().collect(Collectors.toList())
        );

        // Run and check results
        for (int i = 0; i < testCases.length; i++) {
            List<Integer> result = findMissingNumbers(testCases[i]);
            boolean pass = result.equals(expectedResults.get(i));
            System.out.println("Test " + (i + 1) + " => " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("Expected: " + expectedResults.get(i));
                System.out.println("Got     : " + result);
            }
        }
    }
}
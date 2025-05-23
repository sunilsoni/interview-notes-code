package com.interview.notes.code.year.y2025.may.common.test10;

import java.util.HashSet;
import java.util.Set;

public class PairFinder {
    public static void findPairsWithSum(int[] arr, int targetSum) {
        // Use HashSet to store seen numbers and avoid duplicates
        Set<Integer> seen = new HashSet<>();
        // Use HashSet to store found pairs to avoid duplicate pairs
        Set<String> foundPairs = new HashSet<>();

        // Iterate through array once - O(n) time complexity
        for (int num : arr) {
            // Calculate the complement needed to reach target sum
            int complement = targetSum - num;

            // If we've seen the complement before, we found a pair
            if (seen.contains(complement)) {
                // Create pair string with smaller number first for consistency
                String pair = num < complement ?
                        num + " & " + complement :
                        complement + " & " + num;

                // Only add if this exact pair hasn't been found before
                foundPairs.add(pair);
            }

            // Add current number to seen set
            seen.add(num);
        }

        // Print all unique pairs found
        int pairCount = 1;
        for (String pair : foundPairs) {
            System.out.println("pair " + pairCount++ + " = " + pair);
        }
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Given example
        int[] test1 = {72, 8, 15, 63, 17, 10, 70, 1, 2, 3, 8};
        System.out.println("Test Case 1:");
        findPairsWithSum(test1, 80);

        // Test Case 2: Empty array
        int[] test2 = {};
        System.out.println("\nTest Case 2 (Empty array):");
        findPairsWithSum(test2, 80);

        // Test Case 3: No pairs found
        int[] test3 = {1, 2, 3, 4, 5};
        System.out.println("\nTest Case 3 (No pairs):");
        findPairsWithSum(test3, 80);

        // Test Case 4: Array with duplicates
        int[] test4 = {40, 40, 40, 40};
        System.out.println("\nTest Case 4 (Duplicates):");
        findPairsWithSum(test4, 80);

        // Test Case 5: Large array
        int[] test5 = new int[10000];
        for (int i = 0; i < test5.length; i++) {
            test5[i] = i;
        }
        System.out.println("\nTest Case 5 (Large array):");
        findPairsWithSum(test5, 80);
    }
}

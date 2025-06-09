package com.interview.notes.code.year.y2025.may.amazon.test9;

import java.util.*;

public class AllPairsSumFinder {

    // Method to find all unique pairs that sum up to the target without using Java 8 streams
    public static List<int[]> findAllPairs(int[] arr, int target) {
        List<int[]> pairs = new ArrayList<>(); // Store result pairs
        Set<Integer> seen = new HashSet<>(); // Track seen elements
        Set<String> uniquePairs = new HashSet<>(); // To avoid duplicate pairs

        for (int num : arr) {
            int complement = target - num; // Required number to reach target sum

            if (seen.contains(complement)) {
                // Form a sorted key to ensure uniqueness of the pair
                int first = Math.min(num, complement);
                int second = Math.max(num, complement);
                String pairKey = first + ":" + second;

                if (!uniquePairs.contains(pairKey)) {
                    pairs.add(new int[]{first, second});
                    uniquePairs.add(pairKey);
                }
            }
            seen.add(num); // Add current number to set
        }

        return pairs;
    }

    // Main method to run test cases without using any testing framework
    public static void main(String[] args) {
        // Test Case 1
        int[] testArr = {4, 5, 6, 7, 8, 3};
        int target = 11;
        List<int[]> result = findAllPairs(testArr, target);
        System.out.println("Test Case 1 (target=11):");
        for (int[] pair : result) {
            System.out.println(Arrays.toString(pair));
        }

        // Test Case 2 - Large array
        int size = 1_000_000;
        int[] largeArr = new int[size];
        for (int i = 0; i < size; i++) largeArr[i] = i + 1;
        int largeTarget = 1_999_999;
        List<int[]> largeResult = findAllPairs(largeArr, largeTarget);
        System.out.println("Test Case 2 (Large array): " + largeResult.size() + " pair(s) found.");

        // Test Case 3 - No pairs
        int[] noPairsArr = {1, 2, 3};
        int noPairsTarget = 10;
        List<int[]> noPairsResult = findAllPairs(noPairsArr, noPairsTarget);
        System.out.println("Test Case 3 (No pairs): " + (noPairsResult.isEmpty() ? "PASS" : "FAIL"));
    }
}
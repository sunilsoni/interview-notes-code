package com.interview.notes.code.year.y2025.may.amazon.test10;

import java.util.*;
import java.util.stream.*;

public class PairSumFinder {

    // Method to find a pair of numbers that adds up to a given target
    public static Optional<int[]> findPairWithSum(int[] arr, int target) {
        // Set to store numbers already seen
        Set<Integer> seen = new HashSet<>();

        // Iterate over the array using Java 8 Stream
        return Arrays.stream(arr)
            .mapToObj(num -> {
                // Check if the complement (target - num) is already seen
                if (seen.contains(target - num)) {
                    // If complement found, return the pair
                    return new int[]{num, target - num};
                }
                // Add current number to seen numbers
                seen.add(num);
                // Return null if no pair is found yet
                return null;
            })
            // Filter out nulls (no pair found cases)
            .filter(Objects::nonNull)
            // Return the first found pair
            .findFirst();
    }

    // Simple test method to verify correctness
    public static void main(String[] args) {
        int[] testArr = {4, 5, 6, 7, 8, 3};
        int target = 11;

        Optional<int[]> result = findPairWithSum(testArr, target);

        if (result.isPresent()) {
            System.out.println("Pair found: " + Arrays.toString(result.get()) + " => PASS");
        } else {
            System.out.println("No pair found => FAIL");
        }

        // Additional edge case: large input
        int[] largeTestArr = IntStream.rangeClosed(1, 1_000_000).toArray();
        int largeTarget = 1_999_999; // Should return (999999, 1000000)
        Optional<int[]> largeResult = findPairWithSum(largeTestArr, largeTarget);

        if (largeResult.isPresent()) {
            System.out.println("Large array pair: " + Arrays.toString(largeResult.get()) + " => PASS");
        } else {
            System.out.println("No pair found in large array => FAIL");
        }

        // Edge case: no pair exists
        int[] noPairArr = {1, 2, 3};
        int noPairTarget = 10;
        Optional<int[]> noPairResult = findPairWithSum(noPairArr, noPairTarget);

        if (noPairResult.isPresent()) {
            System.out.println("Unexpected pair found => FAIL");
        } else {
            System.out.println("Correctly identified no pair exists => PASS");
        }
    }
}

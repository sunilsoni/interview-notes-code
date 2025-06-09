package com.interview.notes.code.year.y2025.may.amazon.test10;

import java.util.*;
import java.util.stream.*;

public class AllPairsSumFinder {

    // Method to find all unique pairs that sum up to the target
    public static List<int[]> findAllPairs(int[] arr, int target) {
        List<int[]> pairs = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        Set<String> uniquePairs = new HashSet<>(); // to avoid duplicate pairs

        Arrays.stream(arr).forEach(num -> {
            int complement = target - num; // Find the number needed to reach target sum

            if (seen.contains(complement)) {
                // Generate a unique string identifier for the pair to avoid duplicates
                String sortedPair = num < complement ? num + ":" + complement : complement + ":" + num;
                if (!uniquePairs.contains(sortedPair)) {
                    pairs.add(new int[]{num, complement});
                    uniquePairs.add(sortedPair);
                }
            }
            seen.add(num); // Add the current number to seen for future complement checking
        });

        return pairs;
    }

    // Simple testing method without external libraries
    public static void main(String[] args) {
        int[] testArr = {4, 5, 6, 7, 8, 3};
        int target = 11;

        List<int[]> result = findAllPairs(testArr, target);
        System.out.println("Pairs summing to " + target + ":");
        result.forEach(pair -> System.out.println(Arrays.toString(pair)));

        // Edge case: large array
        int[] largeArr = IntStream.rangeClosed(1, 1_000_000).toArray();
        int largeTarget = 1_999_999;

        List<int[]> largeResult = findAllPairs(largeArr, largeTarget);
        System.out.println("Pairs in large array: " + largeResult.size()); // Expect 1 pair

        // Edge case: no pairs
        int[] noPairsArr = {1, 2, 3};
        int noPairsTarget = 10;
        List<int[]> noPairsResult = findAllPairs(noPairsArr, noPairsTarget);
        System.out.println("No pairs found: " + noPairsResult.isEmpty()); // Expect true
    }
}

package com.interview.notes.code.year.y2026.september.common.test1;

import java.util.HashSet;
import java.util.Set;

// Define a record to hold a pair; records automatically generate equals() and hashCode()
record Pair(int first, int second) {
    // Canonical constructor ensures smaller number is always first for consistent uniqueness
    Pair(int first, int second) {
        // Assign smaller value to first field
        this.first = Math.min(first, second);
        // Assign larger value to second field
        this.second = Math.max(first, second);
    }
}

public class BruteForceTwoSum {

    // Method to find all pairs that sum to target using brute force
    public static Set<Pair> findPairsBruteForce(int[] nums, int target) {
        // Create a set to store unique pairs and eliminate duplicate pairs automatically
        Set<Pair> result = new HashSet<>();

        // If array has fewer than 2 elements, we cannot form any pair
        if (nums == null || nums.length < 2) {
            // Return empty set immediately
            return result;
        }

        // Outer loop: iterate through each element as the first candidate
        for (int i = 0; i < nums.length; i++) {
            // Inner loop: check every subsequent element as the second candidate
            for (int j = i + 1; j < nums.length; j++) {
                // Check if the sum of current two numbers equals the target
                if (nums[i] + nums[j] == target) {
                    // Create and add pair to the set; Set handles duplicates automatically
                    result.add(new Pair(nums[i], nums[j]));
                }
            }
        }

        // Return the set containing all unique pairs found
        return result;
    }
}
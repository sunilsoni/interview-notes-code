package com.interview.notes.code.year.y2025.august.common.test14;

import java.util.HashSet;
import java.util.Set;

public class PairSumOptimized {
    public static void main(String[] args) {
        int[] arr = {6, 5, 4, 2, 1, 3};
        int target = 7;

        System.out.println("Pairs with sum = " + target + " are:");

        Set<Integer> seen = new HashSet<>();  // to store visited numbers
        Set<String> printed = new HashSet<>(); // to avoid duplicate prints

        for (int num : arr) {
            int complement = target - num;

            // If complement already exists in set, print the pair
            if (seen.contains(complement)) {
                // Ensure consistent ordering (smaller first)
                String pair = Math.min(num, complement) + "," + Math.max(num, complement);
                if (!printed.contains(pair)) {
                    System.out.println(num + " + " + complement + " = " + target);
                    printed.add(pair);
                }
            }
            seen.add(num);
        }
    }
}
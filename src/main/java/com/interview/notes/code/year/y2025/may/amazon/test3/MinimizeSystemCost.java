package com.interview.notes.code.year.y2025.may.amazon.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MinimizeSystemCost {

    public static long minimizeSystemCost(int k, List<Integer> machines) {
        int n = machines.size();

        // If k is n-1, then only one machine remains, so cost is 0
        if (k == n - 1) {
            return 0;
        }

        // Calculate the original connection costs between adjacent machines
        List<Integer> costs = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            costs.add(Math.abs(machines.get(i + 1) - machines.get(i)));
        }

        // Calculate the total cost without any removal
        long totalCost = costs.stream().mapToLong(Integer::longValue).sum();

        // Try removing each possible subarray of length k
        long minCost = Long.MAX_VALUE;

        for (int i = 0; i <= n - k; i++) {
            long newCost = totalCost;

            // Remove costs of connections that will be affected
            if (i > 0) {
                newCost -= costs.get(i - 1); // Remove cost before the subarray
            }

            // Remove all costs within the subarray
            for (int j = i; j < i + k - 1; j++) {
                newCost -= costs.get(j);
            }

            if (i + k < n) {
                newCost -= costs.get(i + k - 1); // Remove cost after the subarray
            }

            // Add new connection cost (if applicable)
            if (i > 0 && i + k < n) {
                newCost += Math.abs(machines.get(i + k) - machines.get(i - 1));
            }

            minCost = Math.min(minCost, newCost);
        }

        return minCost;
    }

    public static void main(String[] args) {
        // Test case 1
        List<Integer> machines1 = Arrays.asList(3, 7, 1, 11, 8);
        int k1 = 2;
        long expected1 = 5;
        long result1 = minimizeSystemCost(k1, machines1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL") +
                " (Expected: " + expected1 + ", Got: " + result1 + ")");

        // Test case 2
        List<Integer> machines2 = Arrays.asList(3, 2, 6, 1);
        int k2 = 3;
        long expected2 = 0;
        long result2 = minimizeSystemCost(k2, machines2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL") +
                " (Expected: " + expected2 + ", Got: " + result2 + ")");

        // Edge case: k = n-1 (only one machine left)
        List<Integer> machines3 = Arrays.asList(5, 10, 15);
        int k3 = 2;
        long expected3 = 0;
        long result3 = minimizeSystemCost(k3, machines3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "PASS" : "FAIL") +
                " (Expected: " + expected3 + ", Got: " + result3 + ")");

        // Large input test
        List<Integer> machines4 = new ArrayList<>();
        Random rand = new Random(42); // Use seed for reproducibility
        for (int i = 0; i < 100000; i++) {
            machines4.add(rand.nextInt(1000000000) + 1);
        }
        int k4 = 50000;
        long start = System.currentTimeMillis();
        long result4 = minimizeSystemCost(k4, machines4);
        long end = System.currentTimeMillis();
        System.out.println("Large Test Case: Completed in " + (end - start) + "ms with result " + result4);

        // Additional test case
        List<Integer> machines5 = Arrays.asList(10, 20, 30, 40, 50);
        int k5 = 2;
        long expected5 = 30;
        long result5 = minimizeSystemCost(k5, machines5);
        System.out.println("Test Case 5: " + (result5 == expected5 ? "PASS" : "FAIL") +
                " (Expected: " + expected5 + ", Got: " + result5 + ")");
    }
}
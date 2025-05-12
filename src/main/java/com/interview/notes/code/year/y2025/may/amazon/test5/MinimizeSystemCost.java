package com.interview.notes.code.year.y2025.may.amazon.test5;

import java.util.*;
import java.util.stream.*;

public class MinimizeSystemCost {

    public static long minimizeSystemCost(int k, List<Integer> machines) {
        int n = machines.size();
        if (k >= n - 1) return 0;

        long[] cost = new long[n - 1];
        for (int i = 0; i < n - 1; i++) {
            cost[i] = Math.abs(machines.get(i + 1) - machines.get(i));
        }

        long totalCost = Arrays.stream(cost).sum();

        // Find the maximum sum of cost to remove
        long windowCost = 0;
        for (int i = 0; i < k - 1; i++) {
            windowCost += cost[i];
        }

        long maxRemovalCost = windowCost;
        for (int i = k - 1; i < n - 1; i++) {
            windowCost += cost[i];
            if (i >= k) windowCost -= cost[i - k];

            long removalCost = windowCost;

            if (i - k + 1 > 0 && i + 1 < n) {
                removalCost += Math.abs(machines.get(i - k + 1) - machines.get(i + 1));
                removalCost -= cost[i - k];
                removalCost -= cost[i];
            }

            maxRemovalCost = Math.max(maxRemovalCost, removalCost);
        }

        return totalCost - maxRemovalCost;
    }

    public static void main(String[] args) {
        // Provided Test Cases
        test(2, Arrays.asList(3, 7, 1, 11, 8), 5);
        test(3, Arrays.asList(3, 2, 6, 1), 0);

        // Additional Edge Cases
        test(1, Arrays.asList(10, 20), 0);
        test(1, Arrays.asList(1, 3, 6, 10, 15), 9);
        test(4, Arrays.asList(5, 5, 5, 5, 5, 5, 5), 0);

        // Large Input Test
        List<Integer> largeInput = IntStream.range(0, 200000)
                .boxed()
                .collect(Collectors.toList());
        test(100000, largeInput, 99999);
    }

    private static void test(int k, List<Integer> machines, long expected) {
        long result = minimizeSystemCost(k, machines);
        System.out.println("Test case k=" + k + ": " + (result == expected ? "PASS" : "FAIL") +
                           " | Expected=" + expected + " Got=" + result);
    }
}
package com.interview.notes.code.year.y2025.may.amazon.test4;

import java.util.*;
import java.util.stream.*;

public class MinimizeSystemCost {

    public static long minimizeSystemCost(int k, List<Integer> machines) {
        int n = machines.size();
        if (k >= n - 1) return 0;

        long totalCost = 0;
        for (int i = 0; i < n - 1; i++) {
            totalCost += Math.abs(machines.get(i + 1) - machines.get(i));
        }

        long maxSavings = 0;
        for (int i = 0; i <= n - k; i++) {
            long savings = 0;
            if (i > 0) savings += Math.abs(machines.get(i) - machines.get(i - 1));
            if (i + k < n) savings += Math.abs(machines.get(i + k) - machines.get(i + k - 1));
            if (i > 0 && i + k < n) savings -= Math.abs(machines.get(i + k) - machines.get(i - 1));
            maxSavings = Math.max(maxSavings, savings);
        }

        return totalCost - maxSavings;
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

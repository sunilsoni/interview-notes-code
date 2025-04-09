package com.interview.notes.code.year.y2025.march.amazon.test11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetMinConnectionCost {

    public static List<Long> getMinConnectionCost(List<Integer> warehouseCapacity, List<List<Integer>> additionalHubs) {
        int n = warehouseCapacity.size();
        List<Long> results = new ArrayList<>();

        // Process each query independently
        for (List<Integer> query : additionalHubs) {
            // Convert 1-based indexing to 0-based indexing
            int hub1 = query.get(0) - 1;
            int hub2 = query.get(1) - 1;

            long totalCost = 0;

            // For each warehouse, find the nearest hub at or beyond its position
            for (int i = 0; i < n - 1; i++) {
                if (i == hub1 || i == hub2) {
                    // Current warehouse is itself a hub, no connection cost
                    continue;
                }

                // Find nearest hub
                int hubPosition;
                if (i < hub1) {
                    hubPosition = hub1;
                } else if (i < hub2) {
                    hubPosition = hub2;
                } else {
                    hubPosition = n - 1; // Central hub at last warehouse
                }

                // Calculate cost
                totalCost += warehouseCapacity.get(hubPosition) - warehouseCapacity.get(i);
            }

            results.add(totalCost);
        }

        return results;
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> warehouses1 = Arrays.asList(3, 6, 10, 15, 20);
        List<List<Integer>> queries1 = new ArrayList<>();
        queries1.add(Arrays.asList(2, 4));
        List<Long> expected1 = Arrays.asList(8L);
        List<Long> result1 = getMinConnectionCost(warehouses1, queries1);
        System.out.println("Test Case 1: " + (expected1.equals(result1) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected1);
        System.out.println("Result: " + result1);

        // Test Case 2
        List<Integer> warehouses2 = Arrays.asList(2, 6, 8, 14);
        List<List<Integer>> queries2 = new ArrayList<>();
        queries2.add(Arrays.asList(1, 2));
        List<Long> expected2 = Arrays.asList(6L);
        List<Long> result2 = getMinConnectionCost(warehouses2, queries2);
        System.out.println("Test Case 2: " + (expected2.equals(result2) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);

        // Test Case 3
        List<Integer> warehouses3 = Arrays.asList(0, 2, 5, 9, 12, 18);
        List<List<Integer>> queries3 = new ArrayList<>();
        queries3.add(Arrays.asList(2, 5));
        queries3.add(Arrays.asList(1, 3));
        List<Long> expected3 = Arrays.asList(12L, 18L);
        List<Long> result3 = getMinConnectionCost(warehouses3, queries3);
        System.out.println("Test Case 3: " + (expected3.equals(result3) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected3);
        System.out.println("Result: " + result3);

        // Additional test for large data
        int n = 100000;
        List<Integer> largeWarehouses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            largeWarehouses.add(i);
        }
        List<List<Integer>> largeQueries = new ArrayList<>();
        largeQueries.add(Arrays.asList(n / 3, 2 * n / 3));
        long startTime = System.currentTimeMillis();
        getMinConnectionCost(largeWarehouses, largeQueries);
        long endTime = System.currentTimeMillis();
        System.out.println("Large data test completed in: " + (endTime - startTime) + "ms");
    }
}

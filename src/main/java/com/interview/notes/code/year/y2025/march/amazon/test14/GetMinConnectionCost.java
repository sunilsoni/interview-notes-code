package com.interview.notes.code.year.y2025.march.amazon.test14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetMinConnectionCost {
    public static List<Long> getMinConnectionCost(List<Integer> warehouseCapacity, List<List<Integer>> additionalHubs) {
        int n = warehouseCapacity.size();
        List<Long> results = new ArrayList<>();
        
        // Precompute prefix sums for faster range calculations
        long[] prefixSums = new long[n];
        prefixSums[0] = warehouseCapacity.get(0);
        for (int i = 1; i < n; i++) {
            prefixSums[i] = prefixSums[i-1] + warehouseCapacity.get(i);
        }
        
        // Cache the last warehouse capacity
        int lastWarehouse = warehouseCapacity.get(n-1);
        
        for (List<Integer> query : additionalHubs) {
            // Convert 1-based to 0-based indexing
            int hub1 = query.get(0) - 1;
            int hub2 = query.get(1) - 1;
            
            long totalCost = 0;
            
            // Calculate cost for warehouses before hub1
            if (hub1 > 0) {
                long sum = prefixSums[hub1-1];
                totalCost += (long)hub1 * warehouseCapacity.get(hub1) - sum;
            }
            
            // Calculate cost for warehouses between hub1 and hub2
            if (hub2 > hub1 + 1) {
                long rangeSum = prefixSums[hub2-1] - prefixSums[hub1];
                totalCost += (long)(hub2 - hub1 - 1) * warehouseCapacity.get(hub2) - rangeSum;
            }
            
            // Calculate cost for warehouses after hub2
            if (hub2 < n-1) {
                long rangeSum = prefixSums[n-1] - prefixSums[hub2];
                totalCost += (long)(n - hub2 - 1) * lastWarehouse - rangeSum;
            }
            
            results.add(totalCost);
        }
        
        return results;
    }

    // Main method and test cases remain the same
    public static void main(String[] args) {
        // Test Case 1
        List<Integer> warehouses1 = Arrays.asList(3, 6, 10, 15, 20);
        List<List<Integer>> queries1 = new ArrayList<>();
        queries1.add(Arrays.asList(2, 4));
        System.out.println("Result 1: " + getMinConnectionCost(warehouses1, queries1));

        // Test Case 2
        List<Integer> warehouses2 = Arrays.asList(2, 6, 8, 14);
        List<List<Integer>> queries2 = new ArrayList<>();
        queries2.add(Arrays.asList(1, 2));
        System.out.println("Result 2: " + getMinConnectionCost(warehouses2, queries2));
    }
}

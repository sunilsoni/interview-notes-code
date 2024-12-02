package com.interview.notes.code.year.y2024.july24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SuitableLocations5 {

    public static int suitableLocations(List<Integer> center, long d) {
        // Step 1: Sort the centers' positions
        Collections.sort(center);

        int n = center.size();

        // Step 2: Compute prefix sums of the centers
        long[] prefixSum = new long[n];
        prefixSum[0] = center.get(0);
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + center.get(i);
        }

        // Step 3: Initialize suitable location count and bounds
        int suitableLocationCount = 0;
        int minLocation = center.get(0), maxLocation = center.get(n - 1);

        // Binary search to find the number of suitable locations
        for (int x = minLocation; x <= maxLocation; x++) {
            long totalDistance = calculateTotalDistance(center, prefixSum, x);
            if (totalDistance <= d) {
                suitableLocationCount++;
            }
        }

        return suitableLocationCount;
    }

    private static long calculateTotalDistance(List<Integer> centers, long[] prefixSum, int x) {
        int n = centers.size();
        long totalDistance = 0;

        // Calculate the distance for all centers to point x
        for (int i = 0; i < n; i++) {
            totalDistance += 2 * Math.abs(x - centers.get(i));
        }

        return totalDistance;
    }

    public static void main(String[] args) {
        // Example 1
        List<Integer> center1 = Arrays.asList(2, 0, 3, -4);
        long d1 = 22;
        System.out.println(suitableLocations(center1, d1)); // Output: 5

        // Example 2
        List<Integer> center2 = Arrays.asList(-3, -2, 0, 1, 1);
        long d2 = 8;
        System.out.println(suitableLocations(center2, d2)); // Output: 3

        // Example 3
        List<Integer> center3 = Arrays.asList(-3, 2, 2);
        long d3 = 8;
        System.out.println(suitableLocations(center3, d3)); // Output: 0

        // Add more examples if needed
    }
}
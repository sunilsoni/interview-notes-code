package com.interview.notes.code.year.y2024.july24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SuitableLocations3 {

    public static int suitableLocations(List<Integer> center, long d) {
        // Step 1: Sort the centers' positions
        Collections.sort(center);

        // Find the minimum and maximum points in the center after which we need to consider suitable locations
        int minCenter = center.get(0);
        int maxCenter = center.get(center.size() - 1);

        // Initialize suitable location count
        int suitableLocationCount = 0;

        // Iterate through every possible location from the minimum to the maximum center location
        for (int potentialWarehouseLocation = minCenter; potentialWarehouseLocation <= maxCenter; potentialWarehouseLocation++) {
            long totalDistance = 0;

            // Calculate the total travel distance to potentialWarehouseLocation for all centers
            for (Integer c : center) {
                totalDistance += 2L * Math.abs(c - potentialWarehouseLocation);
            }

            if (totalDistance <= d) {
                suitableLocationCount++;
            }
        }

        return suitableLocationCount;
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
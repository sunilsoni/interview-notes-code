package com.interview.notes.code.months.july24.test5;

import java.util.Arrays;
import java.util.List;

public class WarehouseLocation1 {

    // Function to calculate the suitable locations for placing the warehouse
    public static int suitableLocations(List<Integer> centers, long d) {
        // Determine the minimum and maximum locations to consider based on centers
        int minLocation = Integer.MAX_VALUE;
        int maxLocation = Integer.MIN_VALUE;
        for (int center : centers) {
            if (center < minLocation) minLocation = center;
            if (center > maxLocation) maxLocation = center;
        }

        // Counter for suitable locations
        int count = 0;

        // Iterate over all possible warehouse positions
        for (int x = minLocation; x <= maxLocation; x++) {
            long totalDistance = 0;

            // Calculate the total distance from this position to all centers
            for (int center : centers) {
                totalDistance += Math.abs(center - x);
                // Early exit if the distance exceeds 'd'
                if (totalDistance > d) break;
            }

            // If the total distance is within the allowed maximum, count this location
            if (totalDistance <= d) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Example 1
        System.out.println(suitableLocations(List.of(-3, 2, 2), 8)); // Expected Output: 0

        // Example 2
        System.out.println(suitableLocations(List.of(2, 0, 3, -4), 22)); // Expected Output: 5

        // Example 1
        List<Integer> centers1 = Arrays.asList(4, 2, 0, 3, -4, 22);
        long d1 = 22;
        System.out.println(suitableLocations(centers1, d1));  // Output: 5

        // Example 2
        List<Integer> centers2 = Arrays.asList(3, -3, 2, 2, 8);
        long d2 = 8;
        System.out.println(suitableLocations(centers2, d2));  // Output: 0
    }
}

package com.interview.notes.code.months.july24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WarehouseLocations2 {

    public static void main(String[] args) {
        // Example 1
        List<Integer> centers1 = Arrays.asList(4, 2, 0, 3, -4, 22);
        long d1 = 22;
        System.out.println(suitableLocations(centers1, d1));  // Output: 5

        // Example 2
        List<Integer> centers2 = Arrays.asList(3, -3, 2, 2, 8);
        long d2 = 8;
        System.out.println(suitableLocations(centers2, d2));  // Output: 0
    }

    public static int suitableLocations(List<Integer> centers, long d) {
        Collections.sort(centers);
        int n = centers.size();
        int median = centers.get(n / 2);
        long totalDistance = 0;

        // Calculate the sum of distances at the median
        for (int center : centers) {
            totalDistance += Math.abs(center - median);
        }

        // Check if the median itself is a suitable location
        if (totalDistance > d) {
            return 0;  // No suitable locations if the median isn't suitable
        }

        // Expand from the median to find all suitable locations
        int count = 1; // Start with the median itself
        int left = median - 1;
        int right = median + 1;

        while (true) {
            long leftDistance = 0, rightDistance = 0;
            boolean validLeft = true, validRight = true;

            // Calculate distances if we move to the left or right from the median
            for (int center : centers) {
                if (validLeft) leftDistance += Math.abs(center - left);
                if (validRight) rightDistance += Math.abs(center - right);
            }

            // Check if these distances are within the allowed maximum
            if (leftDistance <= d) {
                count++;
                left--;
            } else {
                validLeft = false;
            }

            if (rightDistance <= d) {
                count++;
                right++;
            } else {
                validRight = false;
            }

            // If neither direction is valid, stop
            if (!validLeft && !validRight) {
                break;
            }
        }

        return count;
    }
}

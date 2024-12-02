package com.interview.notes.code.year.y2024.july24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WarehouseLocations {

    public static void main(String[] args) {
        // Test case
        List<Integer> centers = Arrays.asList(2, 0, 3, -4);
        long d = 22;
        System.out.println(suitableLocations(centers, d));  // Expected Output: 5
    }

    public static int suitableLocations(List<Integer> centers, long d) {
        Collections.sort(centers);
        int n = centers.size();
        if (n == 0) return 0;

        int median = centers.get(n / 2);
        long sum = 0;

        // Calculate the total distance at the median
        for (int center : centers) {
            sum += Math.abs(center - median);
        }

        // If the sum at the median is already greater than d, no locations are suitable
        if (sum > d) return 0;

        // Count the median itself
        int count = 1;

        // Check distances expanding from the median
        int left = median - 1;
        int right = median + 1;

        while (left >= median - (d / n) || right <= median + (d / n)) {
            if (left >= Integer.MIN_VALUE && calculateTotalDistance(centers, left) <= d) {
                count++;
                left--;
            }

            if (right <= Integer.MAX_VALUE && calculateTotalDistance(centers, right) <= d) {
                count++;
                right++;
            }

            if (left < median - (d / n) && right > median + (d / n)) {
                break;
            }
        }

        return count;
    }

    private static long calculateTotalDistance(List<Integer> centers, int x) {
        long totalDistance = 0;
        for (int center : centers) {
            totalDistance += Math.abs(center - x);
        }
        return totalDistance;
    }
}

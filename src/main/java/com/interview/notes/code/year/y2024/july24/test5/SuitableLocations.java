package com.interview.notes.code.year.y2024.july24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SuitableLocations {

    public static int suitableLocations(List<Integer> center, long d) {
        // Step 1: Sort the centers' positions
        Collections.sort(center);
        int n = center.size();

        // Step 2: Calculate prefix sums
        long[] prefixSums = new long[n];
        prefixSums[0] = center.get(0);
        for (int i = 1; i < n; i++) {
            prefixSums[i] = prefixSums[i - 1] + center.get(i);
        }

        // Step 3: Check for suitable locations
        int suitableLocationCount = 0;
        int minCenter = center.get(0), maxCenter = center.get(n - 1);

        for (int x = minCenter; x <= maxCenter; x++) {
            if (isSuitableLocation(center, prefixSums, x, d, n)) {
                suitableLocationCount++;
            }
        }

        return suitableLocationCount;
    }

    private static boolean isSuitableLocation(List<Integer> centers, long[] prefixSums, int x, long d, int n) {
        long totalDist = 0;
        int idx = Collections.binarySearch(centers, x);

        if (idx < 0) {
            idx = -(idx + 1);
        }

        // Sum distances for all centers <= x
        if (idx > 0) {
            totalDist += 2L * (1L * x * idx - prefixSums[idx - 1]);
        }

        // Sum distances for all centers > x
        if (idx < n) {
            totalDist += 2L * (prefixSums[n - 1] - (idx > 0 ? prefixSums[idx - 1] : 0) - 1L * x * (n - idx));
        }

        return totalDist <= d;
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

        // Example 3
        List<Integer> center4 = Arrays.asList(-2, 1, 0);
        long d4 = 8;
        System.out.println(suitableLocations(center4, d4)); // Output: 0

        // Add more examples if needed
    }
}
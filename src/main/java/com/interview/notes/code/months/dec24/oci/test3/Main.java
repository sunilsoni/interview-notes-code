package com.interview.notes.code.months.dec24.oci.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    /**
     * Given a list of data point locations and an integer k (number of clusters),
     * returns the minimal possible maximum distance between any data point and its nearest cluster center.
     */
    public static int getMaximumDistance(List<Integer> location, int k) {
        // Convert the list to an array for easier manipulation
        int n = location.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = location.get(i);
        }

        // Sort locations
        Arrays.sort(arr);

        // Define search space for the answer (distance)
        int left = 0;
        int right = arr[n - 1] - arr[0]; // max possible distance
        int result = right;

        // Binary search to find the minimal feasible distance
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canCoverAllPoints(arr, k, mid)) {
                // mid is feasible, try smaller
                result = mid;
                right = mid - 1;
            } else {
                // mid not feasible, try larger
                left = mid + 1;
            }
        }

        return result;
    }

    /**
     * Checks if we can cover all points using k cluster centers where
     * the maximum distance from any point to its cluster center is <= radius.
     * <p>
     * We use a greedy approach:
     * - Place the first cluster center to cover as many points as possible starting from arr[0].
     * If max distance = radius, a single center can cover points within an interval of length 2*radius.
     * - Once a point is found outside the current center's coverage, place a new center.
     * - If more than k centers are needed, return false.
     */
    private static boolean canCoverAllPoints(int[] arr, int k, int radius) {
        int usedCenters = 1;
        int currentCoverLimit = arr[0] + 2 * radius;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > currentCoverLimit) {
                // Need a new center
                usedCenters++;
                currentCoverLimit = arr[i] + 2 * radius;
                if (usedCenters > k) return false;
            }
        }
        return true;
    }

    /**
     * Utility method for testing the solution.
     * - Converts int[] to List<Integer>.
     * - Calls getMaximumDistance.
     * - Prints PASS if actual == expected, else FAIL.
     */
    private static void runTest(String testName, int[] arr, int k, int expected) {
        List<Integer> locList = new ArrayList<>();
        for (int val : arr) {
            locList.add(val);
        }

        int actual = getMaximumDistance(locList, k);
        System.out.println(testName + ": " + (actual == expected ? "PASS" : "FAIL")
                + " (expected " + expected + ", got " + actual + ")");
    }

    public static void main(String[] args) {
        // Sample Case from the problem:
        // locations = [1,9,3,10,14], k = 2
        // Expected minimal maximum distance = 3
        runTest("Sample Case 0", new int[]{1, 9, 3, 10, 14}, 2, 3);

        // Another Example:
        // n=5, location=[4,1,6,7,2], k=2
        // Optimal placement leads to max distance=2
        runTest("Example", new int[]{4, 1, 6, 7, 2}, 2, 2);

        // Edge Case: k=1
        // If we have location=[1,14], one center must cover everything.
        // We can place the center at 7.5 (between 1 and 14),
        // distance to 1=6.5 and to 14=6.5, so minimal integer max distance=6.
        runTest("Edge k=1", new int[]{1, 14}, 1, 6);

        // Edge Case: k=n
        // If k=number_of_points, we can place a center on each point, max distance=0.
        runTest("Edge k=n", new int[]{2, 5, 10}, 3, 0);

        // All identical:
        // location=[7,7,7,7], k=2
        // All points same, placing any center at 7 covers all with distance=0.
        runTest("All identical", new int[]{7, 7, 7, 7}, 2, 0);

        // Large data scenario (conceptual):
        // location=[1,2,3,1000], k=2
        // One cluster can cover [1,2,3] closely, another at 1000.
        // Minimal max distance = 1 (placing first center at 2 covers 1&3 with dist=1)
        runTest("Large data scenario", new int[]{1, 2, 3, 1000}, 2, 1);
    }
}

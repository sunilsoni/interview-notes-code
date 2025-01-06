package com.interview.notes.code.year.y2024.dec24.amazon.test11;

import java.util.Arrays;
import java.util.List;

public class MaxSumArr {

    /**
     * Calculate the maximum possible sum_arr after removing n/3 elements.
     *
     * @param item_weights list of integers representing item weights
     * @return the maximum possible sum_arr
     */
    public static int getMaxSumarr(List<Integer> item_weights) {
        int n = item_weights.size();
        int k = n / 3;

        // Convert list to array for easier processing
        int[] weights = item_weights.stream().mapToInt(i -> i).toArray();

        // Sort the weights
        Arrays.sort(weights);

        // Calculate the maximum sum possible for the first half:
        // Sum the largest (n - k) elements for maximum first half sum
        int firstHalfSum = 0;
        for (int i = k; i < n; i++) {
            firstHalfSum += weights[i];
        }

        // Calculate the maximum sum possible for the second half:
        // Sum the k smallest elements for minimum second half sum
        int secondHalfSum = 0;
        for (int i = 0; i < k; i++) {
            secondHalfSum += weights[i];
        }

        // Result is the difference
        return firstHalfSum - secondHalfSum;
    }

    public static void main(String[] args) {
        testGetMaxSumarr();
    }

    private static void testGetMaxSumarr() {
        runTest(Arrays.asList(1, 3, 4, 7, 5, 2), 4, "Sample Case 0");
        runTest(Arrays.asList(-3, -2, -1), -1, "Sample Case 1");
        runTest(Arrays.asList(3, 2, 1, -5, -4, -3), 6, "Edge Case: Mixed Positives and Negatives");
    }

    private static void runTest(List<Integer> item_weights, int expected, String testName) {
        int result = getMaxSumarr(item_weights);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL (Expected " + expected + ", Got " + result + ")");
        }
    }
}
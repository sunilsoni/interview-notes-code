package com.interview.notes.code.year.y2025.november.Virtusa.test1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MinDiffPair {

    // This method finds the first pair that has the smallest absolute difference
    public static int[] findMinDiffPair(int[] nums) {

        // Convert array to stream, sort it because closest numbers sit next to each other
        int[] sorted = Arrays.stream(nums).sorted().toArray();

        // Hold minimum difference found so far. Start with large value
        int minDiff = Integer.MAX_VALUE;

        // Hold the best pair we found so far
        int[] result = new int[2];

        // Loop through sorted array to compare each pair of neighbors
        for (int i = 0; i < sorted.length - 1; i++) {

            // Find difference between two next numbers
            int diff = Math.abs(sorted[i] - sorted[i + 1]);

            // If this difference is smaller than current minimum, update result
            if (diff < minDiff) {
                minDiff = diff;            // update smallest difference
                result[0] = sorted[i];      // store first number
                result[1] = sorted[i + 1];  // store second number
            }
        }

        // Return pair with minimum difference
        return result;
    }

    // Main method to test PASS/FAIL for all cases
    public static void main(String[] args) {

        // ----- TEST CASE 1 -----
        int[] nums1 = {2, 5, 3, 7, 9};
        int[] expected1 = {2, 3};
        test(nums1, expected1, "Test Case 1");

        // ----- TEST CASE 2: already sorted -----
        int[] nums2 = {1, 4, 6, 8};
        int[] expected2 = {4, 6};
        test(nums2, expected2, "Test Case 2");

        // ----- TEST CASE 3: large numbers -----
        int[] nums3 = {100, 101, 250, 300};
        int[] expected3 = {100, 101};
        test(nums3, expected3, "Test Case 3");

        // ----- TEST CASE 4: duplicates -----
        int[] nums4 = {5, 5, 5, 10};
        int[] expected4 = {5, 5};
        test(nums4, expected4, "Test Case 4");

        // ----- TEST CASE 5: large data -----
        int[] nums5 = IntStream.range(1, 1000000).toArray(); // 1 to 999,999
        int[] expected5 = {1, 2};
        test(nums5, expected5, "Large Data Test");
    }

    // Simple test method to check PASS or FAIL
    public static void test(int[] input, int[] expected, String testName) {
        int[] result = findMinDiffPair(input);

        boolean pass = result[0] == expected[0] && result[1] == expected[1];

        System.out.println(testName + " -> " +
                (pass ? "PASS" : "FAIL") +
                " | result = [" + result[0] + "," + result[1] + "]");
    }
}

package com.interview.notes.code.year.y2024.aug24.test9;

public class Solution {
    // Main method to find the median of two sorted arrays
    public static double findMedianSortedArrays(int[] A, int[] B) {
        // Ensure A is the smaller array for efficiency
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A);
        }

        int x = A.length;
        int y = B.length;

        // Binary search range
        int low = 0;
        int high = x;

        while (low <= high) {
            // Calculate partition points
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            // Find elements around partition points
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : A[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : A[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : B[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : B[partitionY];

            // Check if we've found the correct partition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // If total length is even, average the middle elements
                if ((x + y) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    // If total length is odd, return the middle element
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                // Adjust partition if maxLeftX is too large
                high = partitionX - 1;
            } else {
                // Adjust partition if maxLeftY is too large
                low = partitionX + 1;
            }
        }

        // If we reach here, input arrays are not sorted
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    // Method to run all test cases
    public static boolean doTestsPass() {
        boolean result = true;

        // Test case 1: Basic even-length example
        result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}) == 2.5;

        // Test case 2: Odd-length combined array
        result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0;

        // Test case 3: Another odd-length example
        result = result && findMedianSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6}) == 3.5;

        // Test case 4: Different-sized arrays
        result = result && findMedianSortedArrays(new int[]{1, 3, 8, 9, 15}, new int[]{7, 11, 18, 19, 21, 25}) == 11.0;

        // Test case 5: Arrays with repeated numbers
        result = result && findMedianSortedArrays(new int[]{1, 2, 3, 4, 5}, new int[]{1, 1, 3, 4, 5}) == 3.0;

        // Test case 6: Empty array
        result = result && findMedianSortedArrays(new int[]{}, new int[]{1}) == 1.0;

        // Test case 7: Negative numbers
        result = result && findMedianSortedArrays(new int[]{-5, -3, -1}, new int[]{-4, -2, 0}) == -2.5;

        return result;
    }

    // Main method to run tests and demonstrate examples
    public static void main(String[] args) {
        // Run all test cases
        if (doTestsPass()) {
            System.out.println("All tests pass.");
        } else {
            System.out.println("There are test failures.");
        }

        // Additional examples for demonstration
        System.out.println("Median of [1, 3] and [2, 4]: " + findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}));
        System.out.println("Median of [1, 3] and [2]: " + findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println("Median of [1, 3, 5] and [2, 4, 6]: " + findMedianSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6}));
    }
}

package com.interview.notes.code.year.y2024.aug24.test9;

public class Solution1 {
    public static double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A);
        }

        int x = A.length;
        int y = B.length;

        int low = 0;
        int high = x;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : A[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : A[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : B[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : B[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static boolean doTestsPass() {
        boolean result = true;

        // Test case 1
        result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}) == 2.5;

        // Test case 2
        result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0;

        // Test case 3: Odd-length combined array
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

    public static void main(String[] args) {
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

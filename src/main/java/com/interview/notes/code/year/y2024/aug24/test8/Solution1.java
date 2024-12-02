package com.interview.notes.code.year.y2024.aug24.test8;

public class Solution1 {
    public static double findMedianSortedArrays(int[] A, int[] B) {
        // Ensure A is the smaller array for simplicity
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
        result = result && Math.abs(findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}) - 2.5) < 1e-6;
        result = result && Math.abs(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) - 2.0) < 1e-6;
        result = result && Math.abs(findMedianSortedArrays(new int[]{}, new int[]{1}) - 1.0) < 1e-6;
        result = result && Math.abs(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}) - 2.5) < 1e-6;
        result = result && Math.abs(findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0}) - 0.0) < 1e-6;
        result = result && Math.abs(findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3, 4, 5}) - 3.0) < 1e-6;
        result = result && Math.abs(findMedianSortedArrays(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8}) - 4.5) < 1e-6;
        return result;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }

        // Additional test cases
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4})); // Expected: 2.5
        System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{2})); // Expected: 2.0
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1})); // Expected: 1.0
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4})); // Expected: 2.5
        System.out.println(findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0})); // Expected: 0.0
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3, 4, 5})); // Expected: 3.0
        System.out.println(findMedianSortedArrays(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6, 8})); // Expected: 4.5
    }
}

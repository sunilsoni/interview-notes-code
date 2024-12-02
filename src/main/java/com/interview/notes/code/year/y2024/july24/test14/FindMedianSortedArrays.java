package com.interview.notes.code.year.y2024.july24.test14;

import java.util.Arrays;

public class FindMedianSortedArrays {
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
                // We have found the right partition
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

    public static void main1(String[] args) {
        // Example 1: The one we discussed earlier
        int[] A1 = {1, 3};
        int[] B1 = {2, 4};
        System.out.println("Example 1 Median: " + findMedianSortedArrays(A1, B1));

        // Example 2: Odd number of elements
        int[] A2 = {1, 3, 5};
        int[] B2 = {2, 4, 6};
        System.out.println("Example 2 Median: " + findMedianSortedArrays(A2, B2));

        // Example 3: One empty array
        int[] A3 = {};
        int[] B3 = {1, 2, 3, 4, 5};
        System.out.println("Example 3 Median: " + findMedianSortedArrays(A3, B3));

        // Example 4: Arrays of different sizes
        int[] A4 = {1, 2};
        int[] B4 = {3, 4, 5, 6, 7};
        System.out.println("Example 4 Median: " + findMedianSortedArrays(A4, B4));

        /*
         result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}) == 2.5;
    result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0;
         */
    }

    public static void main2(String[] args) {
        // Basic cases
        testCase(new int[]{1, 3}, new int[]{2, 4}, "Basic even case");
        testCase(new int[]{1, 3, 5}, new int[]{2, 4, 6}, "Basic odd case");

        // Edge cases
        testCase(new int[]{}, new int[]{1}, "One empty array, odd elements");
        testCase(new int[]{}, new int[]{1, 2}, "One empty array, even elements");
        testCase(new int[]{1}, new int[]{}, "Other empty array");
        testCase(new int[]{1}, new int[]{2}, "Two single-element arrays");

        // Arrays with different sizes
        testCase(new int[]{1, 2}, new int[]{3, 4, 5, 6, 7}, "Different sizes, odd total");
        testCase(new int[]{1, 2, 3}, new int[]{4, 5, 6, 7}, "Different sizes, even total");

        // Large number cases
        int[] largeArray1 = generateSortedArray(1000000, 1);
        int[] largeArray2 = generateSortedArray(1000000, 1000001);
        testCase(largeArray1, largeArray2, "Large arrays (1M elements each)");

        // Arrays with negative numbers
        testCase(new int[]{-5, -3, -1}, new int[]{-2, 0, 2, 4}, "Arrays with negative numbers");

        // Arrays with duplicate elements
        testCase(new int[]{1, 1, 3, 3}, new int[]{1, 1, 3, 3}, "Arrays with duplicates");

        // Edge case: all elements are the same
        testCase(new int[]{2, 2, 2}, new int[]{2, 2, 2}, "All elements are the same");
    }

    private static void testCase(int[] A, int[] B, String description) {
        System.out.println("Test Case: " + description);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Array B: " + Arrays.toString(B));
        double median = findMedianSortedArrays(A, B);
        System.out.println("Median: " + median);
        System.out.println();
    }

    private static int[] generateSortedArray(int size, int startValue) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = startValue + i;
        }
        return array;
    }

    public static void main(String[] args) {
        boolean result = true;

        // Basic cases
        result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}) == 2.5;
        result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0;
        result = result && findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4, 5, 6}) == 3.5;

        // Edge cases
        result = result && findMedianSortedArrays(new int[]{}, new int[]{1}) == 1.0;
        result = result && findMedianSortedArrays(new int[]{}, new int[]{1, 2}) == 1.5;
        result = result && findMedianSortedArrays(new int[]{1}, new int[]{}) == 1.0;
        result = result && findMedianSortedArrays(new int[]{1}, new int[]{2}) == 1.5;

        // Arrays with different sizes
        result = result && findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4, 5, 6, 7}) == 4.0;
        result = result && findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4, 5, 6, 7}) == 4.0;

        // Large number cases
        //  int[] largeArray1 = generateSortedArray(1000000, 1);
        //  int[] largeArray2 = generateSortedArray(1000000, 1000001);
        // result = result && findMedianSortedArrays(largeArray1, largeArray2) == 1000000.5;

        // Arrays with negative numbers
        result = result && findMedianSortedArrays(new int[]{-5, -3, -1}, new int[]{-2, 0, 2, 4}) == -0.5;

        // Arrays with duplicate elements
        result = result && findMedianSortedArrays(new int[]{1, 1, 3, 3}, new int[]{1, 1, 3, 3}) == 2.0;

        // Edge case: all elements are the same
        result = result && findMedianSortedArrays(new int[]{2, 2, 2}, new int[]{2, 2, 2}) == 2.0;

        System.out.println("All tests passed: " + result);
    }


}

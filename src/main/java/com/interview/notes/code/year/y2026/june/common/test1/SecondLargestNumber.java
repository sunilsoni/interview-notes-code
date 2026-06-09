package com.interview.notes.code.year.y2026.june.common.test1;

import java.util.Arrays;

public class SecondLargestNumber {

    // Method to find second largest distinct number
    static int findSecondLargest(int[] arr) {

        // Return -1 when array is null or has less than 2 elements
        if (arr == null || arr.length < 2) {
            return -1;
        }

        // Store largest value found so far
        int largest = Integer.MIN_VALUE;

        // Store second largest value found so far
        int secondLargest = Integer.MIN_VALUE;

        // Traverse every element in array
        for (int num : arr) {

            // If current number becomes new largest
            if (num > largest) {

                // Previous largest becomes second largest
                secondLargest = largest;

                // Update largest
                largest = num;
            }

            // Update second largest only if:
            // 1. Number is not equal to largest
            // 2. Number is greater than current second largest
            else if (num != largest && num > secondLargest) {

                // Update second largest
                secondLargest = num;
            }
        }

        // If second largest never changed, return -1
        return secondLargest == Integer.MIN_VALUE ? -1 : secondLargest;
    }

    // Test helper
    static void test(int[] input, int expected) {

        // Execute method
        int actual = findSecondLargest(input);

        // Print PASS/FAIL
        System.out.println(
                Arrays.toString(input)
                        + " Expected=" + expected
                        + " Actual=" + actual
                        + " -> "
                        + (actual == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {

        // Given test cases
        test(new int[]{10, 5, 20, 8, 20}, 10);
        test(new int[]{1, 2, 3, 4, 5}, 4);
        test(new int[]{5, 5, 5}, -1);
        test(new int[]{10}, -1);
        test(new int[]{-1, -5, -2}, -2);

        // Edge cases
        test(new int[]{2, 1}, 1);
        test(new int[]{100, 100, 99}, 99);
        test(new int[]{Integer.MAX_VALUE, 5, 10}, 10);

        // Large input test
        int[] large = new int[100000];

        for (int i = 0; i < large.length; i++) {
            large[i] = i;
        }

        System.out.println(
                "Large Test -> "
                        + (findSecondLargest(large) == 99998
                        ? "PASS"
                        : "FAIL"));
    }
}
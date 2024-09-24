package com.interview.notes.code.months.sept24.test9;

public class SecondLargestFinder {
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {3, 5, 1, 2, 4},
            {7, 7, 7, 7},
            {1},
            {-1, -2, -3, -4},
            {5, 5, 6, 6, 7}
        };
        for (int i = 0; i < testCases.length; i++) {
            try {
                int result = findSecondLargest(testCases[i]);
                System.out.println("Test case " + (i + 1) + ": Second largest is " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Test case " + (i + 1) + ": " + e.getMessage());
            }
        }
    }
    public static int findSecondLargest(int[] nums) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) {
                second = num;
            }
        }

        if (second == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("No second largest element found.");
        }

        return second;
    }
}


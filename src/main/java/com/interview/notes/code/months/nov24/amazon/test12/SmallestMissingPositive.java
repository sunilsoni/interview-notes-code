package com.interview.notes.code.months.nov24.amazon.test12;


/*

Given an array of integers, nums, which is unsorted and may contain both positive and negative values, as well as zeros.

Examine the contents of this array and determine the smallest positive integer that does not appear within
1t.
In other words, you need to identify the lowest positive integer, starting from 1, that is missing from this list of numbers.
The solution should focus on finding this smallest positive integer efficiently,
regardless of the order or range of the numbers provided in nums.


 */
public class SmallestMissingPositive {

    public static int findSmallestMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: Ignore non-positive and out-of-range numbers
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }

        // Step 2: Mark present numbers
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // Step 3: Find the first missing positive
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        // If all numbers from 1 to n are present
        return n + 1;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{1, 2, 0}, 3);
        runTest(new int[]{3, 4, -1, 1}, 2);
        runTest(new int[]{7, 8, 9, 11, 12}, 1);
        runTest(new int[]{1, 2, 3, 4, 5}, 6);
        runTest(new int[]{-1, -2, 0}, 1);
        runTest(new int[]{2, 3, 4, 5, 6}, 1);
        runTest(new int[]{1, 1, 1, 1}, 2);
        runTest(new int[]{1, 1000000}, 2);
        runTest(new int[]{1, 2, 3, 4, 5, 1000000}, 6);
        runTest(new int[]{1000000}, 1);

        // Large data input test
        int[] largeInput = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            largeInput[i] = i + 1;
        }
        largeInput[999999] = 1000001; // Make the last number larger
        runTest(largeInput, 1000000);
    }

    private static void runTest(int[] input, int expected) {
        int[] inputCopy = input.clone(); // Create a copy to preserve original input
        int result = findSmallestMissingPositive(inputCopy);
        boolean passed = result == expected;
        System.out.println("Test case: " + (passed ? "PASSED" : "FAILED"));
        if (!passed) {
            System.out.println("  Input: " + arrayToString(input));
            System.out.println("  Expected: " + expected);
            System.out.println("  Got: " + result);
        }
    }

    private static String arrayToString(int[] arr) {
        if (arr.length > 10) {
            return "[" + arr[0] + ", " + arr[1] + ", ..., " + arr[arr.length - 2] + ", " + arr[arr.length - 1] + "]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

package com.interview.notes.code.months.nov24.amazon.test13;

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
        if (nums == null || nums.length == 0 || nums[0] > 1) {
            return 1;
        }

        int n = nums.length;
        int expected = 1;

        for (int num : nums) {
            if (num <= 0) {
                continue;  // Skip non-positive numbers
            }

            if (num == expected) {
                expected++;
            } else if (num > expected) {
                return expected;
            }
        }

        return expected;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{1, 2, 3, 4, 6, 7}, 5);
        runTest(new int[]{1, 2, 3, 4, 5}, 6);
        runTest(new int[]{2, 3, 4, 5, 6}, 1);
        runTest(new int[]{-3, -2, -1, 0, 1, 2, 3}, 4);
        runTest(new int[]{1, 1, 2, 3, 3, 4, 5, 5}, 6);
        runTest(new int[]{1, 2, 3, 4, 5, 1000000}, 6);
        runTest(new int[]{1000000}, 1);
        runTest(new int[]{}, 1);

        // Large data input test
        int[] largeInput = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            largeInput[i] = i + 1;
        }
        runTest(largeInput, 1000001);
    }

    private static void runTest(int[] input, int expected) {
        int result = findSmallestMissingPositive(input);
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

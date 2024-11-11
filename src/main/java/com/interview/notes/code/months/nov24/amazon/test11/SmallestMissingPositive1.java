package com.interview.notes.code.months.nov24.amazon.test11;

import java.util.HashSet;
import java.util.Set;

public class SmallestMissingPositive1 {

    public static int findSmallestMissingPositive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        int maxNum = 0;

        // Add all positive numbers to the set and find the maximum
        for (int num : nums) {
            if (num > 0) {
                numSet.add(num);
                maxNum = Math.max(maxNum, num);
            }
        }

        // Check for missing numbers from 1 to maxNum
        for (int i = 1; i <= maxNum; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }

        // If no missing number found, return maxNum + 1
        return maxNum + 1;
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

        // Large data input test
        int[] largeInput = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            largeInput[i] = i + 1;
        }
        largeInput[999999] = 1000001; // Make the last number larger
        runTest(largeInput, 1000000);
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

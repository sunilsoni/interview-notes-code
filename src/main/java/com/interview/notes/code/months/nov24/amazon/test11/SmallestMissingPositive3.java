package com.interview.notes.code.months.nov24.amazon.test11;

public class SmallestMissingPositive3 {
    public static int findSmallestMissingPositive(int[] nums) {
        int n = nums.length;
        boolean[] present = new boolean[n + 1];

        for (int num : nums) {
            if (num > 0 && num <= n) {
                present[num] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!present[i]) {
                return i;
            }
        }

        return n + 1;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{3, 4, -1, 1}, 2);
        runTest(new int[]{1, 2, 0}, 3);
        runTest(new int[]{7, 8, 9, 11, 12}, 1);
        runTest(new int[]{1, 2, 3, 4, 5}, 6);
        runTest(new int[]{-1, -2, 0}, 1);
    }

    private static void runTest(int[] input, int expected) {
        int result = findSmallestMissingPositive(input);
        System.out.println("Input: " + java.util.Arrays.toString(input));
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED"));
        System.out.println();
    }
}

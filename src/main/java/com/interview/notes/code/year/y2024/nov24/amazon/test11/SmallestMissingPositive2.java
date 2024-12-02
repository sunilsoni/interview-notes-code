package com.interview.notes.code.year.y2024.nov24.amazon.test11;

public class SmallestMissingPositive2 {

    public static int findSmallestMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        int n = nums.length;

        // Step 1: Move positive integers <= n to their correct positions
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        // Step 2: Find the first missing positive integer
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // If all numbers from 1 to n are present
        return n + 1;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{3, 4, -1, 1}, 2);
        runTest(new int[]{1, 2, 0}, 3);
        runTest(new int[]{7, 8, 9, 11, 12}, 1);
        runTest(new int[]{1, 2, 3, 4, 5}, 6);
        runTest(new int[]{-1, -2, 0}, 1);
        runTest(new int[]{2, 3, 4, 5, 6}, 1);
        runTest(new int[]{1, 1, 1, 1}, 2);
        runTest(new int[]{1, 1000000}, 2);
        runTest(new int[]{1, 2, 3, 4, 5, 1000000}, 6);
        runTest(new int[]{1000000}, 1);
        runTest(new int[]{}, 1);

        // Large data input test
        int[] largeInput = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            largeInput[i] = 1000000 - i;  // Reverse order to test unsorted scenario
        }
        runTest(largeInput, 1000001);
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

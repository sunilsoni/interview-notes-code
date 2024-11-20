package com.interview.notes.code.months.nov24.amazon.test25;

/*

### Problem Statement:
You are given an integer array `nums`. Perform the following tasks:

1. For each index `i` in the array, calculate the number of **smaller elements** to the right of `nums[i]` and return an array `countsSmaller` containing these values for all indices.
2. Similarly, for each index `i` in the array, calculate the number of **larger elements** to the right of `nums[i]` and return an array `countsLarger` containing these values for all indices.

---

### Input:
- An integer array `nums`.

### Output:
- Two arrays:
  1. `countsSmaller` – Array containing counts of smaller elements to the right.
  2. `countsLarger` – Array containing counts of larger elements to the right.

 */
public class CountSmallerLargerElements {

    public static Result countSmallerAndLarger(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new Result(new int[0], new int[0]);
        }

        int n = nums.length;
        int[] countsSmaller = new int[n];
        int[] countsLarger = new int[n];

        // For each element, count smaller and larger elements to its right
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    countsSmaller[i]++;
                } else if (nums[j] > nums[i]) {
                    countsLarger[i]++;
                }
            }
        }

        return new Result(countsSmaller, countsLarger);
    }

    private static boolean verifyTestCase(int[] input, int[] expectedSmaller, int[] expectedLarger) {
        Result result = countSmallerAndLarger(input);

        System.out.println("Input: ");
        printArray(input);
        System.out.println("\nExpected Smaller: ");
        printArray(expectedSmaller);
        System.out.println("\nActual Smaller: ");
        printArray(result.countsSmaller);
        System.out.println("\nExpected Larger: ");
        printArray(expectedLarger);
        System.out.println("\nActual Larger: ");
        printArray(result.countsLarger);
        System.out.println("\n");

        // Check array lengths
        if (result.countsSmaller.length != expectedSmaller.length ||
                result.countsLarger.length != expectedLarger.length) {
            return false;
        }

        // Verify smaller counts
        for (int i = 0; i < expectedSmaller.length; i++) {
            if (result.countsSmaller[i] != expectedSmaller[i]) {
                return false;
            }
        }

        // Verify larger counts
        for (int i = 0; i < expectedLarger.length; i++) {
            if (result.countsLarger[i] != expectedLarger[i]) {
                return false;
            }
        }

        return true;
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        // Test Case 1: [9, 2, 3, 1]
        int[] test1 = {9, 2, 3, 1};
        int[] expected1Smaller = {3, 1, 1, 0};
        int[] expected1Larger = {0, 1, 0, 0};  // Fixed this array
        System.out.print("Test Case 1: ");
        System.out.println(verifyTestCase(test1, expected1Smaller, expected1Larger) ? "PASS" : "FAIL");

        // Test Case 2: [-1]
        int[] test2 = {-1};
        int[] expected2Smaller = {0};
        int[] expected2Larger = {0};
        System.out.print("Test Case 2: ");
        System.out.println(verifyTestCase(test2, expected2Smaller, expected2Larger) ? "PASS" : "FAIL");

        // Test Case 3: [-1, -1]
        int[] test3 = {-1, -1};
        int[] expected3Smaller = {0, 0};
        int[] expected3Larger = {0, 0};
        System.out.print("Test Case 3: ");
        System.out.println(verifyTestCase(test3, expected3Smaller, expected3Larger) ? "PASS" : "FAIL");

        // Test Case 4: [1, 2, 3, 4]
        int[] test4 = {1, 2, 3, 4};
        int[] expected4Smaller = {0, 0, 0, 0};
        int[] expected4Larger = {3, 2, 1, 0};
        System.out.print("Test Case 4: ");
        System.out.println(verifyTestCase(test4, expected4Smaller, expected4Larger) ? "PASS" : "FAIL");

        // Test Case 5: [4, 3, 2, 1]
        int[] test5 = {4, 3, 2, 1};
        int[] expected5Smaller = {3, 2, 1, 0};
        int[] expected5Larger = {0, 0, 0, 0};
        System.out.print("Test Case 5: ");
        System.out.println(verifyTestCase(test5, expected5Smaller, expected5Larger) ? "PASS" : "FAIL");

        // Large Data Test
        int[] largeTest = new int[1000];
        for (int i = 0; i < 1000; i++) {
            largeTest[i] = 1000 - i;
        }
        long startTime = System.currentTimeMillis();
        Result largeResult = countSmallerAndLarger(largeTest);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Data Test (1000 elements) completed in: " + (endTime - startTime) + "ms");
    }

    static class Result {
        int[] countsSmaller;
        int[] countsLarger;

        Result(int[] smaller, int[] larger) {
            this.countsSmaller = smaller;
            this.countsLarger = larger;
        }
    }
}

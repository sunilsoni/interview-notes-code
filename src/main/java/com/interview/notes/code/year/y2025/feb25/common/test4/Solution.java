package com.interview.notes.code.year.y2025.feb25.common.test4;

/**
 * A simple Java solution that computes the sum of an array of integers.
 * It includes a main method for testing various scenarios including edge cases and large input.
 */
public class Solution {

    /**
     * Computes the sum of the elements in the given array.
     *
     * @param arr the array of integers
     * @return the sum of the elements in arr
     */
    public static int arraySum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    /**
     * The main method to run tests on the arraySum function.
     * It prints out PASS/FAIL for each test case.
     */
    public static void main(String[] args) {
        // Test Case 1: Empty Array
        int[] testCase1 = {};
        int expected1 = 0;
        runTest("Test Case 1 (Empty Array)", testCase1, expected1);

        // Test Case 2: Regular Array with positive numbers
        int[] testCase2 = {1, 2, 3, 4, 5};
        int expected2 = 15;
        runTest("Test Case 2 (Positive Numbers)", testCase2, expected2);

        // Test Case 3: Array with negative numbers
        int[] testCase3 = {-1, -2, -3, -4, -5};
        int expected3 = -15;
        runTest("Test Case 3 (Negative Numbers)", testCase3, expected3);

        // Test Case 4: Mixed positive and negative numbers
        int[] testCase4 = {-10, 20, -30, 40, -50};
        int expected4 = -30;
        runTest("Test Case 4 (Mixed Numbers)", testCase4, expected4);

        // Test Case 5: Large Data Input
        int largeSize = 1_000_000;  // One million elements
        int[] largeTest = new int[largeSize];
        // Fill the array with 1's.
        for (int i = 0; i < largeSize; i++) {
            largeTest[i] = 1;
        }
        int expectedLarge = largeSize;  // Sum should be equal to the number of elements
        long startTime = System.currentTimeMillis();
        int resultLarge = arraySum(largeTest);
        long endTime = System.currentTimeMillis();
        if (resultLarge == expectedLarge) {
            System.out.println("Test Case 5 (Large Data): PASS (" + (endTime - startTime) + "ms)");
        } else {
            System.out.println("Test Case 5 (Large Data): FAIL. Expected " + expectedLarge + " but got " + resultLarge);
        }
    }

    /**
     * Helper method to run a test case and print the result.
     *
     * @param testName the name of the test case
     * @param testData the input array to test
     * @param expected the expected sum of the array elements
     */
    private static void runTest(String testName, int[] testData, int expected) {
        int result = arraySum(testData);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL. Expected " + expected + " but got " + result);
        }
    }
}
package com.interview.notes.code.year.y2025.feb.common.test5;

public class Solution {

    /**
     * Sums all integers in the provided array.
     *
     * @param arr an array of integers to sum
     * @return the sum of the array elements
     */
    public static int sum(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result += num;
        }
        return result;
    }

    /**
     * Runs multiple test cases to validate the sum() function.
     * Prints "Passed" or "Failed" for each test.
     */
    public static void runTests() {
        int testsPassed = 0;
        int totalTests = 0;

        // Test 1: Simple test with positive numbers.
        totalTests++;
        if (sum(new int[]{1, 2, 3}) == 6) {
            System.out.println("Test 1 Passed");
            testsPassed++;
        } else {
            System.out.println("Test 1 Failed");
        }

        // Test 2: Empty array test.
        totalTests++;
        if (sum(new int[]{}) == 0) {
            System.out.println("Test 2 Passed");
            testsPassed++;
        } else {
            System.out.println("Test 2 Failed");
        }

        // Test 3: Test with negative numbers.
        totalTests++;
        if (sum(new int[]{-1, -2, -3}) == -6) {
            System.out.println("Test 3 Passed");
            testsPassed++;
        } else {
            System.out.println("Test 3 Failed");
        }

        // Test 4: Large input data test.
        totalTests++;
        int size = 1000000; // one million elements
        int[] largeArray = new int[size];
        // Fill the array with 1's.
        for (int i = 0; i < size; i++) {
            largeArray[i] = 1;
        }
        if (sum(largeArray) == size) {
            System.out.println("Test 4 Passed");
            testsPassed++;
        } else {
            System.out.println("Test 4 Failed");
        }

        // Print overall test results.
        System.out.println("Total Tests Passed: " + testsPassed + " out of " + totalTests);
    }

    /**
     * Main method to run the test cases.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        runTests();
    }
}
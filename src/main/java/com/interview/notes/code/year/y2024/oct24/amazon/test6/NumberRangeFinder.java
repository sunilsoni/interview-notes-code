package com.interview.notes.code.year.y2024.oct24.amazon.test6;

import java.util.Arrays;

public class NumberRangeFinder {

    public static void main(String[] args) {
        NumberRangeFinder finder = new NumberRangeFinder();
        finder.runTests();
    }

    // Main solution method to find the smallest number in the range
    public int solution(int[] numbers, int[] nRange) {
        int minInRange = Integer.MAX_VALUE; // Initialize with a large value

        // Loop through numbers to find candidates in the given range
        for (int number : numbers) {
            if (number > nRange[0] && number < nRange[1]) {
                minInRange = Math.min(minInRange, number);
            }
        }

        // If no number was found within the range, return 0
        return minInRange == Integer.MAX_VALUE ? 0 : minInRange;
    }

    // Method to run tests and check if they pass or fail
    public void runTests() {
        // Example test cases
        int[][] testNumbers = {
                {11, 4, 23, 9, 10},
                {1, 3, 2},
                {7, 23, 3, 1, 3, 5, 2}
        };

        int[][] testRanges = {
                {5, 12},
                {1, 1},
                {2, 7}
        };

        int[] expectedResults = {9, 0, 3};

        boolean allTestsPassed = true;

        for (int i = 0; i < testNumbers.length; i++) {
            int result = solution(testNumbers[i], testRanges[i]);
            boolean testPassed = result == expectedResults[i];
            System.out.println("Test Case " + (i + 1) + ": " + (testPassed ? "PASS" : "FAIL") +
                    " (Expected: " + expectedResults[i] + ", Got: " + result + ")");
            if (!testPassed) {
                allTestsPassed = false;
            }
        }

        // Run large data test
        int[] largeNumbers = new int[100];
        Arrays.fill(largeNumbers, 50);
        int[] largeRange = {10, 90};
        int largeResult = solution(largeNumbers, largeRange);
        boolean largeTestPassed = largeResult == 50;
        System.out.println("Large Data Test: " + (largeTestPassed ? "PASS" : "FAIL") +
                " (Expected: 50, Got: " + largeResult + ")");

        if (!allTestsPassed || !largeTestPassed) {
            System.out.println("Some tests failed. Please check the implementation.");
        } else {
            System.out.println("All tests passed successfully.");
        }
    }
}

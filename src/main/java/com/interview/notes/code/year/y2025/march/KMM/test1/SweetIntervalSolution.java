package com.interview.notes.code.year.y2025.march.KMM.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SweetIntervalSolution {

    /**
     * Finds the first subarray in 'ar' that sums to 'S'.
     * Returns a list of size 2: [startIndex, endIndex] (1-based).
     * Returns [-1, -1] if no such subarray exists.
     */
    public static List<Integer> solve(List<Integer> ar, int S) {
        int start = 0;
        int currentSum = 0;

        for (int end = 0; end < ar.size(); end++) {
            // Expand the window by adding the current element
            currentSum += ar.get(end);

            // Shrink the window from the left if the sum is too large
            while (currentSum > S && start <= end) {
                currentSum -= ar.get(start);
                start++;
            }

            // Check if we found the exact sum
            if (currentSum == S) {
                // Return 1-based indices
                return Arrays.asList(start + 1, end + 1);
            }
        }

        // If no subarray found, return an indicator
        return Arrays.asList(-1, -1);
    }

    /**
     * A simple test runner using the main method (no JUnit).
     * We will test multiple cases and print PASS/FAIL for each.
     */
    public static void main(String[] args) {
        // Test case 1
        List<Integer> ar1 = Arrays.asList(1, 3, 7, 5);
        int S1 = 10;
        List<Integer> result1 = solve(ar1, S1);
        List<Integer> expected1 = Arrays.asList(2, 3); // 3 + 7 = 10
        printTestResult("Test Case 1", result1, expected1);

        // Test case 2
        List<Integer> ar2 = Arrays.asList(1, 2, 3, 4, 5);
        int S2 = 15;
        List<Integer> result2 = solve(ar2, S2);
        List<Integer> expected2 = Arrays.asList(1, 5); // entire array sums to 15
        printTestResult("Test Case 2", result2, expected2);

        // Test case 3 (no subarray found)
        List<Integer> ar3 = Arrays.asList(2, 2, 2);
        int S3 = 10;
        List<Integer> result3 = solve(ar3, S3);
        List<Integer> expected3 = Arrays.asList(-1, -1);
        printTestResult("Test Case 3", result3, expected3);

        // Test case 4 (single element match)
        List<Integer> ar4 = Arrays.asList(5);
        int S4 = 5;
        List<Integer> result4 = solve(ar4, S4);
        List<Integer> expected4 = Arrays.asList(1, 1);
        printTestResult("Test Case 4", result4, expected4);

        // Test case 5 (multiple intervals, first match)
        List<Integer> ar5 = Arrays.asList(2, 3, 5, 2, 3);
        int S5 = 5;
        List<Integer> result5 = solve(ar5, S5);
        List<Integer> expected5 = Arrays.asList(2, 3); // 3 + 5
        printTestResult("Test Case 5", result5, expected5);

        // Edge case: sum = 0 (if it’s valid to search for zero)
        List<Integer> ar6 = Arrays.asList(0, 0, 0);
        int S6 = 0;
        List<Integer> result6 = solve(ar6, S6);
        // Depending on problem definition, the first valid subarray might be index 1,1 if we allow zero
        // If subarray of length >= 1 is needed, it might still be 1,1 since ar[0] = 0
        // We’ll assume we return (1,1) here for a single 0 element.
        List<Integer> expected6 = Arrays.asList(1, 1);
        printTestResult("Test Case 6 (Sum=0)", result6, expected6);

        // Large data test (for demonstration, though constraints are small)
        // We’ll create a random array and see if the solution runs quickly.
        List<Integer> largeTest = new ArrayList<>();
        for (int i = 0; i < 100; i++) { // within constraints
            largeTest.add(i % 10);
        }
        int S7 = 45; // quick guess for partial sum
        List<Integer> result7 = solve(largeTest, S7);
        System.out.println("Large Data Test result: " + result7);
    }

    /**
     * Utility method to compare result vs. expected and print PASS/FAIL.
     */
    private static void printTestResult(String testName, List<Integer> result, List<Integer> expected) {
        boolean pass = result.equals(expected);
        System.out.println(
                testName + " - Expected: " + expected + ", Got: " + result + " => " + (pass ? "PASS" : "FAIL")
        );
    }
}
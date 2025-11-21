package com.interview.notes.code.year.y2025.feb.paypal.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    // Function to compute the minimum unique array sum
    public static int getMinimumUniqueSum(List<Integer> arr) {
        if (arr == null || arr.isEmpty()) {
            return 0;
        }

        // Sort the array using streams
        List<Integer> sorted = arr.stream()
                .sorted()
                .collect(Collectors.toList());

        int sum = sorted.get(0);
        int prev = sorted.get(0);

        // Process each element ensuring uniqueness with minimal increments
        for (int i = 1; i < sorted.size(); i++) {
            int current = sorted.get(i);
            // If current is less than or equal to previous, adjust it
            if (current <= prev) {
                current = prev + 1;
            }
            sum += current;
            prev = current;
        }

        return sum;
    }

    // Simple main method for testing
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase(Arrays.asList(1, 2, 2), 6, "Sample Test Case"),
                new TestCase(Arrays.asList(3, 2, 1, 2, 7), 17, "Example Test Case"),
                new TestCase(List.of(1), 1, "Single Element Test"),
                new TestCase(Arrays.asList(2, 2, 2, 2), 2 + 3 + 4 + 5, "All Duplicates Test"),
                new TestCase(Arrays.asList(1, 2, 3, 4), 10, "Already Unique Test")
        );

        // Adding a large input test case
        List<Integer> largeInput = new ArrayList<>();
        int expectedLargeSum = 0;
        // Create an input with 2000 elements all as 1 (worst-case duplicates)
        for (int i = 0; i < 2000; i++) {
            largeInput.add(1);
        }
        // Compute expected sum for large input: starting at 1, then 2, 3, ..., 2000.
        // Sum of first N natural numbers = N*(N+1)/2
        expectedLargeSum = 2000 * 2001 / 2;
        testCases.add(new TestCase(largeInput, expectedLargeSum, "Large Input Test"));

        // Run test cases
        for (TestCase tc : testCases) {
            int result = getMinimumUniqueSum(tc.input);
            String status = (result == tc.expected) ? "PASS" : "FAIL";
            System.out.println(tc.testName + ": " + status + " (Expected: " + tc.expected + ", Got: " + result + ")");
        }
    }

    // Helper class for test cases
    static class TestCase {
        List<Integer> input;
        int expected;
        String testName;

        TestCase(List<Integer> input, int expected, String testName) {
            this.input = input;
            this.expected = expected;
            this.testName = testName;
        }
    }
}

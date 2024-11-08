package com.interview.notes.code.months.nov24.amazon.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TemperatureRegulator {

    public static long regulateTemperatures(List<Integer> temperature) {
        int n = temperature.size();
        long actions = 0;
        long currentSum = 0;
        long maxSum = 0;

        for (int i = 0; i < n; i++) {
            currentSum += temperature.get(i);
            maxSum = Math.max(maxSum, currentSum);
            actions += Math.abs(temperature.get(i));
        }

        return actions + maxSum;
    }

    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 1
        testCases.add(new TestCase(Arrays.asList(2, -2, -3, -1), 10));

        // Sample Case 0
        testCases.add(new TestCase(Arrays.asList(2, 2, 4, 3), 4));

        // Additional test cases
        testCases.add(new TestCase(Arrays.asList(1, 1, 1), 3));
        testCases.add(new TestCase(Arrays.asList(-5, 0, 5), 15));
        testCases.add(new TestCase(Arrays.asList(100, -100), 300));

        // Large input test case
        List<Integer> largeInput = new ArrayList<>();
        Random rand = new Random(0);  // Use seed for reproducibility
        for (int i = 0; i < 200000; i++) {
            largeInput.add(rand.nextInt(2000000001) - 1000000000);
        }
        testCases.add(new TestCase(largeInput, -1)); // -1 indicates we don't know the expected result

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long result = regulateTemperatures(tc.temperatures);

            if (tc.expectedResult == -1) {
                System.out.println("Test Case " + (i + 1) + " (Large Input): Result = " + result);
            } else {
                boolean passed = result == tc.expectedResult;
                System.out.println("Test Case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL") +
                        " (Expected: " + tc.expectedResult + ", Got: " + result + ")");
            }
        }
    }

    static class TestCase {
        List<Integer> temperatures;
        long expectedResult;

        TestCase(List<Integer> temperatures, long expectedResult) {
            this.temperatures = temperatures;
            this.expectedResult = expectedResult;
        }
    }
}

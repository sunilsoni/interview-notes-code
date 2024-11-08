package com.interview.notes.code.months.nov24.amazon.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemperatureRegulator {

    /**
     * Calculates the minimum number of operations required to regulate the temperatures of CPU cores to zero.
     *
     * @param temperature List of integers representing the initial temperatures of the cores.
     * @return The minimum number of operations as a long integer.
     */
    public static long regulateTemperatures(List<Integer> temperature) {
        if (temperature == null || temperature.isEmpty()) {
            return 0L;
        }

        // Step 1: Find the minimum temperature
        int minTemp = Integer.MAX_VALUE;
        for (int temp : temperature) {
            if (temp < minTemp) {
                minTemp = temp;
            }
        }

        // Step 2: Determine the number of Operation 3 needed
        long z = minTemp < 0 ? (long) -minTemp : 0L;

        // Step 3: Adjust temperatures by applying Operation 3 z times
        // and compute the operations via Operations 1 and 2
        List<Long> adjustedTemps = new ArrayList<>(temperature.size());
        for (int temp : temperature) {
            adjustedTemps.add((long) temp + z);
        }

        // Calculate operations via Operations 1 (Left to Right)
        long operations_via1 = adjustedTemps.get(0);
        long prev = adjustedTemps.get(0);
        for (int i = 1; i < adjustedTemps.size(); i++) {
            if (adjustedTemps.get(i) > prev) {
                operations_via1 += (adjustedTemps.get(i) - prev);
            }
            prev = adjustedTemps.get(i);
        }

        // Calculate operations via Operations 2 (Right to Left)
        long operations_via2 = adjustedTemps.get(adjustedTemps.size() - 1);
        long next = adjustedTemps.get(adjustedTemps.size() - 1);
        for (int i = adjustedTemps.size() - 2; i >= 0; i--) {
            if (adjustedTemps.get(i) > next) {
                operations_via2 += (adjustedTemps.get(i) - next);
            }
            next = adjustedTemps.get(i);
        }

        // Total operations via Operations 1 and 2 is the maximum of the two
        long operations_via1_and_2 = Math.max(operations_via1, operations_via2);

        // Total operations is z (Operation 3) plus operations via 1 and 2
        return z + operations_via1_and_2;
    }

    /**
     * Runs a series of test cases to validate the regulateTemperatures function.
     */
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 1
        testCases.add(new TestCase(Arrays.asList(2, -2, -3, -1), 10L));

        // Sample Case 0
        testCases.add(new TestCase(Arrays.asList(2, 2, 4, 3), 4L));

        // Edge Case: All zeros
        testCases.add(new TestCase(Arrays.asList(0, 0, 0, 0), 0L));

        // Edge Case: All positive
        testCases.add(new TestCase(Arrays.asList(1, 2, 3, 4, 5), 5L));

        // Edge Case: All negative
        testCases.add(new TestCase(Arrays.asList(-1, -2, -3, -4, -5), 10L));

        // Mixed Case
        testCases.add(new TestCase(Arrays.asList(3, -1, 2, -2, 4), 7L));

        // Large Input Case
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            largeInput.add(1000000);
        }
        testCases.add(new TestCase(largeInput, 1000000L));

        // Additional Test Case 1: Increasing sequence
        testCases.add(new TestCase(Arrays.asList(1, 2, 3, 4, 5), 5L));

        // Additional Test Case 2: Decreasing sequence
        testCases.add(new TestCase(Arrays.asList(5, 4, 3, 2, 1), 5L));

        // Additional Test Case 3: Mixed increases and decreases
        testCases.add(new TestCase(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5), 18L));

        // Additional Test Case 4: All positive with plateau
        testCases.add(new TestCase(Arrays.asList(2, 2, 2, 2, 2), 2L));

        // Additional Test Case 5: All negative with plateau
        testCases.add(new TestCase(Arrays.asList(-2, -2, -2, -2, -2), 4L));

        // Run Test Cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long result = regulateTemperatures(tc.temperature);
            if (result == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected " + tc.expected + ", Got " + result + ")");
            }
        }

        System.out.println(passed + "/" + testCases.size() + " Test Cases Passed.");
    }

    /**
     * Represents a test case with input and expected output.
     */
    static class TestCase {
        List<Integer> temperature;
        long expected;

        TestCase(List<Integer> temperature, long expected) {
            this.temperature = temperature;
            this.expected = expected;
        }
    }
}

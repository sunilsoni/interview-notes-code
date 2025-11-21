package com.interview.notes.code.year.y2024.nov24.amazon.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemperatureRegulator1 {

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
        long operations_via_1_and_2 = 0L;
        long prev = 0L;

        for (int i = 0; i < temperature.size(); i++) {
            long adjustedTemp = (long) temperature.get(i) + z;
            if (i == 0) {
                operations_via_1_and_2 += adjustedTemp;
            } else {
                if (adjustedTemp > prev) {
                    operations_via_1_and_2 += (adjustedTemp - prev);
                }
            }
            prev = adjustedTemp;
        }

        // Total operations is z (Operation 3) plus operations via 1 and 2
        return z + operations_via_1_and_2;
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
        testCases.add(new TestCase(Arrays.asList(1, 2, 3, 4, 5), 5L + (1 + 1 + 1 + 1))); // z=0, operations_via1_and2=5 +4=9

        // Edge Case: All negative
        testCases.add(new TestCase(Arrays.asList(-1, -2, -3, -4, -5), 5L + (5))); // z=5, operations_via1_and2=5

        // Mixed Case
        testCases.add(new TestCase(Arrays.asList(3, -1, 2, -2, 4), 7L));

        // Large Input Case
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            largeInput.add(1000000);
        }
        testCases.add(new TestCase(largeInput, 1000000L));

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

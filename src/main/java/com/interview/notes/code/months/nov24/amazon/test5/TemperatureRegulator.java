package com.interview.notes.code.months.nov24.amazon.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemperatureRegulator {
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
        for (int i = 1; i < adjustedTemps.size(); i++) {
            long diff = adjustedTemps.get(i) - adjustedTemps.get(i - 1);
            if (diff > 0) {
                operations_via1 += diff;
            }
        }

        // Calculate operations via Operations 2 (Right to Left)
        int n = adjustedTemps.size();
        long operations_via2 = adjustedTemps.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            long diff = adjustedTemps.get(i) - adjustedTemps.get(i + 1);
            if (diff > 0) {
                operations_via2 += diff;
            }
        }

        // Total operations via Operations 1 and 2 is the minimum of operations_via1 and operations_via2
        long operations_via1_and_2 = Math.min(operations_via1, operations_via2);

        // Total operations is z (Operation 3) plus the minimal operations via 1 and 2
        return z + operations_via1_and_2;
    }

    public static long regulateTemperatures4(List<Integer> temperature) {
        if (temperature == null || temperature.isEmpty()) {
            return 0L;
        }

        int n = temperature.size();
        long z = 0L;

        // Step 1: Find the minimum temperature
        int minTemp = temperature.stream().min(Integer::compareTo).orElse(0);
        if (minTemp < 0) {
            z = -(long) minTemp;
        }

        // Step 2: Adjust temperatures by applying Operation 3 z times
        long[] adjustedTemps = new long[n];
        for (int i = 0; i < n; i++) {
            adjustedTemps[i] = temperature.get(i) + z;
        }

        // Step 3: Calculate the minimal number of operations
        long totalOperations = z;
        for (int i = 0; i < n; i++) {
            long left = (i == 0) ? 0 : adjustedTemps[i - 1];
            long right = (i == n - 1) ? 0 : adjustedTemps[i + 1];
            long maxNeighbor = Math.max(left, right);
            if (adjustedTemps[i] > maxNeighbor) {
                totalOperations += adjustedTemps[i] - maxNeighbor;
            }
        }

        return totalOperations;
    }


    public static long regulateTemperatures5(List<Integer> temperature) {
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
        for (int i = 1; i < adjustedTemps.size(); i++) {
            long diff = adjustedTemps.get(i) - adjustedTemps.get(i - 1);
            if (diff > 0) {
                operations_via1 += diff;
            }
        }

        // Calculate operations via Operations 2 (Right to Left)
        int n = adjustedTemps.size();
        long operations_via2 = adjustedTemps.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            long diff = adjustedTemps.get(i) - adjustedTemps.get(i + 1);
            if (diff > 0) {
                operations_via2 += diff;
            }
        }

        // Total operations via Operations 1 and 2 is the minimum of operations_via1 and operations_via2
        long operations_via1_and_2 = Math.min(operations_via1, operations_via2);

        // Total operations is z (Operation 3) plus the minimal operations via 1 and 2
        return z + operations_via1_and_2;
    }

    /**
     * Calculates the minimum number of operations required to regulate the temperatures of CPU cores to zero.
     *
     * @param temperature List of integers representing the initial temperatures of the cores.
     * @return The minimum number of operations as a long integer.
     */
    public static long regulateTemperatures2(List<Integer> temperature) {
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
        long operations_via1 = 0L;
        long operations_via2 = 0L;

        long prev1 = 0L;
        long prev2 = 0L;

        for (int i = 0; i < temperature.size(); i++) {
            long adjustedTemp = (long) temperature.get(i) + z;

            // Calculate operations via Operations 1 (Left to Right)
            if (adjustedTemp > prev1) {
                operations_via1 += (adjustedTemp - prev1);
            }
            prev1 = adjustedTemp;

            // Calculate operations via Operations 2 (Right to Left)
            long reverseIndex = (long) temperature.get(temperature.size() - 1 - i) + z;
            if (reverseIndex > prev2) {
                operations_via2 += (reverseIndex - prev2);
            }
            prev2 = reverseIndex;
        }

        // Total operations via Operations 1 and 2 is the maximum of z, operations_via1, operations_via2
        long operations_via1_and_2 = Math.max(operations_via1, operations_via2);

        // Total operations is z (Operation 3) plus operations via 1 and 2
        return z + operations_via1_and_2;
    }

    /**
     * Calculates the minimum number of operations required to regulate the temperatures of CPU cores to zero.
     *
     * @param temperature List of integers representing the initial temperatures of the cores.
     * @return The minimum number of operations as a long integer.
     */
    public static long regulateTemperatures1(List<Integer> temperature) {
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

        // Total operations via Operations 1 and 2 is the maximum of z, operations_via1, operations_via2
        long operations_via1_and_2 = Math.max(z, Math.max(operations_via1, operations_via2));

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

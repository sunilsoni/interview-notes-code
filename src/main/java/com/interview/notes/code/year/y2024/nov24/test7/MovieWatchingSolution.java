package com.interview.notes.code.year.y2024.nov24.test7;

import java.util.Arrays;
import java.util.Random;

public class MovieWatchingSolution {
    public static int minimumDays(double[] durations) {
        // Sort in descending order
        Arrays.sort(durations);
        int n = durations.length;
        boolean[] watched = new boolean[n];
        int days = 0;

        for (int i = 0; i < n; i++) {
            if (watched[i]) continue;

            double remainingTime = 3.0 - durations[i];
            watched[i] = true;

            // Try to find the largest movie that fits in remaining time
            int bestIdx = -1;
            for (int j = i + 1; j < n; j++) {
                if (!watched[j] && durations[j] <= remainingTime) {
                    bestIdx = j;
                    break;
                }
            }

            if (bestIdx != -1) {
                watched[bestIdx] = true;
            }

            days++;
        }

        return days;
    }

    public static void main(String[] args) {
        // Test cases
        runTestCase(1, new double[]{1.90, 1.04, 1.25, 2.5, 1.75}, 3);
        runTestCase(2, new double[]{2.0, 2.0, 2.0}, 3);
        runTestCase(3, new double[]{1.01}, 1);
        runTestCase(4, new double[]{3.0, 2.5, 1.5, 1.8}, 3);

        // Edge cases
        runTestCase(5, new double[]{1.5, 1.5, 1.5, 1.5, 1.5, 1.5}, 3);

        // Additional test cases
        runTestCase(6, new double[]{2.99, 2.99, 2.99}, 3);
        runTestCase(7, new double[]{1.01, 1.01, 3.0}, 2);

        // Large dataset test
        double[] largeDataset = generateLargeDataset(1000);
        long startTime = System.currentTimeMillis();
        int result = minimumDays(largeDataset);
        long endTime = System.currentTimeMillis();
        System.out.println("Large dataset (1000 movies) completed in: " +
                (endTime - startTime) + "ms, Result: " + result);
    }

    private static void runTestCase(int testNumber, double[] input, int expectedOutput) {
        // Create a copy of input array to preserve original
        double[] inputCopy = Arrays.copyOf(input, input.length);
        int result = minimumDays(inputCopy);
        boolean passed = result == expectedOutput;

        System.out.println("Test Case " + testNumber + ": " +
                (passed ? "PASSED" : "FAILED"));
        if (!passed) {
            System.out.println("Expected: " + expectedOutput +
                    ", Got: " + result);
            System.out.println("Input: " + Arrays.toString(input));
        }
    }

    private static double[] generateLargeDataset(int size) {
        double[] dataset = new double[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            // Generate random duration between 1.01 and 3.00
            dataset[i] = 1.01 + (rand.nextDouble() * 1.99);
            // Round to 2 decimal places
            dataset[i] = Math.round(dataset[i] * 100.0) / 100.0;
        }
        return dataset;
    }
}
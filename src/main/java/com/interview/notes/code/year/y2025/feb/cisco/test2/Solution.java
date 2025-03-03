package com.interview.notes.code.year.y2025.feb.cisco.test2;

import java.util.*;

public class Solution {

    /**
     * Computes the floor of the mean of the array.
     * If sum is large, we use a long for safety.
     */
    public static int computeFloorMean(int[] arr) {
        long sum = 0L;
        for (int num : arr) {
            sum += num;
        }
        // Integer division in Java automatically floors the result
        return (int) (sum / arr.length);
    }

    /**
     * Computes the mode of the array (most frequently occurring value).
     * If there's a tie, it picks the smallest value.
     */
    public static int computeMode(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int mode = arr[0];
        int maxCount = 0;

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                mode = value;
            } else if (count == maxCount && value < mode) {
                // Tie case: pick smaller number
                mode = value;
            }
        }
        return mode;
    }

    /**
     * Minimal reproducible solution that:
     * 1. Reads N from System.in
     * 2. Reads N integers
     * 3. Computes floor of mean and mode
     * 4. Prints them
     */
    public static void solveFromInput() {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        int floorMean = computeFloorMean(arr);
        int mode = computeMode(arr);

        System.out.println(floorMean + " " + mode);
    }

    /**
     * Simple test method to check multiple test cases
     * without using JUnit. Prints PASS/FAIL.
     */
    public static void runTests() {
        // Each test: {inputArray, expectedFloorMean, expectedMode}
        List<TestData> tests = new ArrayList<>();

        // 1) Example from the problem statement
        tests.add(new TestData(new int[]{1, 2, 7, 3, 2}, 3, 2));

        // 2) Single element
        tests.add(new TestData(new int[]{10}, 10, 10));

        // 3) All elements the same
        tests.add(new TestData(new int[]{2, 2, 2, 2}, 2, 2));

        // 4) Multiple modes - picking the smallest if tie
        tests.add(new TestData(new int[]{1, 2, 2, 3, 3}, 2, 2));

        // 5) Large values (just a quick check for sum correctness)
        tests.add(new TestData(new int[]{1000000000, 1000000000}, 1000000000, 1000000000));

        // Run each test
        int testNumber = 1;
        for (TestData t : tests) {
            int actualMean = computeFloorMean(t.input);
            int actualMode = computeMode(t.input);

            boolean passMean = (actualMean == t.expectedMean);
            boolean passMode = (actualMode == t.expectedMode);
            boolean pass = passMean && passMode;

            System.out.println("Test #" + testNumber++ + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("  Expected: Mean=" + t.expectedMean
                        + ", Mode=" + t.expectedMode);
                System.out.println("  Actual:   Mean=" + actualMean
                        + ", Mode=" + actualMode);
            }
        }
    }

    /**
     * Main method to either run tests or solve from input.
     * You can control behavior by toggling comments.
     */
    public static void main(String[] args) {
        // Option 1: Run the predefined test cases
        runTests();

        // Option 2: Uncomment to read from standard input and solve:
        // solveFromInput();
    }
}

/**
 * Simple helper class for storing test data.
 */
class TestData {
    int[] input;
    int expectedMean;
    int expectedMode;

    public TestData(int[] input, int expectedMean, int expectedMode) {
        this.input = input;
        this.expectedMean = expectedMean;
        this.expectedMode = expectedMode;
    }
}

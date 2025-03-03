package com.interview.notes.code.year.y2025.jan.amazon.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static int getMaximumEvents(List<Integer> payload) {
        if (payload == null || payload.size() < 2) return 0;

        int n = payload.size();
        int maxEvents = 0;

        // Try each possible i and j combination
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Try to form sequence with current i and j
                int currentCount = getSequenceLength(payload, i, j);
                maxEvents = Math.max(maxEvents, currentCount);
            }
        }

        return maxEvents;
    }

    private static int getSequenceLength(List<Integer> payload, int i, int j) {
        List<Integer> sequence = new ArrayList<>();
        int n = payload.size();

        // Check if we can form increasing sequence up to i
        int prev = Integer.MIN_VALUE;
        for (int k = 0; k < n; k++) {
            int curr = payload.get(k);
            if (k <= i) {
                if (curr > prev) {
                    sequence.add(curr);
                    prev = curr;
                }
            } else if (k <= j) {
                // Decreasing sequence from i to j
                if (curr < prev) {
                    sequence.add(curr);
                    prev = curr;
                }
            } else {
                // Increasing sequence from j onwards
                if (curr > prev) {
                    sequence.add(curr);
                    prev = curr;
                }
            }
        }

        // Verify the sequence follows the pattern
        if (sequence.size() < 2) return 0;

        // Verify increasing-decreasing-increasing pattern
        boolean valid = true;
        boolean foundDecrease = false;
        boolean foundIncrease = false;

        for (int k = 1; k < sequence.size(); k++) {
            if (!foundDecrease) {
                if (sequence.get(k) < sequence.get(k - 1)) {
                    foundDecrease = true;
                }
            } else if (!foundIncrease) {
                if (sequence.get(k) > sequence.get(k - 1)) {
                    foundIncrease = true;
                }
            }
        }

        return valid ? sequence.size() : 0;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(Arrays.asList(5, 5, 2, 1, 3, 4, 5), 6, "Test 1: Sample case 0");
        runTest(Arrays.asList(1, 100), 2, "Test 2: Sample case 1");
        runTest(Arrays.asList(1, 3, 5, 4, 2, 6, 7, 8, 9), 9, "Test 3: Example case");
        runTest(Arrays.asList(1, 2), 2, "Test 4: Minimum size");
        runTest(Arrays.asList(5, 4, 3, 2, 1), 5, "Test 5: Decreasing sequence");

        // Large test case
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeInput.add(i % 100);
        }
        runTest(largeInput, 99, "Test 6: Large input");
    }

    private static void runTest(List<Integer> input, int expected, String testName) {
        System.out.println(testName);
        System.out.println("Input: " + (input.size() > 10 ?
                input.subList(0, 7) + "..." : input));
        int result = getMaximumEvents(input);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED"));
        System.out.println();
    }
}

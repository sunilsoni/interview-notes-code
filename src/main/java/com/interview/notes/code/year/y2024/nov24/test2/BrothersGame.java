package com.interview.notes.code.year.y2024.nov24.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BrothersGame {
    public static int solve(List<Integer> ar) {
        int n = ar.size();
        int currentOnes = 0;
        int maxBenefit = 0;
        int currentBenefit = 0;

        for (int i = 0; i < n; i++) {
            if (ar.get(i) == 1) {
                currentOnes++;
                currentBenefit--;
            } else {
                currentBenefit++;
            }
            maxBenefit = Math.max(maxBenefit, currentBenefit);
            if (currentBenefit < 0) {
                currentBenefit = 0;
            }
        }

        return Math.max(currentOnes, currentOnes + maxBenefit);
    }

    public static int solve1(List<Integer> ar) {
        int n = ar.size();
        int currentOnes = 0;
        int maxOnes = 0;
        int maxDiff = 0;
        int currentDiff = 0;

        for (int i = 0; i < n; i++) {
            if (ar.get(i) == 1) {
                currentOnes++;
                currentDiff--;
            } else {
                currentDiff++;
            }
            maxDiff = Math.max(maxDiff, currentDiff);
            if (currentDiff < 0) {
                currentDiff = 0;
            }
        }

        maxOnes = Math.max(currentOnes, currentOnes + maxDiff);
        return maxOnes;
    }

    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> testCases = new ArrayList<>();
        testCases.add(Arrays.asList(0, 1, 0, 0, 1));
        testCases.add(Arrays.asList(1, 0, 0, 1, 0, 0));
        testCases.add(Arrays.asList(1, 1, 1, 1, 1));
        testCases.add(Arrays.asList(0, 0, 0, 0, 0));
        testCases.add(Arrays.asList(1, 0, 1, 0, 1, 0, 1));
        testCases.add(Arrays.asList(1, 0, 1, 1, 1));  // New test case

        // Expected outputs
        int[] expectedOutputs = {4, 5, 5, 5, 5, 4};  // Updated expected outputs

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {
            List<Integer> testCase = testCases.get(i);
            int result = solve(testCase);
            boolean passed = result == expectedOutputs[i];
            System.out.println("Test case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            System.out.println("Input: " + testCase);
            System.out.println("Output: " + result);
            System.out.println("Expected: " + expectedOutputs[i]);
            System.out.println();
        }

        // Large input test
        List<Integer> largeInput = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(rand.nextInt(2));
        }
        long startTime = System.nanoTime();
        int largeResult = solve(largeInput);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1e6; // Convert to milliseconds
        System.out.println("Large input test (100,000 elements):");
        System.out.println("Result: " + largeResult);
        System.out.println("Time taken: " + duration + " ms");
    }
}

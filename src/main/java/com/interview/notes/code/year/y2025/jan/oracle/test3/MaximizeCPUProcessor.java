package com.interview.notes.code.year.y2025.jan.oracle.test3;

import java.util.*;

public class MaximizeCPUProcessor {

    // Core function to maximize CPU processing
    public static int maximizeCPU(List<Integer> requirements, int processingCapacity) {
        // Sort requirements in ascending order
        Collections.sort(requirements);

        // Use dynamic programming approach
        int n = requirements.size();
        int maxCapacity = 0;

        // Generate all possible subset combinations
        for (int mask = 1; mask < (1 << n); mask++) {
            int currentSum = 0;

            // Calculate sum for current subset
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) {
                    currentSum += requirements.get(j);
                }
            }

            // Update max capacity if within processing limit
            if (currentSum <= processingCapacity) {
                maxCapacity = Math.max(maxCapacity, currentSum);
            }
        }

        return maxCapacity;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1
        List<Integer> requirements1 = Arrays.asList(7, 6, 9, 11);
        int processingCapacity1 = 25;
        int result1 = maximizeCPU(requirements1, processingCapacity1);
        System.out.println("Test Case 1: " + (result1 == 24 ? "PASS" : "FAIL"));

        // Test Case 2
        List<Integer> requirements2 = Arrays.asList(2, 9, 7);
        int processingCapacity2 = 15;
        int result2 = maximizeCPU(requirements2, processingCapacity2);
        System.out.println("Test Case 2: " + (result2 == 11 ? "PASS" : "FAIL"));

        // Large Input Test Case
        List<Integer> largeRequirements = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            largeRequirements.add(random.nextInt(1000000) + 1);
        }
        int largeProcessingCapacity = 1000000000;
        int largeResult = maximizeCPU(largeRequirements, largeProcessingCapacity);
        System.out.println("Large Input Test: Result = " + largeResult);

        // Performance Test
        long startTime = System.nanoTime();
        maximizeCPU(largeRequirements, largeProcessingCapacity);
        long endTime = System.nanoTime();
        System.out.println("Performance Time: " + (endTime - startTime) / 1_000_000 + " ms");
    }
}
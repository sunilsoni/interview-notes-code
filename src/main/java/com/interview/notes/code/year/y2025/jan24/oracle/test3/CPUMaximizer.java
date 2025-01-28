package com.interview.notes.code.year.y2025.jan24.oracle.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CPUMaximizer {
    public static int maximizeCPU(List<Integer> requirements, int processingCapacity) {
        int n = requirements.size();
        int maxSum = 0;

        // Sort to optimize processing (not required but helps in some cases)
        Collections.sort(requirements);

        // Generate all possible combinations using bit manipulation
        for (int mask = 0; mask < (1 << n); mask++) {
            int currentSum = 0;

            // Check each bit position
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentSum += requirements.get(i);
                }

                // Early exit if sum exceeds capacity
                if (currentSum > processingCapacity) {
                    break;
                }
            }

            // Update maxSum if current combination is valid
            if (currentSum <= processingCapacity && currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum;
    }

    // Test method to verify solution
    public static void main(String[] args) {
        // Test Case 1
        List<Integer> req1 = Arrays.asList(7, 6, 9, 11);
        int cap1 = 25;
        int result1 = maximizeCPU(req1, cap1);
        System.out.println("Test Case 1: " + (result1 == 24 ? "PASS" : "FAIL") +
                " (Expected: 24, Got: " + result1 + ")");

        // Test Case 2
        List<Integer> req2 = Arrays.asList(2, 9, 7);
        int cap2 = 15;
        int result2 = maximizeCPU(req2, cap2);
        System.out.println("Test Case 2: " + (result2 == 11 ? "PASS" : "FAIL") +
                " (Expected: 11, Got: " + result2 + ")");

        // Edge Case: Single element
        List<Integer> req3 = Arrays.asList(5);
        int cap3 = 10;
        int result3 = maximizeCPU(req3, cap3);
        System.out.println("Edge Case 1: " + (result3 == 5 ? "PASS" : "FAIL") +
                " (Expected: 5, Got: " + result3 + ")");

        // Edge Case: Empty capacity
        List<Integer> req4 = Arrays.asList(3, 4, 5);
        int cap4 = 0;
        int result4 = maximizeCPU(req4, cap4);
        System.out.println("Edge Case 2: " + (result4 == 0 ? "PASS" : "FAIL") +
                " (Expected: 0, Got: " + result4 + ")");

        // Large Input Test (but still within constraints)
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            largeInput.add(i + 1);
        }
        int largeCap = 100;
        int resultLarge = maximizeCPU(largeInput, largeCap);
        System.out.println("Large Input Test completed. Result: " + resultLarge);
    }
}

package com.interview.notes.code.year.y2025.jan24.test2;

public class Solution {
    
    public static int secondSmallest(int[] x) {
        // Handle edge cases
        if (x == null || x.length < 2) {
            return 0;
        }

        // Initialize smallest and second smallest
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        // Single pass to find both values
        for (int num : x) {
            if (num < smallest) {
                secondSmallest = smallest;
                smallest = num;
            } else if (num < secondSmallest && num != smallest) {
                secondSmallest = num;
            }
        }

        // If no second smallest found, return 0
        return secondSmallest == Integer.MAX_VALUE ? 0 : secondSmallest;
    }

    public static boolean doTestsPass() {
        // Test cases
        int[][] testCases = {
            {}, // empty array
            {1}, // single element
            {1, 2}, // two elements in order
            {2, 1}, // two elements reversed
            {1, 1}, // duplicate elements
            {5, 2, 3, 1, 4}, // random order
            {-1, -2, -3}, // negative numbers
            {Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, // extreme values
            {1, 1, 1, 1, 1}, // all same values
            new int[10000] // large array
        };

        int[] expectedOutputs = {0, 0, 2, 2, 0, 2, -2, 0, 0, 0};
        
        // Fill large array for testing
        for (int i = 0; i < testCases[9].length; i++) {
            testCases[9][i] = i;
        }

        boolean allTestsPassed = true;
        
        // Run all test cases
        for (int i = 0; i < testCases.length; i++) {
            int result = secondSmallest(testCases[i]);
            boolean testPassed = (i < expectedOutputs.length) ? 
                               (result == expectedOutputs[i]) : 
                               (result == 1); // For large array case

            if (!testPassed) {
                System.out.printf("Test case %d failed: Expected %d, got %d%n",
                    i, (i < expectedOutputs.length) ? expectedOutputs[i] : 1, result);
                allTestsPassed = false;
            }
        }

        // Performance test with large array
        long startTime = System.nanoTime();
        secondSmallest(testCases[9]);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        System.out.printf("Large array (size %d) processed in %.2f ms%n", 
            testCases[9].length, duration);

        return allTestsPassed;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests passed successfully!");
        } else {
            System.out.println("Some tests failed!");
        }
    }
}

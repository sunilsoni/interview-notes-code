package com.interview.notes.code.months.nov24.geico.test1;

public class MaximumIncomeSolution {
    public static int solution(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }

        long maxIncome = 0;
        int MOD = 1000000000;
        
        // Keep track if we hold an asset
        boolean hasAsset = true;
        
        // Initial position - we start with an asset
        int prevPrice = A[0];
        
        for (int i = 1; i < A.length; i++) {
            if (hasAsset && A[i] > prevPrice) {
                // Sell if price increased
                maxIncome = (maxIncome + A[i] - prevPrice) % MOD;
                hasAsset = false;
            } else if (!hasAsset && A[i] < prevPrice) {
                // Buy if price decreased
                hasAsset = true;
            }
            prevPrice = A[i];
        }
        
        return (int) maxIncome;
    }

    // Test method to verify solution
    private static void testSolution() {
        int testCase = 1;
        
        // Test Case 1
        int[] test1 = {4, 1, 2, 3};
        int expected1 = 6;
        verifyResult(test1, expected1, testCase++);

        // Test Case 2
        int[] test2 = {1, 2, 3, 3, 2, 1, 5};
        int expected2 = 7;
        verifyResult(test2, expected2, testCase++);

        // Test Case 3
        int[] test3 = {1000000000, 1, 2, 2, 1000000000, 1, 1000000000};
        int expected3 = 999999998;
        verifyResult(test3, expected3, testCase++);

        // Edge Cases
        int[] test4 = {1};
        int expected4 = 0;
        verifyResult(test4, expected4, testCase++);

        int[] test5 = {5, 4, 3, 2, 1};
        int expected5 = 0;
        verifyResult(test5, expected5, testCase++);

        // Large Data Test
        int[] largeTest = generateLargeTestCase(200000);
        verifyPerformance(largeTest, testCase++);
    }

    private static void verifyResult(int[] input, int expected, int testCase) {
        int result = solution(input);
        boolean passed = result == expected;
        System.out.printf("Test Case %d: %s\n", testCase, 
            passed ? "PASSED" : "FAILED");
        if (!passed) {
            System.out.printf("Expected: %d, Got: %d\n", expected, result);
        }
    }

    private static void verifyPerformance(int[] input, int testCase) {
        long startTime = System.currentTimeMillis();
        solution(input);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.printf("Test Case %d (Performance): %s (Duration: %dms)\n", 
            testCase, duration < 1000 ? "PASSED" : "FAILED", duration);
    }

    private static int[] generateLargeTestCase(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 1000000000);
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println("Running test cases...");
        testSolution();
    }
}
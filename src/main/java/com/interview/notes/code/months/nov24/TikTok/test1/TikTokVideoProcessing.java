package com.interview.notes.code.months.nov24.TikTok.test1;

import java.util.*;

public class TikTokVideoProcessing {

    public static long maximizeProcessingPower(List<Integer> processingPower) {
        if (processingPower == null || processingPower.isEmpty()) {
            return 0;
        }

        int n = processingPower.size();
        // dp[i] represents max processing power achievable up to index i
        long[] dp = new long[n];

        // Base cases
        dp[0] = processingPower.get(0);
        if (n == 1) return dp[0];

        // For n >= 2
        dp[1] = Math.max(processingPower.get(0), processingPower.get(1));
        if (n == 2) return dp[1];

        // For each position, we have two choices:
        // 1. Include current element and skip adjacent ones
        // 2. Skip current element and use previous best
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(
                    dp[i - 1], // skip current
                    dp[i - 2] + processingPower.get(i) // include current
            );

            // Check if we can include current with elements before i-2
            if (i >= 3) {
                dp[i] = Math.max(dp[i], dp[i - 3] + processingPower.get(i));
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        // Test cases
        runTestCase1();
        runTestCase2();
        runTestCase3();
        runLargeDataTestCase();
        runEdgeCaseTest();
    }

    private static void runTestCase1() {
        List<Integer> input = Arrays.asList(3, 3, 5, 5, 2, 2, 5);
        long expected = 21;
        long result = maximizeProcessingPower(input);
        printTestResult("Test Case 1", expected, result);
    }

    private static void runTestCase2() {
        List<Integer> input = Arrays.asList(8, 5, 1, 5);
        long expected = 19;
        long result = maximizeProcessingPower(input);
        printTestResult("Test Case 2", expected, result);
    }

    private static void runTestCase3() {
        List<Integer> input = Arrays.asList(2, 3, 9, 2, 3);
        long expected = 15;
        long result = maximizeProcessingPower(input);
        printTestResult("Test Case 3", expected, result);
    }

    private static void runLargeDataTestCase() {
        // Generate large test data
        List<Integer> largeInput = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(rand.nextInt(1000000) + 1);
        }
        long startTime = System.currentTimeMillis();
        long result = maximizeProcessingPower(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Data Test (100000 elements):");
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        System.out.println("Result: " + result);
        System.out.println("PASS - Completed without errors\n");
    }

    private static void runEdgeCaseTest() {
        // Test single element
        List<Integer> singleElement = Collections.singletonList(5);
        printTestResult("Edge Case - Single Element", 5,
                maximizeProcessingPower(singleElement));

        // Test two elements
        List<Integer> twoElements = Arrays.asList(3, 4);
        printTestResult("Edge Case - Two Elements", 4,
                maximizeProcessingPower(twoElements));

        // Test empty list
        List<Integer> emptyList = new ArrayList<>();
        printTestResult("Edge Case - Empty List", 0,
                maximizeProcessingPower(emptyList));
    }

    private static void printTestResult(String testName, long expected, long result) {
        System.out.println(testName + ":");
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println(expected == result ? "PASS" : "FAIL");
        System.out.println();
    }
}

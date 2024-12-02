package com.interview.notes.code.year.y2024.nov24.amazon.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MaximumChargeCalculator3 {

    public static long getMaximumCharge(List<Integer> charge) {
        int n = charge.size();
        long[] dp = new long[n];

        // Base cases
        dp[0] = charge.get(0);
        if (n > 1) dp[1] = Math.max(charge.get(0), charge.get(1));

        // Dynamic programming to fill dp array
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + charge.get(i));
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> testCases = new ArrayList<>();
        testCases.add(Arrays.asList(-2, 4, 9, 1, -1));
        testCases.add(Arrays.asList(-1, 3, 2));
        testCases.add(Arrays.asList(-3, 1, 4, -1, 5, -9));

        // Large test case
        List<Integer> largeCase = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 200000; i++) {
            largeCase.add(rand.nextInt(2000000000) - 1000000000);
        }
        testCases.add(largeCase);

        // Expected results
        long[] expectedResults = {9, 3, 9, -1}; // -1 for unknown large case result

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {
            List<Integer> testCase = testCases.get(i);
            long result = getMaximumCharge(testCase);

            if (i < expectedResults.length - 1) {
                if (result == expectedResults[i]) {
                    System.out.println("Test case " + (i + 1) + ": PASS");
                } else {
                    System.out.println("Test case " + (i + 1) + ": FAIL");
                    System.out.println("Expected: " + expectedResults[i] + ", Got: " + result);
                }
            } else {
                System.out.println("Large test case result: " + result);
            }
        }
    }
}

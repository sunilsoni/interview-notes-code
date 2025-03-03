package com.interview.notes.code.year.y2025.jan.test23;

import java.util.Arrays;

/*

You are tasked with designing a system for an insurance company that optimizes policy management. Each policy has a start and end date, and a profit associated with it. You need to select the maximum number of non-overlapping policies such that the total profit is maximized.


You need to design in system where it says that it is for, basically, for the insurance company, for the optimizing the policy management.

So in that you have direct policy started and end date associated with it, right? So you need to select the maximum number of non overlapping policies.
So let me give you some input also.

policies = [
(1, 5, 50),
(6, 10, 60),
(2, 7, 100),
(8, 12, 90),
(13, 16, 120)
]

Output: 180

Explanation: The optimal selection is (1, 5, 50), (6, 10, 60), (13, 16, 120).

Input:
An array of policies, where each policy is represented as a tuple (start_date, end_date, profit).
start_date and end_date are integers representing the day of the year (from 1 to 365).
profit is an integer representing the profit associated with the policy.

Output:
An integer representing the maximum total profit from the selected non-overlapping policies.

 */
public class InsurancePolicyOptimizer {
    public static int maximizeProfit(Policy[] policies) {
        if (policies == null || policies.length == 0) {
            return 0;
        }

        // Sort policies by end date
        Arrays.sort(policies, (a, b) -> a.end - b.end);

        int n = policies.length;
        int[] dp = new int[n];
        dp[0] = policies[0].profit;

        for (int i = 1; i < n; i++) {
            // Profit including current policy
            int profitIncluding = policies[i].profit;
            int lastNonOverlap = -1;

            // Find the last non-overlapping policy
            for (int j = i - 1; j >= 0; j--) {
                if (policies[j].end <= policies[i].start) {
                    profitIncluding += dp[j];
                    break;
                }
            }

            // Maximum profit is either including current policy or excluding it
            dp[i] = Math.max(profitIncluding, dp[i - 1]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        // Test Case 1: Given example
        Policy[] test1 = new Policy[]{
                new Policy(1, 5, 50),
                new Policy(6, 10, 60),
                new Policy(2, 7, 100),
                new Policy(8, 12, 90),
                new Policy(13, 16, 120)
        };
        testCase(test1, 180, "Basic Test Case");

        // Test Case 2: Empty input
        Policy[] test2 = new Policy[]{};
        testCase(test2, 0, "Empty Input");

        // Test Case 3: Single policy
        Policy[] test3 = new Policy[]{new Policy(1, 5, 50)};
        testCase(test3, 50, "Single Policy");

        // Test Case 4: All overlapping policies
        Policy[] test4 = new Policy[]{
                new Policy(1, 10, 50),
                new Policy(2, 8, 60),
                new Policy(3, 7, 70)
        };
        testCase(test4, 70, "All Overlapping");

        // Test Case 5: Complex case
        Policy[] test5 = new Policy[]{
                new Policy(1, 4, 30),
                new Policy(2, 6, 40),
                new Policy(5, 7, 30),
                new Policy(3, 8, 50),
                new Policy(7, 10, 60)
        };
        testCase(test5, 90, "Complex Case");
    }

    private static void testCase(Policy[] policies, int expectedResult, String testName) {
        long startTime = System.currentTimeMillis();
        int result = maximizeProfit(policies);
        long endTime = System.currentTimeMillis();

        if (result == expectedResult) {
            System.out.println("PASS: " + testName +
                    " (Time taken: " + (endTime - startTime) + "ms)");
        } else {
            System.out.println("FAIL: " + testName +
                    " Expected: " + expectedResult +
                    " Got: " + result);
        }
    }

    static class Policy {
        int start;
        int end;
        int profit;

        Policy(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}

package com.interview.notes.code.year.y2025.feb.randstad.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GrandNumberGame {

    public static int solve(List<Integer> arr) {
        int n = arr.size() / 2;
        int[] nums = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            nums[i] = arr.get(i);
        }
        int totalStates = 1 << (2 * n);
        int[] dp = new int[totalStates];
        Arrays.fill(dp, -1);
        return helper(0, 1, n, nums, dp);
    }

    private static int helper(int mask, int op, int n, int[] nums, int[] dp) {
        int total = 2 * n;
        if (mask == (1 << total) - 1) {
            return 0;
        }
        if (dp[mask] != -1) {
            return dp[mask];
        }
        int maxScore = 0;
        for (int i = 0; i < total; i++) {
            if ((mask & (1 << i)) != 0) continue;
            for (int j = i + 1; j < total; j++) {
                if ((mask & (1 << j)) != 0) continue;
                int newMask = mask | (1 << i) | (1 << j);
                int currentScore = op * gcd(nums[i], nums[j]) + helper(newMask, op + 1, n, nums, dp);
                maxScore = Math.max(maxScore, currentScore);
            }
            break;
        }
        dp[mask] = maxScore;
        return maxScore;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    // Testing method
    private static void runTest(List<Integer> arr, int expected, String testName) {
        int result = solve(arr);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    public static void main(String[] args) {
        // Example Test 1
        runTest(Arrays.asList(3, 4, 9, 5), 7, "Example Test 1");
        // Example Test 2
        runTest(Arrays.asList(1, 2, 3, 4, 5, 6), 14, "Example Test 2");
        // Edge Test: minimum case (N=1)
        runTest(Arrays.asList(10, 15), 5, "Edge Test 1");
        // Additional Test: all elements equal
        runTest(Arrays.asList(2, 2, 2, 2), 4, "Edge Test 2");
        // Large Data Test: Maximum N=10, 20 elements, using random numbers
        Random rand = new Random();
        List<Integer> largeData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            largeData.add(rand.nextInt(1000000000) + 1);
        }
        // As we don't have a precomputed expected value, just print the result.
        System.out.println("Large Data Test (random 20 elements): " + solve(largeData));
    }
}
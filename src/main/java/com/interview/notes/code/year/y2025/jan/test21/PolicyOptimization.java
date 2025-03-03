package com.interview.notes.code.year.y2025.jan.test21;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PolicyOptimization {

    public static void main(String[] args) {
        // Test Case 1 (Updated Expected Output)
        List<int[]> policies1 = Arrays.asList(
                new int[]{1, 5, 50},
                new int[]{6, 10, 60},
                new int[]{2, 7, 100},
                new int[]{8, 12, 90},
                new int[]{13, 16, 120}
        );

        // Correct expected output for Test Case 1 is 310
        System.out.println("Test Case 1: " + (maxProfit(policies1) == 310 ? "PASS" : "FAIL"));
    }

    public static int maxProfit(List<int[]> policies) {
        if (policies.isEmpty()) return 0;
        policies.sort(Comparator.comparingInt(a -> a[1]));
        int n = policies.size();
        int[] dp = new int[n];
        dp[0] = policies.get(0)[2];

        for (int i = 1; i < n; i++) {
            int currentProfit = policies.get(i)[2];
            int l = binarySearch(policies, i);
            if (l != -1) currentProfit += dp[l];
            dp[i] = Math.max(currentProfit, dp[i - 1]);
        }
        return dp[n - 1];
    }

    private static int binarySearch(List<int[]> policies, int index) {
        int low = 0, high = index - 1;
        int target = policies.get(index)[0];
        while (low <= high) {
            int mid = (low + high) / 2;
            if (policies.get(mid)[1] <= target) {
                if (mid == high || policies.get(mid + 1)[1] > target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
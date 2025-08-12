package com.interview.notes.code.year.y2025.august.common.test2;

import java.util.*;

public class ClimbStairs {

    // Method to calculate distinct ways
    public static int climbStairs(int n) {
        // If n <= 1, there's only one way (either no move or one step)
        if (n <= 1) return 1;

        // prev1 → ways to reach (n-1)
        // prev2 → ways to reach (n-2)
        int prev1 = 1; // ways(1)
        int prev2 = 1; // ways(0)

        // Loop through steps starting from 2a
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2; // ways(n) = ways(n-1) + ways(n-2)
            prev2 = prev1; // shift prev2 forward
            prev1 = current; // shift prev1 forward
        }
        return prev1; // final answer for ways(n)
    }

    public static void main(String[] args) {
        // Test cases
        List<Integer> testCases = Arrays.asList(5, 1, 2, 3, 10, 20, 30);

        for (int n : testCases) {
            int result = climbStairs(n);
            System.out.println("n = " + n + " → Ways = " + result);
        }
    }
}
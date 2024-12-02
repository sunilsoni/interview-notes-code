package com.interview.notes.code.year.y2024.may24.test8;

public class SpecialKeyboard {
    public static void main(String[] args) {
        // Example 1
        System.out.println("Output for N=8: " + solve(8));

        // Example 2
        System.out.println("Output for N=4: " + solve(4));
        // Example 3
        System.out.println("Output for N=8: " + solve(3));
    }

    public static int solve(int N) {
        if (N > 75) {
            return -1;  // Return -1 if N is greater than 75
        }

        int[] dp = new int[N + 1];  // dp[i] will store the maximum number of B's that can be printed with i keystrokes

        for (int i = 1; i <= N; i++) {
            dp[i] = i;  // When all keypresses are Key 1
            for (int j = 3; j < i; j++) {
                // The sequence is j - 2, copy and multiply (i - j + 1) times
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        return dp[N];
    }
}

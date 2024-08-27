package com.interview.notes.code.months.aug24.amz.test17;

import java.util.Scanner;

/*
You have a pointer at index 0 in an array of size arren. At each step, you can move 1 position to the left, 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time). Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps. Since the answer may be too large, return it modulo 10^9 + 7. Example 1: Input:
steps = 3, arrLen = 2 Output: 4 Explanation: There are 4 differents ways to stay at index 0 after 3 steps. Right, Left, Stay Stay, Right, Left Right, Stay,
Left Stay, Stay, Stay Example 2: Input: steps = 2, arrLen = 4 Output: 2 Explanation: There are 2 differents ways to stay at index 0 after 2 steps Right,
 */
public class Main {
    private static final int MOD = 1_000_000_007;

    public static int numWays(int steps, int arrLen) {
        arrLen = Math.min(arrLen, steps + 1);
        int[] dp = new int[arrLen];
        int[] temp = new int[arrLen];
        dp[0] = 1;

        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j < arrLen; j++) {
                temp[j] = dp[j];
                if (j > 0) temp[j] = (temp[j] + dp[j - 1]) % MOD;
                if (j < arrLen - 1) temp[j] = (temp[j] + dp[j + 1]) % MOD;
            }
            int[] swap = dp;
            dp = temp;
            temp = swap;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example 1
        System.out.println("Example 1:");
        System.out.println("Input: steps = 3, arrLen = 2");
        System.out.println("Output: " + numWays(3, 2));

        // Example 2
        System.out.println("\nExample 2:");
        System.out.println("Input: steps = 2, arrLen = 4");
        System.out.println("Output: " + numWays(2, 4));

        // Additional test case
        System.out.println("\nAdditional test case:");
        System.out.println("Input: steps = 4, arrLen = 3");
        System.out.println("Output: " + numWays(4, 3));

        // User input
        System.out.println("\nEnter your own test case (format: steps arrLen):");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        int steps = Integer.parseInt(parts[0]);
        int arrLen = Integer.parseInt(parts[1]);
        System.out.println("Output: " + numWays(steps, arrLen));

        scanner.close();
    }
}

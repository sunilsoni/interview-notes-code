package com.interview.notes.code.year.y2025.feb25.imocha.test1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int MaxValue(int N, int A, int B, int C) {
        // Initialize an array dp where dp[i] represents
        // the maximum number of parts to form length i
        // using segments A, B, or C. Default to -404.
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -404);

        // Base case: 0 length can be formed with 0 parts
        dp[0] = 0;

        // Build up the dp array
        for (int i = 1; i <= N; i++) {
            if (i - A >= 0 && dp[i - A] != -404) {
                dp[i] = Math.max(dp[i], dp[i - A] + 1);
            }
            if (i - B >= 0 && dp[i - B] != -404) {
                dp[i] = Math.max(dp[i], dp[i - B] + 1);
            }
            if (i - C >= 0 && dp[i - C] != -404) {
                dp[i] = Math.max(dp[i], dp[i - C] + 1);
            }
        }

        // dp[N] will hold the maximum number of parts or -404 if not possible
        return dp[N];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        sc.close();

        System.out.print(MaxValue(N, A, B, C));
    }
}
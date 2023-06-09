package com.interview.notes.code.test.test7;

import java.util.Arrays;

public class Main1 {
    public static void main(String[] args) {
        // int n =5;

        // int[] arr = { 1,2,4,1,2};

        int[] arr = {1, 2, 4, 1, 2};
        int n = 5;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 2; i < n; i++) {
            if ((arr[i - 2] < arr[i - 1] && arr[i - 1] < arr[i]) || (arr[i - 2] > arr[i - 1] && arr[i - 1] > arr[i])) {
                dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            }
            dp[i] = Math.min(dp[i], dp[i - 2] + 1);
        }

        System.out.println(dp[n - 1]);
    }
}

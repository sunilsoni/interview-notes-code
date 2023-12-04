package com.interview.notes.code.months.dec23.test1;

import java.util.Arrays;
import java.util.List;

class Result1 {

    /*
     * Complete the 'gainMaxValue' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY security_val
     *  2. INTEGER k
     */

    public static int gainMaxValue(List<Integer> security_val, int k) {
        int n = security_val.size();
        int[] dp = new int[n];
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            if (i < k) {
                dp[i] = security_val.get(i);
            } else {
                dp[i] = security_val.get(i) + dp[i - k];
            }
            max = Math.max(max, dp[i]);
        }

        for (int i = 0; i < k; i++) {
            for (int j = i + k; j < n; j += k) {
                dp[j] = Math.max(dp[j], dp[j - k] + security_val.get(j));
                max = Math.max(max, dp[j]);
            }
        }
        
        return max;
    }
    public static void main(String[] args) {
        List<Integer> security_val = Arrays.asList(3, 5, -2, -4, 6, 1);
        int k = 2;

        int maxSecurityValue = Result1.gainMaxValue(security_val, k);
        System.out.println("Maximum Security Value: " + maxSecurityValue);
    }
}

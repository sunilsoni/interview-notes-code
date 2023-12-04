package com.interview.notes.code.months.dec23.test1;

import java.util.Arrays;
import java.util.List;

public class Result {

    public static int gainMaxValue(List<Integer> security_val, int k) {
        int n = security_val.size();
        int maxSum = Integer.MIN_VALUE;

        // Iterate over all possible starting nodes
        for (int start = 0; start < k; start++) {
            int sum = 0;
            // Jump from the starting node by k steps and sum the security values
            for (int j = start; j < n; j += k) {
                sum += security_val.get(j);
            }
            // Update maxSum if the current sum is greater
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public static int gainMaxValue(int n, int[] security_val, int k) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < k; i++) {
            int sum = 0;
            for (int j = i; j < n; j += k) {
                sum += security_val[j];
            }
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
    
    public static void main(String[] args) {
        List<Integer> security_val = Arrays.asList(5, 2, 5, -8, -6, -7, 3);
        int k = 2;

        int maxSecurityValue = gainMaxValue(security_val, k);

        System.out.println("Maximum Security Value: " + maxSecurityValue);
    }
}

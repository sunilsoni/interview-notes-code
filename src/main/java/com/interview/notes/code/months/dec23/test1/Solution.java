package com.interview.notes.code.months.dec23.test1;

import java.util.Arrays;
import java.util.List;

public class Solution {

    public static int gainMaxValue(List<Integer> security_val, int k) {
        int n = security_val.size();
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            int node = i;
            while (node < n) {
                sum += security_val.get(node);
                node += k;
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

        security_val = Arrays.asList(6, 5, 3, -2, -4, 9, 16, 2);
        k = 2;

        maxSecurityValue = gainMaxValue(security_val, k);

        System.out.println("Maximum Security Value: " + maxSecurityValue);
    }
}
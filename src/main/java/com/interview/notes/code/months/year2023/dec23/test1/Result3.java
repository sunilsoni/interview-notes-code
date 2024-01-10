package com.interview.notes.code.months.year2023.dec23.test1;

import java.util.Arrays;
import java.util.List;

public class Result3 {

    public static int gainMaxValue(List<Integer> security_val, int k) {
        int maxSum = 0;

        // Check each possible starting position
        for (int start = 0; start < k; start++) {
            int sum = 0;
            for (int i = start; i < security_val.size(); i += k) {
                sum += security_val.get(i);
            }
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        List<Integer> security_val = Arrays.asList(3, 5, -2, -4, 9, 16);
        int k = 2;

        int maxSecurityValue = gainMaxValue(security_val, k);
        System.out.println("Maximum Security Value: " + maxSecurityValue);
    }
}

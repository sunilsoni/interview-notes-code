package com.interview.notes.code.months.year2023.dec23.test1;

import java.util.Arrays;
import java.util.List;

public class Result2 {

    public static int gainMaxValue(List<Integer> security_val, int k) {
        int n = security_val.size();
        int maxSum = 0;
        int startIndex = 0;

        // Calculate the maximum security value sum for each valid starting index
        for (int i = 0; i < k; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j += k) {
                currentSum += security_val.get(j);
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
                startIndex = i;
            }
        }

        // Here we assume that the result is required to be the maximum sum.
        // If the starting index is also needed, we can return a Pair or a custom object.
        return maxSum;
    }

    public static void main(String[] args) {
        List<Integer> security_val = Arrays.asList(3, 5, -2, -4, 6, 1);
        int k = 2;

        int maxSecurityValue = gainMaxValue(security_val, k);
        System.out.println("Maximum Security Value: " + maxSecurityValue);
    }
}

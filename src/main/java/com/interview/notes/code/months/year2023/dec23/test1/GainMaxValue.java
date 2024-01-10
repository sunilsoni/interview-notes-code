package com.interview.notes.code.months.year2023.dec23.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GainMaxValue {
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

    public static void main1(String[] args) {
        int n = 6;
        int[] security_val = {3, 5, -2, -4, 9, 16};
        int k = 2;

        int result = gainMaxValue(n, security_val, k);
        System.out.println(result);
    }


    public static int gainMaxValue1(List<Integer> security_val, int k) {
        int maxSum = Integer.MIN_VALUE;
        int n = security_val.size();

        for (int i = 0; i < k; i++) {
            int sum = 0;
            for (int j = i; j < n; j += k) {
                sum += security_val.get(j);
            }
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public static int gainMaxValue(List<Integer> security_val, int k) {
        int n = security_val.size();
        int[] max_sum_up_to = new int[n];

        // Initialize base cases
        max_sum_up_to[0] = security_val.get(0);
        for (int i = 1; i < n; i++) {
            int maxPrev = 0;
            for (int j = Math.max(0, i - k); j < i; j++) {
                maxPrev = Math.max(maxPrev, max_sum_up_to[j]);
            }
            max_sum_up_to[i] = maxPrev + security_val.get(i);
        }

        // Find the maximum sum in `max_sum_up_to` array
        int max_sum = Integer.MIN_VALUE;
        for (int value : max_sum_up_to) {
            max_sum = Math.max(max_sum, value);
        }

        return max_sum;
    }

    public static int gainMaxValue(int[] security_val, int k) {
        int n = security_val.length;
        int[] max_sum_up_to = new int[n];

        // Initialize base cases
        max_sum_up_to[0] = security_val[0];
        for (int i = 1; i < n; i++) {
            int maxPrev = 0;
            for (int j = Math.max(0, i - k); j < i; j++) {
                maxPrev = Math.max(maxPrev, max_sum_up_to[j]);
            }
            max_sum_up_to[i] = maxPrev + security_val[i];
        }

        // Find the maximum sum in `max_sum_up_to` array
        int max_sum = Integer.MIN_VALUE;
        for (int value : max_sum_up_to) {
            max_sum = Math.max(max_sum, value);
        }

        return max_sum;
    }

    public static void main(String[] args) {
        List<Integer> security_val = new ArrayList<>();
        security_val.add(3);
        security_val.add(5);
        security_val.add(-2);
        security_val.add(-4);
        security_val.add(9);
        security_val.add(16);
        int k = 2;

        int result = gainMaxValue(security_val, k);
        System.out.println(result);


        security_val = Arrays.asList(6, 5, 3, -2, -4, 9, 16, 2);
        k = 2;

        int maxSecurityValue = gainMaxValue(security_val, k);

        System.out.println("Maximum Security Value: " + maxSecurityValue);


    }


}

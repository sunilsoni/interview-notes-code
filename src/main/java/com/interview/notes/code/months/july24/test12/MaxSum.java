package com.interview.notes.code.months.july24.test12;

import java.util.Arrays;
import java.util.List;

public class MaxSum {
    public static int findMaxSum(List<Integer> list) {
        if (list == null || list.size() < 2) {
            throw new IllegalArgumentException("List must contain at least two elements");
        }

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (Integer num : list) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax) {
                secondMax = num;
            }
        }

        return max + secondMax;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 9, 7, 11);
        // Should return 20, since 9 and 11 are the largest elements
        // and their sum is 20
        System.out.println(findMaxSum(list));
    }
}

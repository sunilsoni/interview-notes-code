package com.interview.notes.code.months.year2023.Oct23.test5;

import java.util.*;

/**
 * There is an array A consisting of N integers. What is the maximum sum of two integers from A that
 * share their first and last digits? For example, 1007 and 167 share their first (1) and last (7) digits,
 * whereas 2002 and 55 do not.
 * Write a function:
 * class Solution { public int solution(int[] A); )
 * that, given an array A consisting of N integers, returns the maximum sum of two integers that share
 * their first and last digits. If there are no two integers that share their first and last digits, the function
 * should return -1.
 * Examples:
 * 1. Given A = [130,191,200,10], the function should return 140. The only integers in A that share first
 * and last digits are 130 and 10.
 * 2. Given A = [405,45,300,3001, the function should return 600. There are two pairs of integers that
 * share first and last digits: (405,45) and (300,300). The sum of the two 300s is bigger than the sum
 * of 405 and 45.
 * 3. Given A = [50,222,49, 52,25], the function should return -1. There are no two integers that share
 * their first and last digits.
 * 4. Given A = [30,909,3190,99,3990,9009], the function should return 9918.
 * Write an efficient algorithm for the following assumptions:
 * •  N is an integer within the range [1 ..100,000];
 * •  each element of array A is an integer within the range [10..1,000,000,000].
 * Copyright 2009-2023 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited
 */
public class MaxSumOf2Ints {
    public static void main(String[] args) {
        MaxSumOf2Ints solution = new MaxSumOf2Ints();
        int[] test = {30, 909, 3190, 99, 3990, 9009};
        System.out.println(solution.solution(test));  // Expected: 9918
    }

    public int solution(int[] A) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (int num : A) {
            String numStr = String.valueOf(num);
            char firstDigit = numStr.charAt(0);
            char lastDigit = numStr.charAt(numStr.length() - 1);
            String key = firstDigit + "" + lastDigit;

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(num);
        }

        int maxSum = -1;
        for (List<Integer> list : map.values()) {
            Collections.sort(list, Collections.reverseOrder());
            if (list.size() > 1) {
                int sum = list.get(0) + list.get(1);
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}

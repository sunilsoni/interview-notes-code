package com.interview.notes.code.months.year2023.Oct23.test11;

import java.util.HashMap;

/**
 * You are given an array of integers. Your task is to create pairs of them, such that every pair consists of equal numbers. Each array
 * element may belong to one pair only. Is it possible to use all of the integers?
 * Write a function:
 * class Solution { public boolean solution(int[] A); }
 * that, given an array A consisting of N integers, returns whether it is possible to split all integers into pairs.
 * Examples:
 * 1. Given A = [1,2, 2,1], your function should return True, as the pairs are (A[0], A[3]) (both have value 1) and (A[1 L A[2]) (both have
 * value 2).
 * 2. Given A = [7,7,7], your function should return False, as you can make one pair of numbers 7, but you still have a single 7 left.
 * 3. Given A = [1,2, 2, 3], your function should return False, as there's nothing that A[0] can be paired with.
 * Write an efficient algorithm for the following assumptions:
 * • N is an integer within the range [1 ..100,000];
 * • each element of array A is an integer within the range [-1,000,000.-1,000,000].
 * Copyright 2009-2023 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
class Solution2 {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        // Test cases
        int[] test1 = {1, 2, 2, 1};
        int[] test2 = {7, 7, 1};
        int[] test3 = {1, 2, 2, 3};

        System.out.println(solution.solution(test1));  // Expected output: true
        System.out.println(solution.solution(test2));  // Expected output: false
        System.out.println(solution.solution(test3));  // Expected output: false
    }

    public boolean solution(int[] array) {
        // Using a HashMap
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int num : array) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Checking for Odd Counts
        for (int count : countMap.values()) {
            if (count % 2 != 0) {
                return false;
            }
        }

        // Return Result
        return true;
    }
}

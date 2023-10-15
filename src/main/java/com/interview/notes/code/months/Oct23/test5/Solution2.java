package com.interview.notes.code.months.Oct23.test5;

import java.util.HashMap;

/**
 * In Java
 *
 *
 *
 * Please explain the questions and requirements, and provide a step-by-step plan for solving them.
 *
 * Additionally, include code with explanations, and calculate time and space complexity at each step.
 *
 *
 *
 * You are given an array of integers. Your task is to create pairs of them, such that every pair consists
 * of equal numbers. Each array element may belong to one pair only. Is it possible to use all of the
 * integers?
 * Write a function:
 * class Solution { public boolean solution(int[] A); }
 * that, given an array A consisting of N integers, returns whether it is possible to split all integers into
 * pairs.
 * Examples:
 * 1. Given A = [1,2,2,1], your function should return True, as the pairs are (A[0], A[3]) (both have value
 * 1) and (A[1 L A[2]) (both have value 2).
 * 2. Given A = [7,7,7], your function shoul d return False, as you can make one pair of numbers 7, but
 * you still have a single 7 left.
 * 3. Given A = [1,2, 2,3], your function should return False, as there's nothing that A[0] can be paired
 * with.
 * Write an efficient algorithm for the following assumptions:
 * •   N is an integer within the range [1 ..100,000];
 * •   each element of array A is an integer within the range [-1,000,000.. 1,000,000].
 * Copyright 2009-2023 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */
class Solution2 {
    public boolean solution(int[] A) {
        // Initialize a HashMap to store frequency of each integer
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        // Variable to keep track of any odd frequencies
        int oddFrequencyCount = 0;

        // Traverse through array and update frequencies while checking parity
        for (int num : A) {
            int newFreq = frequencyMap.getOrDefault(num, 0) + 1;

            // Update the frequency in the HashMap
            frequencyMap.put(num, newFreq);

            // Check if the frequency is now odd or even and update oddFrequencyCount accordingly
            if (newFreq % 2 == 0) {
                oddFrequencyCount--;
            } else {
                oddFrequencyCount++;
            }
        }

        // If there are no elements with odd frequencies, then it's possible to make pairs
        return oddFrequencyCount == 0;
    }

    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        System.out.println(sol.solution(new int[]{1, 2, 2, 1}));  // Output should be true
    }
}

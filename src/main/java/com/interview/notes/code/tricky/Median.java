package com.interview.notes.code.tricky;

import java.util.Arrays;

/**
 * Given a sequence of integers, find its median.
 * Example
 * • For sequence = [-1, 3, -2, 2] , the output should be solution(sequence) = 0.5 • For sequence = [1, 3, 2] , the output should be solution(sequence) = 2 .
 * Input/Output
 * • [execution time limit] 3 seconds (Java) • [input] array.integer sequence An unsorted array of integers. Guaranteed constraints:
 * 2 sequence.length 105 -106 sequence[i] < 106 • [output] float The median of sequence .
 */
public class Median {

    public static void main(String[] args) {

    }
    public class Solution {
        public static double solution(int[] sequence) {
            Arrays.sort(sequence);
            int n = sequence.length;
            if (n % 2 == 0) {
                return (sequence[n/2-1] + sequence[n/2]) / 2.0;
            } else {
                return sequence[n/2];
            }
        }
    }

}

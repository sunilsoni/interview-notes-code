package com.interview.notes.code.july23.test4;

public class Solution {
    public static int solution(int N, int K) {
        // If the sum of all glasses' capacities is less than the target amount, it's impossible
        if ((long) N * (N + 1) / 2 < K) {
            return -1;
        }

        int count = 0;
        for (int i = N; i > 0; i--) {
            if (K >= i) {
                K -= i;
                count++;
            }
            if (K == 0) {
                return count;
            }
        }

        // This condition is actually redundant now because we already ensured
        // at the beginning that the total capacity is sufficient.
        if (K > 0) {
            return -1;
        }

        return count;
    }

    public static int solution1(int N, int K) {
        int count = 0;

        while (K > 0) {
            if (N <= K) {
                K -= N;
                count++;
            } else {
                N--;
            }

            if (K < N) {
                N = K;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 6));
        System.out.println(solution1(2, 6));
    }
}

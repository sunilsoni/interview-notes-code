package com.interview.notes.code.year.y2024.feb24.test8;


/**
 * Prime numbers less than N
 * You are given a non-negative integer N. Your task is to write a program that can print the number of prime numbers less than N.
 * Input
 * The input contains an integer N, representing the non-negative integer.
 * Output
 * Print the number of prime numbers less than N.
 * Constraints
 * 0 <= N <= 5 * 106
 * Example #1
 * Input
 * 10
 * Output
 * 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * Example #2
 * Input
 * 0
 * Output
 * 0
 */
class Outcome {
    public static int solve(int N) {
        if (N <= 2) return 0;
        boolean[] prime = new boolean[N];
        for (int i = 2; i < N; i++) prime[i] = true;
        for (int p = 2; p * p < N; p++) {
            if (prime[p]) {
                for (int i = p * p; i < N; i += p) prime[i] = false;
            }
        }
        int primeCount = 0;
        for (int i = 2; i < N; i++) {
            if (prime[i]) primeCount++;
        }
        return primeCount;
    }
}

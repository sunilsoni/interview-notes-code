package com.interview.notes.code.months.march24.test1;

import java.util.Arrays;


/**
 * Prime numbers less than N
 * You are given a non-negative integer N. Your task is to write a program that can print the number of prime numbers less than N.
 * Input
 * The input contains an integer N, representing the nonnegative integer.
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
 */
public class PrimenumberslessthanN {
    public static int solve(int N) {
        boolean[] prime = new boolean[N];
        if (N >= 2) {
            Arrays.fill(prime, true);
            prime[0] = false;
            prime[1] = false;
            for (int i = 2; i * i < N; i++) {
                if (prime[i]) {
                    for (int j = i * i; j < N; j += i) {
                        prime[j] = false;
                    }
                }
            }
        }
        int primeCount = 0;
        for (int i = 2; i < N; i++) {
            if (prime[i]) primeCount++;
        }
        return primeCount;
    }


    // maxArea method from the previous response here

}

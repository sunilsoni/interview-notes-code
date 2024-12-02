package com.interview.notes.code.year.y2023.nov23.test5;

/**
 * Prime numbers less than N
 * You are given a non-negative integer N. Your task is
 * to  write  a  program  that  can  print  the  number  ol
 * prime numbers less than N.
 * Input
 * The input contains an integer N, representing the
 * non-negative integer.
 * Output
 * Print the number of prime numbers less than N.
 * Constraints
 * 0<=N <=5*106
 * Example #1
 * Input
 * Explanation: There are 4 prime numbers less than
 * 10, they are 2,3, 5, 7.
 */
public class PrimeCounter {

    public static int solve(int N) {
        if (N <= 2) {
            return 0;
        }

        boolean[] isPrime = new boolean[N];
        for (int i = 2; i < N; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int primeCount = 0;
        for (int i = 2; i < N; i++) {
            if (isPrime[i]) {
                primeCount++;
            }
        }

        return primeCount;
    }

    public static void main(String[] args) {
        int N = 10; // Example input
        System.out.println(solve(N)); // Output: 4
    }
}

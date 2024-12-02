package com.interview.notes.code.year.y2023.dec23.test3;

import java.util.ArrayList;
import java.util.List;

public class Result3 {

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;

        if (number % 2 == 0 || number % 3 == 0) return false;

        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }

    public static List<Integer> primesEndingIn3(int n) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (isPrime(i) && i % 10 == 3) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static int maxGameScore(List<Integer> cell) {
        int n = cell.size();
        int[] dp = new int[n];
        List<Integer> primes = primesEndingIn3(n);

        dp[0] = 0;  // The pawn starts at cell 0 with score 0.

        for (int i = 0; i < n; i++) {
            // Move one step to the right
            if (i + 1 < n) {
                dp[i + 1] = Math.max(dp[i + 1], dp[i] + cell.get(i + 1));
            }
            // Move p steps to the right where p is a prime number ending in 3
            for (int p : primes) {
                if (i + p < n) {
                    dp[i + p] = Math.max(dp[i + p], dp[i] + cell.get(i + p));
                }
            }
        }

        // The maximum score will be at the last cell
        return dp[n - 1];
    }

    public static void main(String[] args) {
        List<Integer> example = List.of(0, -10, -20, -30, 50);
        System.out.println(maxGameScore(example));  // Expected output: 40

        List<Integer> example1 = List.of(0, -10, 100, -20);
        System.out.println(maxGameScore(example1));  // Expected output: 40

        // Add more test cases if needed
    }
}

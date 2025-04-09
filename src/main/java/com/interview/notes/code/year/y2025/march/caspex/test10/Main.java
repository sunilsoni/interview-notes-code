package com.interview.notes.code.year.y2025.march.caspex.test10;

import java.util.Arrays;

public class Main {
    private static final int MAX = 5_000_000;
    private static final boolean[] isPrime = new boolean[MAX + 1];
    private static final int[] prefix = new int[MAX + 1];

    static {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 1; i <= MAX; i++) {
            prefix[i] = prefix[i - 1] + (isPrime[i] ? 1 : 0);
        }
    }

    public static int solve(int N) {
        return (N <= 1) ? 0 : prefix[N - 1];
    }

    private static void test(int input, int expected) {
        int result = solve(input);
        System.out.println(result == expected ? "pass" : "fail");
    }

    public static void main(String[] args) {
        test(10, 4);
        test(0, 0);
        test(1, 0);
        test(2, 0);
        test(3, 1);
        test(5, 2);
        test(29, 9);
        test(30, 10);
        test(100, 25);
        // Additional large test
        test(5_000_000, 348513);
    }
}
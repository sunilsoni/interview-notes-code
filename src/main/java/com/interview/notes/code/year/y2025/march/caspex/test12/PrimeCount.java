package com.interview.notes.code.year.y2025.march.caspex.test12;

import java.util.Arrays;

public class PrimeCount {

    private static final int MAX = 5_000_000;
    private static int[] primeCount = new int[MAX + 1];

    static {
        boolean[] isPrime = new boolean[MAX + 1];
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
        int count = 0;
        for (int i = 0; i <= MAX; i++) {
            if (isPrime[i]) {
                count++;
            }
            primeCount[i] = count;
        }
    }

    public static int solve(int N) {
        if (N <= 1) return 0;
        if (N > MAX) return primeCount[MAX];
        return primeCount[N - 1];
    }

    public static void main(String[] args) {
        test(10, 4);  // Example #1
        test(0, 0);   // Example #2
        test(1, 0);
        test(2, 0);
        test(3, 1);
        test(5, 2);
        test(11, 4);
        test(100, 25);
        // Large input test
        test(5_000_000, primeCount[4_999_999]);
    }

    private static void test(int input, int expected) {
        int result = solve(input);
        if (result == expected) {
            System.out.println("Input=" + input + " PASS (Got " + result + ")");
        } else {
            System.out.println("Input=" + input + " FAIL (Got " + result 
                               + ", Expected " + expected + ")");
        }
    }
}

package com.interview.notes.code.months.aug24.test29;

public class PrimeNumbers {

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
        // Test cases
        System.out.println(solve(10));  // Output: 4
        System.out.println(solve(0));   // Output: 0
        System.out.println(solve(1));   // Output: 0
        System.out.println(solve(2));   // Output: 0
        System.out.println(solve(20));  // Output: 8
    }
}

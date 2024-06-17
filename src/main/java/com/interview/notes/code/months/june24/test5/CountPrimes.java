package com.interview.notes.code.months.june24.test5;

public class CountPrimes {

    public static void main(String[] args) {
        CountPrimes cp = new CountPrimes();

        System.out.println(cp.countPrimes(10)); // Output: 4
        System.out.println(cp.countPrimes(0));  // Output: 0
        System.out.println(cp.countPrimes(1));  // Output: 0
    }

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }
}

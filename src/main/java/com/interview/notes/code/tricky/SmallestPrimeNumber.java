package com.interview.notes.code.tricky;

/**
 * Find the smallest prime number strictly greater than the given n .
 * Example
 * • For n = 7 , the output should be solution (n) = 11 ; • For n = 12 , the output should be solution (n) = 13 .
 * Input/Output
 * • [execution time limit] 3 seconds (java) • [input] integer n A positive integer. Guaranteed constraints:
 * 1 < n < 107 .
 * • [output] integer
 */
public class SmallestPrimeNumber {
    public static int solution(int n) {
        while (true) {
            n++;
            if (isPrime(n)) {
                return n;
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}

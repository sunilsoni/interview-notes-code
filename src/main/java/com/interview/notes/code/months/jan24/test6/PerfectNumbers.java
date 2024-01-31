package com.interview.notes.code.months.jan24.test6;


/**
 * Perfect Number: A (positive integer) number is called a perfect number, if the sum of all of its factors, except the number itself, is equal to the number itself, then that number is called a perfect number.
 * For example, all the factors of number 6 are: 1, 2. 3, and 6. But, we have to remove 6 from that list; so if you add
 * 1 + 2 + 3: you get 6! So, 6 is a perfect number.
 * Problem Statement: Write a program to find all perfect numbers between 1 and given number N.
 * Interesting tidbits: Check out this interesting relationship between Perfect Numbers and Mersenne Primes:
 * www.youtube.com/watch?v=TOxKHwQH-4|
 */
public class PerfectNumbers {

    public static void main(String[] args) {
        int n = 100; // Change this to your desired upper limit

        for (int i = 1; i <= n; i++) {
            if (isPerfectNumber(i)) {
                System.out.println(i + " is a perfect number.");
            }
        }
    }

    private static boolean isPerfectNumber(int n) {
        int sumOfFactors = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sumOfFactors += i;
            }
        }
        return sumOfFactors == n;
    }
}
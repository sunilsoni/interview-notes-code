package com.interview.notes.code.months.year2023.june23.test12;

/**
 * In Java Perfect Number: A (positive integer) number is called a perfect number, if the sum of
 * all of its factors, except the number itself is equal to the number itself, then that
 * number is called a perfect number
 * For example, all the factors of number 6 are 1.2.3. and 6 But. we have to remove 6
 * from that list: so if you add 1 + 2 + 3: you get 6! So 6 is a perfect number
 * Problem Statement: Wnte a program to find all perfect numbers between 1 and
 * given number N
 * <p>
 * Interesting tidbits: Check out this interesting relationship between Perfect Numbers
 * and Mersenne Primes:
 * www.youtubecom/watch?v=ToxKHwQH-4l
 */
public class PerfectNumber {

    public static void main(String[] args) {
        int N = 1000;  // input number
        for (int i = 1; i <= N; i++) {
            if (isPerfectNumber(i)) {
                System.out.println(i + " is a perfect number.");
            }
        }
    }

    // Method to check if a number is perfect or not
    public static boolean isPerfectNumber(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }
}

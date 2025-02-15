package com.interview.notes.code.year.y2025.feb25.common.test5;

public class PrimeNumber {
    public static boolean isPrime(int number) {
        // Handle special cases
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        // Check from 5 to square root of number
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int number = 17;
        if (isPrime(number)) {
            System.out.println(number + " is a prime number");
        } else {
            System.out.println(number + " is not a prime number");
        }
    }
}

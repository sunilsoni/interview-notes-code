package com.interview.notes.code.year.y2024.april24.test4;

public class SumOfPrimes {
    public static void main(String[] args) {
        int inputNumber = 18; // Example input
        System.out.println("Input - " + inputNumber);

        // Flag to check if the input number can be expressed as the sum of two prime numbers
        boolean flag = false;

        // Loop through all numbers from 2 to half of the input number
        for (int i = 2; i <= inputNumber / 2; i++) {
            // Check if both i and inputNumber - i are prime
            if (isPrime(i) && isPrime(inputNumber - i)) {
                // If both numbers are prime, print them as a solution
                System.out.println(inputNumber + " = " + i + " + " + (inputNumber - i));
                flag = true; // We found at least one pair
            }
        }

        // If no pairs were found, indicate that the number cannot be expressed as a sum of two primes
        if (!flag)
            System.out.println(inputNumber + " cannot be expressed as the sum of two prime numbers.");
    }

    // Method to check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false; // 1 and negative numbers are not prime
        }

        // Check from 2 to the square root of num
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // num is divisible by i, so it is not prime
            }
        }

        // If no divisors were found, num is prime
        return true;
    }
}

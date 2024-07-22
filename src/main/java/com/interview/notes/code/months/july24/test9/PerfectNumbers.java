package com.interview.notes.code.months.july24.test9;

public class PerfectNumbers {

    public static void main(String[] args) {
        int N = 10000;  // You can change this value to any upper limit you want to check for perfect numbers.
        System.out.println("Perfect numbers up to " + N + ":");
        for (int i = 1; i <= N; i++) {
            if (isPerfect(i)) {
                System.out.println(i);
            }
        }
    }

    // Method to check if a number is perfect
    private static boolean isPerfect(int number) {
        if (number == 1) return false; // 1 is not a perfect number

        int sum = 1; // Start sum at 1 since 1 is a divisor of all numbers
        // Only iterate up to sqrt(number) to reduce computation
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                sum += i;
                int correspondingDivisor = number / i;
                if (correspondingDivisor != i) { // Avoid adding the square root twice if it's a perfect square
                    sum += correspondingDivisor;
                }
            }
        }
        return sum == number;
    }
}

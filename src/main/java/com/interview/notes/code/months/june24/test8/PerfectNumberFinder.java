package com.interview.notes.code.months.june24.test8;

public class PerfectNumberFinder {

    public static void main(String[] args) {
        int N = 10000;  // Example: Find all perfect numbers up to 10000
        System.out.println("Perfect numbers up to " + N + ":");
        for (int i = 1; i <= N; i++) {
            if (isPerfectNumber(i)) {
                System.out.println(i);
            }
        }
    }

    /**
     * Checks if a number is a perfect number.
     * A number is perfect if the sum of its proper divisors equals the number itself.
     *
     * @param number the number to check
     * @return true if the number is perfect, false otherwise
     */
    public static boolean isPerfectNumber(int number) {
        if (number < 2) { // 1 is not a perfect number
            return false;
        }

        int sum = 1; // Start with 1 because it is a proper divisor for all numbers > 1
        int sqrt = (int) Math.sqrt(number);

        // Check divisors from 2 up to the square root of the number
        for (int i = 2; i <= sqrt; i++) {
            if (number % i == 0) {
                sum += i; // Add the divisor
                int pairedDivisor = number / i;
                if (pairedDivisor != i) { // Avoid adding the square root twice if it's a perfect square
                    sum += pairedDivisor;
                }
            }
        }

        return sum == number;
    }
}

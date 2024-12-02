package com.interview.notes.code.year.y2023.july23.test3;

/**
 * Sure, I can certainly explain how the number 6 is a perfect number.
 * <p>
 * In number theory, a perfect number is a positive integer that is equal to the sum of its proper positive divisors, excluding the number itself.
 * <p>
 * Let's consider the number 6. First, let's find all the proper positive divisors of 6.
 * <p>
 * A divisor is a number that divides another number evenly, i.e., without leaving a remainder. The proper positive divisors of a number are all the divisors excluding the number itself.
 * <p>
 * The proper positive divisors of 6 are:
 * <p>
 * - 1 (since 1 divides every number)
 * - 2 (since 6 divided by 2 equals 3 with no remainder)
 * - 3 (since 6 divided by 3 equals 2 with no remainder)
 * <p>
 * Now, let's add these divisors together:
 * <p>
 * 1 + 2 + 3 = 6
 * <p>
 * So, the sum of the proper positive divisors of 6 is equal to 6 itself. This makes 6 a perfect number according to the definition.
 */
public class PerfectNumbers {
    public static void main(String[] args) {
        int N = 6; // Change this to the number you want
        for (int i = 1; i <= N; i++) {
            if (isPerfectNumber(i)) {
                System.out.println(i + " is a perfect number");
            }
        }
    }

    public static boolean isPerfectNumber(int number) {
        int sum = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum = sum + i;
            }
        }
        return sum == number;
    }
}

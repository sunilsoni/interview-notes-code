package com.interview.notes.code.year.y2023.june23.test9;


/**
 * Write a program to print all prime factors of a given number.
 * <p>
 * For example, if the input number is 12, then the output should be “2 2 3”. And if the input number is 315, then the output should be “3 3 5 7”.
 */
public class PrimeFactorsExample {
    public static void main(String[] args) {
        int number = 315;

        System.out.print("Prime factors of " + number + ": ");
        printPrimeFactors(number);
    }

    private static void printPrimeFactors(int number) {
        for (int i = 2; i <= number; i++) {
            while (number % i == 0) {
                System.out.print(i + " ");
                number /= i;
            }
        }
    }
}

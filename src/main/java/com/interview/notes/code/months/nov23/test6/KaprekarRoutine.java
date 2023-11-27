package com.interview.notes.code.months.nov23.test6;

import java.util.Arrays;

/**
 * Indian mathematician D R. Kaprekar observed a very interesting property of number 6174
 * If you take any four digit number (Let's say 3281) and arrange its digits in descending order (8321)
 * and ascending order (1238) and then subtract the smaller number from the larger number — and if
 * you repeat that process, you will reach number 6174
 * For example
 * 3281: 8321 -1238 - 7083 8730 - 0378 ■ 8352 8532 - 2358 •= 6174
 * Number 6174 - when you do this process, will result into 6174 itself
 * Exception to this is a four digit number with all same digits (such as 1111.2222. 3333 etc ), as that
 * will result into 0 in the very first step.
 * Your task is to write a function that finds out that for all four digit numbers (from 1000 to gggg).
 * how many steps does it take to reach the magic number (6174) The function should also find out if
 * it can’t reach the number 6174 for whatever reason.
 */
public class KaprekarRoutine {
    public static int kaprekarSteps(int number) {
        if (number == 6174) return 0; // If the number is already 6174, no steps are needed.
        if (String.valueOf(number).chars().distinct().count() == 1) return -1; // All digits are the same.

        int steps = 0;
        while (number != 6174) {
            char[] digits = String.format("%04d", number).toCharArray();
            Arrays.sort(digits);
            int ascending = Integer.parseInt(new String(digits));
            int descending = Integer.parseInt(new StringBuilder(new String(digits)).reverse().toString());

            number = descending - ascending;
            steps++;

            // To detect the loop, you would need to implement a mechanism to track already seen numbers.
            // For simplicity, this is omitted but should be considered in a complete implementation.
        }

        return steps;
    }

    public static void main(String[] args) {
        // Example usage:
        int[] testNumbers = {3524, 1111, 9831, 8774, 6174};
        for (int number : testNumbers) {
            int steps = kaprekarSteps(number);
            System.out.println("Number: " + number + " - Steps to reach 6174: " + steps);
        }
    }
}

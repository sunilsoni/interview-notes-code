package com.interview.notes.code.months.may24.test12;

import java.util.Arrays;

/**
 * Indian mathematician D.R. Kaprekar observed a very interesting property of number 6174.
 * If you take any four digit number (let's say 3281) and arrange its digits in descending order (8321) and ascending order (1238) and then subtract the smaller number from the larger number --and if you repeat that process, you will reach number 6174.
 * For example:
 * 3281:
 * 8321 - 1238 = 7083
 * 8730 - 0378 = 8352
 * 8532 - 2358 = 6174
 * Number 6174 - when you do this process, will result into 6174 itself.
 * <p>
 * Exception to this is a four digit number with all same digits (such as 1111, 2222, 3333 etc.), as thr will result into o in the very first step.
 */
public class KaprekarConstant {

    public static int findKaprekarConstant(int number) {
        if (number == 0) {
            return 0; // Exception case: All digits are same
        }

        int kaprekarConstant = 6174;

        while (number != kaprekarConstant) {
            int ascending = getAscendingOrder(number);
            int descending = getDescendingOrder(number);

            number = descending - ascending;

            System.out.println(descending + " - " + ascending + " = " + number);

            if (number == 0) {
                break; // Avoid infinite loop for numbers with all same digits
            }
        }

        return number;
    }

    private static int getAscendingOrder(int number) {
        char[] digits = Integer.toString(number).toCharArray();
        Arrays.sort(digits);
        return Integer.parseInt(new String(digits));
    }

    private static int getDescendingOrder(int number) {
        char[] digits = Integer.toString(number).toCharArray();
        Arrays.sort(digits);
        StringBuilder sb = new StringBuilder(new String(digits));
        return Integer.parseInt(sb.reverse().toString());
    }

    public static void main(String[] args) {
        int number = 3281;
        int result = findKaprekarConstant(number);
        System.out.println("Result: " + result);

        /**
         * 8321 - 1238 = 7083
         * 8730 - 378 = 8352
         * 8532 - 2358 = 6174
         * Result: 6174a
         */
    }
}

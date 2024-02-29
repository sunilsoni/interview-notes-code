package com.interview.notes.code.months.feb24.test9;

/**
 * Largest Odd Number
 * You are given a string S that has lowercase letters and numbers. Your task is to compare the number groupings and print the largest odd number. If there is no odd number present in the given string, return -1.
 * Input
 * The input contains a string S, that has lowercase letters and numbers.
 * Output
 * Return the largest odd number of all numeric groupings.
 * Constraints
 * 1 <= length of 5 <= 500
 * Example #1
 * Input
 * gt12cty65mt1
 * Output
 * 65
 * Explanation: There are 3 number groupings: 12, 65, and 1. As 65 is the greatest odd number, that is the answer.
 */
public class LargestOddNumber {
    public static void main(String[] args) {
        System.out.println(solve("gt12cty65mt1")); // Example 1
        System.out.println(solve("mkf43kdlcmk32klmv123")); // Example 2
    }

    public static int solve(String S) {
        int maxOddNumber = -1;
        StringBuilder numberBuffer = new StringBuilder();

        for (char c : S.toCharArray()) {
            if (Character.isDigit(c)) {
                numberBuffer.append(c);
            } else {
                maxOddNumber = updateMaxOdd(numberBuffer.toString(), maxOddNumber);
                numberBuffer = new StringBuilder();
            }
        }

        return updateMaxOdd(numberBuffer.toString(), maxOddNumber);
    }

    private static int updateMaxOdd(String numberStr, int currentMax) {
        if (!numberStr.isEmpty()) {
            int number = Integer.parseInt(numberStr);
            if (number % 2 != 0 && number > currentMax) {
                return number;
            }
        }
        return currentMax;
    }
}

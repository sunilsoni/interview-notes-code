package com.interview.notes.code.year.y2024.july24.test17;

/**
 * You are given a string S, which consists entirely of decimal digits (0-9). Using digits from S, create a palindromic number with the largest possible decimal value. You should use at least one digit and you can reorder the digits. A palindromic number remains the same when its digits are reversed; for instance, "7", "44" or "83238". Any palindromic number you create should not, however, have any leading zeros, such as in "0990" or "010".
 * For example, decimal palindromic numbers that can be created from "8199" are: "1", "8", "9", "99", "919" and "989".
 * Among them, "989" has the largest value.
 * Write a function:
 * class Solution { public String solution (String S); }
 * that, given a string S of N digits, returns the string representing the palindromic number with the largest value.
 * Examples:
 * 1. Given "39878", your function should return "898".
 * 2. Given "00900", your function should return "9".
 * 3. Given "0000", your function should return "0".
 * 4. Given "54321", your function should return "5".
 * Write an efficient algorithm for the following assumptions:
 * • N is an integer within the range [1.100,000);
 * • string S is made only of digits (0-9).
 */
class LargestValuePalindromicNumber {
    public String solution(String S) {
        int[] count = new int[10];
        for (char c : S.toCharArray()) {
            count[c - '0']++;
        }

        StringBuilder left = new StringBuilder();
        char middle = ' ';
        boolean hasMiddle = false;

        for (int i = 9; i >= 0; i--) {
            if (left.length() == 0 && i == 0) continue; // Skip leading zeros

            while (count[i] > 1) {
                left.append((char) (i + '0'));
                count[i] -= 2;
            }

            if (!hasMiddle && count[i] > 0) {
                middle = (char) (i + '0');
                hasMiddle = true;
                count[i]--;
            }
        }

        if (left.length() == 0 && !hasMiddle) {
            // If no non-zero digits were used, return "0"
            return "0";
        }

        StringBuilder result = new StringBuilder(left);
        if (hasMiddle) {
            result.append(middle);
        }
        result.append(left.reverse());

        return result.length() > 0 ? result.toString() : "0";
    }
}

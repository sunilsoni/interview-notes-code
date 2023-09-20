package com.interview.notes.code.months.july23.test11;

/**
 * You are given a string S, which consists entirely of decimal digits (0-9). Using digits from S, create a palindromic number with the
 * largest possible decimal value. You should use at least one digit and you can reorder the digits. A palindromic number remains the
 * same when its digits are reversed; for instance, "T, "44" or "83238”. Any palindromic number you create should not, however, have any
 * leading zeros, such as in "0990” or "010”.
 * For example, decimal palindromic numbers that can be created from "8199" are: "1", "8", "9", "99", "919" and "989". Among them, “989"
 * has the largest value.
 * Write a function:
 * class Solution { public String solution(String S); }
 * that, given a string S of N digits, returns the string representing the palindromic number with the largest value.
 * Examples:
 * 1. Given "39878", your function should return "898”.
 * 2. Given "00900", your function should return "9".
 * 3. Given "0000”, your function should return "0".
 * 4. Given "54321", your function should return "5".
 * Write an efficient algorithm for the following assumptions:
 * •  N is an integer within the range [1..100,000];
 * •  string S is made only of digits (0-9).
 */
class LargestPalindromicNumber {
    public static void main(String[] args) {
        LargestPalindromicNumber solution = new LargestPalindromicNumber();
        System.out.println(solution.solution("39878")); // 898
        System.out.println(solution.solution("00900")); // 9
        System.out.println(solution.solution("0000")); // 0
        System.out.println(solution.solution("54321")); // 5
    }

    public String solution(String S) {
        int[] counts = new int[10];

        // Count occurrences of each digit.
        for (char c : S.toCharArray()) {
            counts[c - '0']++;
        }

        // If all are zeros, return "0".
        if (counts[0] == S.length()) {
            return "0";
        }

        StringBuilder leftHalf = new StringBuilder();
        char middle = '\0';

        // Construct the palindrome.
        for (int i = 9; i >= 0; i--) {
            // Use symmetrically if there are 2 or more occurrences.
            while (counts[i] >= 2) {
                leftHalf.append(i);
                counts[i] -= 2;
            }

            // Save the largest unused digit.
            if (counts[i] == 1 && middle == '\0') {
                middle = (char) (i + '0');
            }
        }

        // Construct the right half by reversing the left half.
        StringBuilder rightHalf = new StringBuilder(leftHalf).reverse();

        // If middle character exists, add it.
        String result;
        if (middle != '\0') {
            result = leftHalf.toString() + middle + rightHalf.toString();
        } else {
            result = leftHalf.toString() + rightHalf.toString();
        }

        // Check if palindrome is of length 1 and if so, return only the middle digit.
        if (result.length() > 1 && result.charAt(0) == '0') {
            return Character.toString(result.charAt(result.length() / 2));
        } else {
            return result;
        }
    }
}

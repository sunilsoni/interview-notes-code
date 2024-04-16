package com.interview.notes.code.months.april24.test7;

import java.util.function.BiFunction;

/**
 * In a given char array there exists a palindrome somewhere in between.Write a java code to Return the max length of this palindrome using lambda expression.
 * <p>
 * Input: [s, f, k, a, e, a, k]
 * Output: 5
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        char[] input = {'s', 'f', 'k', 'a', 'e', 'a', 'k'};
        System.out.println("Output: " + findLongestPalindromeLength(input));
    }

    private static int findLongestPalindromeLength(char[] input) {
        BiFunction<char[], Integer, Integer> longestPalindrome = (arr, idx) -> {
            int maxLen = 1; // Every single character is a palindrome of length 1.
            // Try to expand as a palindrome around every index (consider odd and even length palindromes).
            for (int center = 0; center < arr.length; center++) {
                maxLen = Math.max(maxLen, expandAroundCenter(arr, center, center)); // Odd length
                maxLen = Math.max(maxLen, expandAroundCenter(arr, center, center + 1)); // Even length
            }
            return maxLen;
        };

        return longestPalindrome.apply(input, null);
    }

    private static int expandAroundCenter(char[] arr, int left, int right) {
        while (left >= 0 && right < arr.length && arr[left] == arr[right]) {
            left--;
            right++;
        }
        return right - left - 1; // Length of the palindrome
    }
}

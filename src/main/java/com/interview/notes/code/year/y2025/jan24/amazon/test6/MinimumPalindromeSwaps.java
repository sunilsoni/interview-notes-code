package com.interview.notes.code.year.y2025.jan24.amazon.test6;

import java.util.*;

public class MinimumPalindromeSwaps {

    public static void main(String[] args) {
        testMinPalindromeSwaps("1110", -1, 1);
        testMinPalindromeSwaps("101000", 1, 2);
        testMinPalindromeSwaps("0100101", 2, 3);
        testMinPalindromeSwaps("110011", 1, 4);
        testMinPalindromeSwaps("1100", 1, 5);
        testMinPalindromeSwaps("1001", 0, 6);
        testMinPalindromeSwaps("1", 0, 7);
        testMinPalindromeSwaps("0", 0, 8);
        testLargeInputTestCase(9);
        testLargeInputTestCaseImpossible(10);
    }

    public static int minPalindromeSwaps(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        if (!canFormPalindrome(s)) {
            return -1;
        }

        String palin1 = buildPalindrome(s, '0');
        String palin2 = buildPalindrome(s, '1');
        int minSwaps = Integer.MAX_VALUE;

        if (palin1 != null) {
            minSwaps = Math.min(minSwaps, countSwaps(s, palin1));
        }

        if (palin2 != null) {
            minSwaps = Math.min(minSwaps, countSwaps(s, palin2));
        }

        return minSwaps;
    }

    private static boolean canFormPalindrome(String s) {
        int count0 = 0;
        int count1 = 0;

        for (char c : s.toCharArray()) {
            if (c == '0') count0++;
            else count1++;
        }

        int oddCounts = 0;
        if (count0 % 2 != 0) oddCounts++;
        if (count1 % 2 != 0) oddCounts++;
        if (s.length() % 2 == 0) {
            return oddCounts == 0;
        } else {
            return oddCounts == 1;
        }
    }

    private static String buildPalindrome(String s, char midChar) {
        int n = s.length();
        int count0 = 0;
        int count1 = 0;

        for (char c : s.toCharArray()) {
            if (c == '0') count0++;
            else count1++;
        }

        char[] palin = new char[n];
        int left = 0, right = n - 1;

        int c0 = count0;
        int c1 = count1;

        while (left <= right) {
            if (left == right) {
                // Middle character for odd length
                palin[left] = midChar;
                if (midChar == '0') c0--;
                else c1--;
                left++;
                right--;
            } else {
                if (c0 >= 2) {
                    palin[left] = palin[right] = '0';
                    c0 -= 2;
                } else if (c1 >= 2) {
                    palin[left] = palin[right] = '1';
                    c1 -= 2;
                } else {
                    return null; // Not enough characters to fill
                }
                left++;
                right--;
            }
        }

        return new String(palin);
    }

    private static int countSwaps(String s, String palin) {
        int mismatches = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != palin.charAt(i)) {
                mismatches++;
            }
        }
        return mismatches / 2;
    }

    private static void testMinPalindromeSwaps(String input, int expected, int testCaseNumber) {
        int result = minPalindromeSwaps(input);
        boolean passed = result == expected;
        System.out.println("Test Case " + testCaseNumber + ": " + (passed ? "PASSED" : "FAILED"));
        if (!passed) {
            System.out.println("Input: \"" + input + "\"");
            System.out.println("Expected Output: " + expected);
            System.out.println("Actual Output:   " + result);
            System.out.println("-----------------------------------");
        }
    }

    private static void testLargeInputTestCase(int testCaseNumber) {
        // Generate a large palindrome string where swaps are needed
        int size = 200000;
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(i % 2 == 0 ? '0' : '1');
        }
        String input = sb.toString();
        int result = minPalindromeSwaps(input);
        boolean passed = result >= 0;
        System.out.println("Test Case " + testCaseNumber + ": " + (passed ? "PASSED (Large Input Test)" : "FAILED"));
        if (!passed) {
            System.out.println("Large input test failed. Actual Output: " + result);
            System.out.println("-----------------------------------");
        }
    }

    private static void testLargeInputTestCaseImpossible(int testCaseNumber) {
        // Generate a large string where palindrome is not possible
        int size = 200001;
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append('1');
        }
        String input = sb.toString();
        int result = minPalindromeSwaps(input);
        boolean passed = result == -1;
        System.out.println("Test Case " + testCaseNumber + ": " + (passed ? "PASSED (Large Impossible Test)" : "FAILED"));
        if (!passed) {
            System.out.println("Large impossible test failed. Actual Output: " + result);
            System.out.println("-----------------------------------");
        }
    }
}
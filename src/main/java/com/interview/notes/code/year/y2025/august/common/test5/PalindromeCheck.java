package com.interview.notes.code.year.y2025.august.common.test5;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PalindromeCheck {

    // Function to check if a string is palindrome
    public static boolean isPalindrome(String str) {
        // Convert to lowercase to make check case-insensitive
        str = str.toLowerCase();

        // Two pointers: left at start, right at end
        int left = 0;
        int right = str.length() - 1;

        // Compare characters moving inward
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // mismatch found, not palindrome
            }
            left++;  // move left pointer forward
            right--; // move right pointer backward
        }

        return true; // all matched, it is a palindrome
    }

    // Main method to test multiple cases
    public static void main(String[] args) {
        // Test cases with expected outputs
        Map<String, Boolean> testCases = new LinkedHashMap<>();
        testCases.put("radar", true);
        testCases.put("aba", true);
        testCases.put("kayak", true);
        testCases.put("hello", false);
        testCases.put("", true); // empty string is palindrome
        testCases.put("A", true); // single letter
        testCases.put("Radar", true); // case-insensitive check

        // Testing and printing PASS/FAIL
        testCases.forEach((input, expected) -> {
            boolean result = isPalindrome(input);
            if (result == expected) {
                System.out.println("PASS: \"" + input + "\" -> " + result);
            } else {
                System.out.println("FAIL: \"" + input + "\" -> " + result + " (Expected: " + expected + ")");
            }
        });

        // Large input test
        String largePalindrome = String.join("", Collections.nCopies(1000000, "a"));
        System.out.println("Large test -> " + isPalindrome(largePalindrome));
    }
}
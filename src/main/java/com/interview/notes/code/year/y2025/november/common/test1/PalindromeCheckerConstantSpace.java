package com.interview.notes.code.year.y2025.november.common.test1;

public class PalindromeCheckerConstantSpace {

    // O(n) time, O(1) space
    public static boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) return false;

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false; // early exit
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] tests = {"121", "12321", "123456", "1", "9876789", "1001", "10"};

        for (String t : tests) {
            System.out.println("Input: " + t + " | IsPalindrome: " + isPalindrome(t));
        }
    }
}

package com.interview.notes.code.year.y2026.jan.common.test2;

import java.util.List;

public class Main {                               // Main class name kept simple for easy run.

    static boolean isPalindromeNumber(String raw) {                  // Core function to check palindrome.
        if (raw == null) return false;                               // Null cannot be a palindrome.
        String s = raw.trim();                                       // Remove spaces so input like " 121 " works.
        if (s.isEmpty()) return false;                               // Empty is not a valid number.
        if (s.charAt(0) == '-') return false;                        // Negative numbers are not palindromes here.
        int l = 0, r = s.length() - 1;                               // Two pointers: left and right ends.
        while (l < r) {                                              // Move inward until pointers meet.
            char a = s.charAt(l);                                    // Take left character.
            char b = s.charAt(r);                                    // Take right character.
            if (a != b) return false;                                // If mismatch, not a palindrome.
            l++;                                                     // Move left pointer right.
            r--;                                                     // Move right pointer left.
        }
        return s.chars().allMatch(Character::isDigit);               // Ensure it is really a number (digits only).
    }

    static boolean runOne(TestCase tc) {                             // Runs one test and prints PASS/FAIL.
        boolean got = isPalindromeNumber(tc.input());                // Compute actual result.
        boolean ok = (got == tc.expected());                         // Compare with expected.
        System.out.println(
                (ok ? "PASS" : "FAIL") + " | " + tc.name() +             // Print status + test name.
                        " | input=" + tc.input() +                               // Show the input used.
                        " | expected=" + tc.expected() +                         // Show expected answer.
                        " | got=" + got                                          // Show actual answer.
        );
        return ok;                                                   // Return test result for final summary.
    }

    static String bigPalindrome(int halfLen) {                       // Builds a very large palindrome string.
        String base = "0123456789";                                  // Repeating pattern to build big data fast.
        StringBuilder left = new StringBuilder(halfLen);             // Left half storage with capacity.
        while (left.length() < halfLen) left.append(base);           // Keep appending until reaching size.
        left.setLength(halfLen);                                     // Trim exactly to halfLen.
        // Full palindrome capacity (2 * half).
        String all = String.valueOf(left) +                                            // Add left part.
                new StringBuilder(left).reverse();               // Add reverse(left) to make palindrome.
        return all;                                       // Return the huge palindrome.
    }

    static String bigNonPalindrome(int halfLen) {                    // Builds a very large non-palindrome string.
        String p = bigPalindrome(halfLen);                           // Start from a valid palindrome.
        char flip = (p.charAt(0) == '0') ? '1' : '0';                // Pick a different digit to break it.
        return flip + p.substring(1);                                // Change first char so it is NOT palindrome.
    }

    public static void main(String[] args) {                         // Main method: runs all tests.
        List<TestCase> tests = List.of(                              // Basic and edge tests.
                new TestCase("pal_121", "121", true),                    // Classic palindrome.
                new TestCase("not_123", "123", false),                   // Classic non-palindrome.
                new TestCase("single_digit", "7", true),                 // Single digit always palindrome.
                new TestCase("zero", "0", true),                         // Zero is palindrome.
                new TestCase("ends_with_zero", "10", false),             // "10" reversed is "01".
                new TestCase("negative", "-121", false),                 // Negative rejected.
                new TestCase("spaces_ok", "  1221  ", true),             // Trimming should still work.
                new TestCase("not_a_number", "12a21", false)             // Non-digit should fail.
        );

        int pass = 0;                                                // Count of passing tests.
        for (var tc : tests) if (runOne(tc)) pass++;                 // Run each test and count PASS.

        // Large data tests (performance + correctness).
        var big1 = bigPalindrome(100_000);                           // 200k length palindrome test input.
        var big2 = bigNonPalindrome(100_000);                        // 200k length non-palindrome input.

        boolean bigOk1 = isPalindromeNumber(big1);           // Expect true for huge palindrome.
        boolean bigOk2 = !isPalindromeNumber(big2);          // Expect false for huge non-palindrome.

        System.out.println((bigOk1 ? "PASS" : "FAIL") + " | big_palindrome_200k");     // Print big test result.
        System.out.println((bigOk2 ? "PASS" : "FAIL") + " | big_non_palindrome_200k"); // Print big test result.

        int total = tests.size() + 2;                                // Total tests = small tests + 2 big tests.
        int totalPass = pass + (bigOk1 ? 1 : 0) + (bigOk2 ? 1 : 0);  // Total passes including big tests.

        System.out.println("SUMMARY: " + totalPass + "/" + total + " tests passed."); // Final summary line.
    }

    // Java 21 record: small immutable holder for a test case (name, input, expected).
    record TestCase(String name, String input, boolean expected) {
    }  // We store test data cleanly.
}

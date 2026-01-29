package com.interview.notes.code.year.y2026.jan.microsoft.test6;

public class PalindromeNoStream {

    // Method to check palindrome using Two-Pointer algorithm
    static boolean isPalindrome(String str) {
        // Check for null input first to prevent system crashes (NullPointerException)
        if (str == null) return false;

        // Initialize 'left' pointer at the start (index 0) using 'var' for brevity
        var left = 0;

        // Initialize 'right' pointer at the last character index
        var right = str.length() - 1;

        // Loop as long as the left pointer is before the right pointer
        while (left < right) {
            // Compare characters: If mismatch found, it is immediately NOT a palindrome
            // We increment 'left' (left++) and decrement 'right' (right--) in the same line to reduce code lines
            if (str.charAt(left++) != str.charAt(right--)) return false;
        }

        // If loop finishes without returning false, all characters matched correctly
        return true;
    }

    // Main method to run tests without JUnit
    public static void main(String[] args) {
        // Print start message for clarity
        System.out.println("Starting Test Suite...");

        // --- Test 1: Standard Palindrome ---
        // Expect PASS for "level"
        test("level", true);

        // --- Test 2: Standard Non-Palindrome ---
        // Expect PASS (result matches expected false) for "world"
        test("world", false);

        // --- Test 3: Edge Case (Empty String) ---
        // Empty string is technically a palindrome
        test("", true);

        // --- Test 4: Edge Case (Single Character) ---
        // Single char is always a palindrome
        test("x", true);

        // --- Test 5: Large Data (1 Million Chars) ---
        // Use Java 11 'repeat' to quickly build a massive string: "A" * 500k + "b" + "A" * 500k
        var largePass = "A".repeat(500000) + "b" + "A".repeat(500000);
        test(largePass, true);

        // --- Test 6: Large Data Fail Case ---
        // Build massive string that fails at the very end
        var largeFail = "A".repeat(1000000) + "B";
        test(largeFail, false);
    }

    // Helper method to execute and format test results
    static void test(String input, boolean expected) {
        // Capture start time to measure performance for large data
        var start = System.currentTimeMillis();

        // Run the logic
        var actual = isPalindrome(input);

        // Determine if test PASSED or FAILED
        var result = (actual == expected) ? "PASS" : "FAIL";

        // Truncate long inputs for display purposes so console isn't flooded
        var disp = (input != null && input.length() > 10) ? "LargeData..." : input;

        // Print final formatted report using printf
        System.out.printf("[%s] Input: %-12s | Time: %dms%n", result, disp, (System.currentTimeMillis() - start));
    }
}
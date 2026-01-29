package com.interview.notes.code.year.y2026.jan.microsoft.test7;

public class PalindromeSpecial {

    static boolean isPalindrome(String str) {
        if (str == null) return false; // Basic safety check

        var left = 0;
        var right = str.length() - 1;

        while (left < right) {
            var charLeft = str.charAt(left);
            var charRight = str.charAt(right);

            // 1. If Left char is junk (symbol/space), skip it by moving right
            if (!Character.isLetterOrDigit(charLeft)) {
                left++;
            }
            // 2. If Right char is junk, skip it by moving left
            else if (!Character.isLetterOrDigit(charRight)) {
                right--;
            }
            // 3. Both are valid letters. Check if they match (ignoring case)
            else {
                if (Character.toLowerCase(charLeft) != Character.toLowerCase(charRight)) {
                    return false; // Mismatch found
                }
                // Move both pointers inward after a successful match
                left++;
                right--;
            }
        }
        return true; // Met in middle, success
    }

    // --- Testing (Main Method) ---
    public static void main(String[] args) {
        System.out.println("--- Running Special Character Tests ---");

        // Test 1: Space handling
        test("race car", true);

        // Test 2: Complex punctuation and Case
        test("A man, a plan, a canal: Panama", true);

        // Test 3: Only symbols (effectively empty)
        test(".,?!", true);

        // Test 4: Fail case with symbols
        test("race a car", false);

        // Test 5: Numbers and letters
        test("M1 madam 1m", true);
    }

    static void test(String input, boolean expected) {
        var result = isPalindrome(input);
        var status = (result == expected) ? "PASS" : "FAIL";
        System.out.printf("[%s] Input: '%s' | Got: %b%n", status, input, result);
    }
}
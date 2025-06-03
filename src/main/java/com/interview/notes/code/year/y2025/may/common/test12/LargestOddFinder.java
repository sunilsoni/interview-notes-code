package com.interview.notes.code.year.y2025.may.common.test12;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LargestOddFinder {

    /**
     * Extracts all numeric substrings from input S, filters odd ones, 
     * and returns the largest odd number as a string. Returns "-1" if none.
     */
    public static String largestOddNumber(String S) {
        // Pattern to match one or more consecutive digits
        Pattern digitPattern = Pattern.compile("\\d+");
        Matcher matcher = digitPattern.matcher(S);  // Create a matcher on the input

        BigInteger maxOdd = null;  // Will hold the largest odd BigInteger found

        // Iterate through all occurrences of digit-groups in S
        while (matcher.find()) {
            String digitGroup = matcher.group();  
            // digitGroup is a substring of S consisting only of digits
            // e.g., if S = "a12b3c45", digitGroup sequence: "12", "3", "45"

            // Check the last character of digitGroup for oddness
            char lastChar = digitGroup.charAt(digitGroup.length() - 1);
            // 'lastChar' is something like '2', '3', '5', etc.

            // Convert the char digit to integer: subtract '0'
            int lastDigit = lastChar - '0';  
            // Now lastDigit is an int between 0 and 9

            // If lastDigit % 2 != 0, it’s an odd number
            if (lastDigit % 2 != 0) {
                // Parse the entire digitGroup into BigInteger for safe comparison
                BigInteger currentValue = new BigInteger(digitGroup);
                // If maxOdd is null (no odd found yet), or currentValue > maxOdd, update
                if (maxOdd == null || currentValue.compareTo(maxOdd) > 0) {
                    maxOdd = currentValue;  
                    // Now maxOdd holds the largest odd found so far
                }
            }
            // If the number is even (lastDigit%2==0), skip it
        }

        // After scanning all digit groups:
        if (maxOdd == null) {
            // No odd substrings found → return "-1"
            return "-1";
        } else {
            // Return the string form of the largest odd BigInteger
            return maxOdd.toString();
        }
    }

    /**
     * Helper to run a single test, compare actual vs expected, and print PASS/FAIL.
     */
    private static void runTest(String input, String expected) {
        String actual = largestOddNumber(input);
        if (actual.equals(expected)) {
            System.out.println("PASS | Input: \"" + input + "\" | Expected: \"" 
                                + expected + "\" | Actual: \"" + actual + "\"");
        } else {
            System.out.println("FAIL | Input: \"" + input + "\" | Expected: \"" 
                                + expected + "\" | Actual: \"" + actual + "\"");
        }
    }

    /**
     * Main method to run multiple test cases (including edge cases and large inputs).
     */
    public static void main(String[] args) {
        System.out.println("=== Running Provided Example Tests ===");
        // Example 1: "gt12cty65mt1" → groups: 12, 65, 1 → odd: 65, 1 → max = 65
        runTest("gt12cty65mt1", "65");  

        // Example 2: "mkf43kd1cmk32k1mv123" → groups: 43,1,32,1,123 → odd: 43,1,1,123 → max = 123
        runTest("mkf43kd1cmk32k1mv123", "123");

        System.out.println("\n=== Running Edge Case Tests ===");
        // 1. No digits at all → should return "-1"
        runTest("abcdef", "-1");

        // 2. All even digit-groups → "a2468b0c" has groups 2468 and 0 → both even → "-1"
        runTest("a2468b0c", "-1");

        // 3. Leading zeros on odd group → "z007y5" groups: "007"(→7 odd), "5"(→5) → max = 7
        runTest("z007y5", "7");

        // 4. Single-digit odd/even
        runTest("x2y", "-1");   // only "2" (even) → "-1"
        runTest("x9y", "9");    // only "9" (odd) → "9"

        // 5. Multiple identical odd numbers → "a11b11c" → two "11"s → max = 11
        runTest("a11b11c", "11");

        // 6. Very large numeric substring (250 digits), all odd → generate 250-digit odd number
        StringBuilder largeOddBuilder = new StringBuilder();
        for (int i = 0; i < 250; i++) {
            largeOddBuilder.append('1');  // Build a 250-character string of '1's
        }
        String largeOdd = largeOddBuilder.toString();
        runTest(largeOdd, largeOdd);

        // 7. Very large even-digit substring mixed with a smaller odd → should pick smaller odd
        StringBuilder largeEvenBuilder = new StringBuilder();
        for (int i = 0; i < 250; i++) {
            largeEvenBuilder.append('2');  // 250-digit “2222…” (all even)
        }
        String largeEven = largeEvenBuilder.toString();
        String mixed = largeEven + "3";  // “2222…2223” → last substring is “2222…2223”, an odd 251-digit number
        runTest(mixed, mixed);  

        System.out.println("\n=== Running Additional Random / Large Input Test ===");
        // 8. Generate a 500-character random mixture of letters/digits, 
        //    then just ensure it completes quickly and returns something.
        String randomInput = generateRandomMixedString(500);
        // We don’t know expected in advance, so just print the result
        String result = largestOddNumber(randomInput);
        System.out.println("Random 500-char input result: " + result);
    }

    /**
     * Generates a random string of length 'len' composed of lowercase letters (a–z) 
     * and digits (0–9). Used for performance testing.
     */
    private static String generateRandomMixedString(int len) {
        StringBuilder sb = new StringBuilder(len);
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        java.util.Random rand = new java.util.Random(0);  
        // Use fixed seed (0) so result is reproducible if needed
        for (int i = 0; i < len; i++) {
            int idx = rand.nextInt(chars.length());
            sb.append(chars.charAt(idx));
        }
        return sb.toString();
    }
}
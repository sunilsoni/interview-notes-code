package com.interview.notes.code.months.nov24.amazon.test15;

import java.math.BigInteger;

public class ProductCodeGenerator {

    public static String findSmallestAppealing(String old_code, int k) {
        int n = old_code.length();
        StringBuilder sb = new StringBuilder(old_code);

        // Check if the old_code is already attractive
        boolean isAttractive = true;
        for (int i = 0; i < n - k; i++) {
            if (sb.charAt(i) != sb.charAt(i + k)) {
                isAttractive = false;
                break;
            }
        }
        if (isAttractive) return old_code;

        // Find the first position where the pattern breaks
        int pos = 0;
        while (pos < n - k && sb.charAt(pos) == sb.charAt(pos + k)) {
            pos++;
        }

        // Increment the substring from pos to pos+k
        for (int i = pos + k - 1; i >= pos; i--) {
            if (sb.charAt(i) < '9') {
                sb.setCharAt(i, (char) (sb.charAt(i) + 1));
                break;
            } else {
                sb.setCharAt(i, '0');
                if (i == pos) {
                    sb.insert(0, '1');
                    n++;
                }
            }
        }

        // Propagate the pattern
        for (int i = pos + k; i < n; i++) {
            sb.setCharAt(i, sb.charAt(i - k));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // Test cases
        testCase("41242", 4, "41244");
        testCase("353", 2, "353");
        testCase("1234", 2, "1313");
        testCase("9999", 3, "10001");
        testCase("12345", 1, "22222");

        // Large input test case
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 200000; i++) {
            largeInput.append((char) ('0' + (i % 10)));
        }
        testCase(largeInput.toString(), 100000, null); // Expected output is too large to specify

        System.out.println("All test cases completed.");
    }

    private static void testCase(String old_code, int k, String expected) {
        String result = findSmallestAppealing(old_code, k);
        boolean passed = expected == null || result.equals(expected);

        System.out.println("Test case: old_code = " + old_code + ", k = " + k);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));

        if (expected != null) {
            System.out.println("Expected: " + expected);
        }

        // Additional checks for large inputs
        if (old_code.length() > 1000) {
            System.out.println("Large input validation:");
            System.out.println("Result length: " + result.length());
            System.out.println("Is attractive: " + isAttractive(result, k));
            System.out.println("Is greater or equal: " + isGreaterOrEqual(result, old_code));
        }

        System.out.println();
    }

    private static boolean isAttractive(String code, int k) {
        for (int i = 0; i < code.length() - k; i++) {
            if (code.charAt(i) != code.charAt(i + k)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isGreaterOrEqual(String newCode, String oldCode) {
        return new BigInteger(newCode).compareTo(new BigInteger(oldCode)) >= 0;
    }
}

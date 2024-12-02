package com.interview.notes.code.year.y2024.nov24.amazon.test16;

public class ProductCodeSolution {
    public static String findSmallestAppealing(String old_code, int k) {
        char[] code = old_code.toCharArray();
        int n = code.length;

        // Check if current code is already attractive
        boolean isAttractive = true;
        for (int i = 0; i < n - k; i++) {
            if (code[i] != code[i + k]) {
                isAttractive = false;
                break;
            }
        }

        if (isAttractive) {
            return old_code;
        }

        // Generate smallest appealing number
        while (true) {
            // Make number attractive by copying first k digits
            for (int i = 0; i < n - k; i++) {
                if (code[i + k] < code[i]) {
                    // Need to increment earlier digits
                    int carry = 1;
                    for (int j = k - 1; j >= 0; j--) {
                        int digit = code[j] - '0' + carry;
                        carry = digit / 10;
                        code[j] = (char) ((digit % 10) + '0');
                    }
                }
                code[i + k] = code[i];
            }

            // Check if generated number is >= old_code
            String newCode = new String(code);
            if (newCode.compareTo(old_code) >= 0) {
                return newCode;
            }

            // If not, increment the first k digits
            int carry = 1;
            for (int i = k - 1; i >= 0; i--) {
                int digit = code[i] - '0' + carry;
                carry = digit / 10;
                code[i] = (char) ((digit % 10) + '0');
            }
        }
    }

    public static void main(String[] args) {
        // Test cases
        testCase("41242", 4, "41244", "Test Case 1");
        testCase("353", 2, "353", "Test Case 2");
        testCase("1234", 2, "1313", "Test Case 3");
        testCase("999", 2, "999", "Test Case 4");
        testCase("12345", 3, "12312", "Test Case 5");

        // Large input test case
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("1");
        }
        testCase(largeInput.toString(), 50000, largeInput.toString(), "Large Input Test");

        // Edge cases
        testCase("1", 1, "1", "Single Digit Test");
        testCase("999999", 3, "999999", "All Nines Test");
    }

    private static void testCase(String old_code, int k, String expected, String testName) {
        try {
            String result = findSmallestAppealing(old_code, k);
            boolean passed = result.equals(expected);
            System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("  Expected: " + expected);
                System.out.println("  Got: " + result);
            }
        } catch (Exception e) {
            System.out.println(testName + ": FAIL (Exception)");
            e.printStackTrace();
        }
    }
}
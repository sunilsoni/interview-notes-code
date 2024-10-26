package com.interview.notes.code.months.oct24.amz.test13;

public class SpecialStringGenerator {

    public static String getSpecialString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        // Start from the end of the string
        for (int i = n - 1; i >= 0; i--) {
            // Try to increment the current character
            if (chars[i] < 'z') {
                chars[i]++;
                // Fill the rest of the string with the smallest valid characters
                for (int j = i + 1; j < n; j++) {
                    chars[j] = 'a';
                    // Ensure no adjacent characters are the same
                    if (j > 0 && chars[j] == chars[j - 1]) {
                        chars[j] = (char) (chars[j] + 1);
                    }
                }
                // Check for adjacent duplicates
                if (isSpecialString(chars)) {
                    return new String(chars);
                }
            }
        }
        return "-1"; // No valid special string found
    }

    private static boolean isSpecialString(char[] chars) {
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {"abccde", "zzab", "a", "zzzz", "abcde"};
        for (String testCase : testCases) {
            String result = getSpecialString(testCase);
            System.out.println("Input: " + testCase + " -> Output: " + result);
        }

        // Additional test case for large input
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            largeInput.append('a');
        }
        System.out.println("Large Input Test -> Output: " + getSpecialString(largeInput.toString()));
    }
}
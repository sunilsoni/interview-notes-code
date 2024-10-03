package com.interview.notes.code.months.sept24.amazon.test7;

public class Solution3 {

    public static String getSpecialString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        // Start from the rightmost character
        for (int i = n - 1; i >= 0; i--) {
            // Try to increment the current character
            for (char c = (char) (chars[i] + 1); c <= 'z'; c++) {
                // Check if the new character is valid
                if ((i == 0 || c != chars[i - 1]) && (i == n - 1 || c != chars[i + 1])) {
                    chars[i] = c;

                    // Fill the rest with the smallest possible characters
                    for (int j = i + 1; j < n; j++) {
                        for (char nc = 'a'; nc <= 'z'; nc++) {
                            if (nc != chars[j - 1] && (j == n - 1 || nc != chars[j + 1])) {
                                chars[j] = nc;
                                break;
                            }
                        }
                    }

                    return new String(chars);
                }
            }
        }

        // If no solution found
        return "-1";
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {"abbd", "abccde", "zzab"};
        String[] expectedOutputs = {"abca", "abcdab", "-1"};

        for (int i = 0; i < testCases.length; i++) {
            String result = getSpecialString(testCases[i]);
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + testCases[i]);
            System.out.println("Output: " + result);
            System.out.println("Expected: " + expectedOutputs[i]);
            System.out.println("Result: " + (result.equals(expectedOutputs[i]) ? "PASS" : "FAIL"));
            System.out.println();
        }
    }
}
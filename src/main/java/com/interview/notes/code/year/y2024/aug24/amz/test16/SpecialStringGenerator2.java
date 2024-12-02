package com.interview.notes.code.year.y2024.aug24.amz.test16;

import java.util.Scanner;

public class SpecialStringGenerator2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example test cases
        String[] testCases = {"abbd", "abccde", "zzab", "xyzxyx", "abc", "a"};

        for (String testCase : testCases) {
            String result = getSpecialString(testCase);
            System.out.println("Input: " + testCase + " -> Output: " + result);
        }

        scanner.close();
    }

    public static String getSpecialString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        // Step 1: Find the rightmost character that can be incremented
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] < 'z') {
                chars[i]++;
                // Step 2: Fill the rest of the string with the smallest valid characters
                for (int j = i + 1; j < n; j++) {
                    chars[j] = 'a'; // Start with 'a'
                }
                // Step 3: Ensure no adjacent characters are the same
                if (isSpecialString(chars)) {
                    return new String(chars);
                } else {
                    // Adjust to ensure no adjacent duplicates
                    for (int j = 1; j < n; j++) {
                        if (chars[j] == chars[j - 1]) {
                            chars[j]++;
                            // If we exceed 'z', we need to backtrack
                            if (chars[j] > 'z') {
                                return "-1"; // No valid string can be formed
                            }
                        }
                    }
                    // Final check after adjustments
                    if (isSpecialString(chars)) {
                        return new String(chars);
                    }
                }
            }
        }
        return "-1"; // No valid special string found
    }

    private static boolean isSpecialString(char[] chars) {
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                return false; // Found adjacent duplicates
            }
        }
        return true; // No adjacent duplicates
    }
}
package com.interview.notes.code.months.aug24.amz.test16;

import java.util.Scanner;

public class SpecialStringGenerator1 {

    public static void main(String[] args) {
        // Test cases
        System.out.println(getSpecialString("abbd"));  // Expected: abca
        System.out.println(getSpecialString("abccde"));  // Expected: abcdab
        System.out.println(getSpecialString("zzab"));  // Expected: -1
        System.out.println(getSpecialString("xyzxyx"));  //

        // Additional test cases
        System.out.println(getSpecialString("a"));  // Expected: b
        System.out.println(getSpecialString("z"));  // Expected: -1
        System.out.println(getSpecialString("az"));  // Expected: ba
        System.out.println(getSpecialString("zz"));  // Expected: -1
        System.out.println(getSpecialString("abcdefghijklmnopqrstuvwxyz"));  // Expected: -1
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example test cases
        String[] testCases = {"abbd", "abccde", "zzab", "abc", "a"};

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
                    // Ensure no adjacent characters are the same
                    if (j > 0 && chars[j] == chars[j - 1]) {
                        chars[j]++;
                    }
                }
                // Step 3: Check for adjacent duplicates
                if (isSpecialString(chars)) {
                    return new String(chars);
                }
            }
        }
        return "-1"; // No valid special string found
    }

    public static String getSpecialString1(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        // Step 1: Find the rightmost character that can be incremented
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] < 'z') {
                chars[i]++;
                // Step 2: Ensure no adjacent characters are the same
                for (int j = i + 1; j < n; j++) {
                    chars[j] = 'a'; // Start from 'a' to ensure lexicographical order
                    // Ensure no adjacent characters are the same
                    if (j > 0 && chars[j] == chars[j - 1]) {
                        chars[j]++;
                    }
                }
                // Step 3: Check for adjacent duplicates
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
                return false; // Found adjacent duplicates
            }
        }
        return true; // No adjacent duplicates
    }
}
package com.interview.notes.code.months.sept24.amazon.test6;

import java.util.*;

public class Solution {

    public static String getSpecialString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        
        // Start from the rightmost character
        for (int i = n - 1; i >= 0; i--) {
            char original = chars[i];
            // Try to increment the current character
            for (char c = (char)(original + 1); c <= 'z'; c++) {
                // Check if the new character is valid
                if ((i == 0 || c != chars[i-1]) && (i == n-1 || c != chars[i+1])) {
                    chars[i] = c;
                    
                    // Fill the rest with the smallest possible characters
                    if (fillRest(chars, i + 1)) {
                        return new String(chars);
                    }
                }
            }
            // Restore the original character if no valid increment found
            chars[i] = original;
        }
        
        // If no solution found
        return "-1";
    }

    private static boolean fillRest(char[] chars, int start) {
        for (int i = start; i < chars.length; i++) {
            boolean found = false;
            for (char c = 'a'; c <= 'z'; c++) {
                if ((i == 0 || c != chars[i-1]) && (i == chars.length-1 || c != chars[i+1])) {
                    chars[i] = c;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {"abbd", "abccde", "zzab"};
        String[] expectedOutputs = {"abca", "abcdab", "-1"};

        for (int i = 0; i < testCases.length; i++) {
            String result = getSpecialString(testCases[i]);
            System.out.println("Test Case " + (i+1) + ":");
            System.out.println("Input: " + testCases[i]);
            System.out.println("Output: " + result);
            System.out.println("Expected: " + expectedOutputs[i]);
            System.out.println("Result: " + (result.equals(expectedOutputs[i]) ? "PASS" : "FAIL"));
            System.out.println();
        }
    }
}
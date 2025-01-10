package com.interview.notes.code.year.y2025.jan24.test5;

import java.util.Arrays;

public class LongestCommonSubstring {
    public static String findLongestCommonSubstring(String[] arr) {
        if (arr == null || arr.length < 2) {
            return "";
        }

        String longestSubstring = "";
        
        // Compare each pair of strings
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                String common = findCommonSubstring(arr[i], arr[j]);
                if (common.length() > longestSubstring.length()) {
                    longestSubstring = common;
                }
            }
        }
        
        return longestSubstring;
    }

    private static String findCommonSubstring(String str1, String str2) {
        String longest = "";
        
        // Check all possible substrings of str1
        for (int i = 0; i < str1.length(); i++) {
            for (int j = i + 1; j <= str1.length(); j++) {
                String current = str1.substring(i, j);
                // Check if substring has no repeating chars and exists in str2
                if (hasNoRepeatingChars(current) && 
                    str2.contains(current) && 
                    current.length() > longest.length()) {
                    longest = current;
                }
            }
        }
        return longest;
    }

    private static boolean hasNoRepeatingChars(String str) {
        boolean[] seen = new boolean[128];
        for (char c : str.toCharArray()) {
            if (seen[c]) return false;
            seen[c] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        runTest("Test 1 - Given Array", 
            new String[]{"India", "Individual", "Indefinite", "Indifferent", "Flow", "Flower", "Power"},
            "Indi");

        runTest("Test 2 - Empty Array", 
            new String[]{},
            "");

        runTest("Test 3 - Single String", 
            new String[]{"Hello"},
            "");

        runTest("Test 4 - No Common Substring", 
            new String[]{"abc", "def", "ghi"},
            "");

        // Performance test
        System.out.println("\nPerformance Test:");
        String[] largeArray = new String[100];
        for (int i = 0; i < 100; i++) {
            largeArray[i] = "Test" + i + "Individual";
        }
        
        long startTime = System.currentTimeMillis();
        String result = findLongestCommonSubstring(largeArray);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Result length: " + result.length());
    }

    private static void runTest(String testName, String[] input, String expected) {
        System.out.println("\n" + testName + ":");
        String result = findLongestCommonSubstring(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Result: " + result);
        System.out.println("Expected: " + expected);
        System.out.println("Status: " + (result.equals(expected) ? "PASS" : "FAIL"));
    }
}

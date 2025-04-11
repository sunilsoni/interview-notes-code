package com.interview.notes.code.year.y2025.april.common.test4;

import java.util.*;

public class LongestSubstringFinder {
    public static void findAllLongestSubstrings(String input) {
        // Input validation
        if (input == null || input.isEmpty()) {
            System.out.println("No substrings found (empty input)");
            return;
        }

        // Set to store unique combinations
        Set<String> combinations = new HashSet<>();
        int maxLength = 0;
        String current = "";

        // First pass: Find maximum length and initial combinations
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int index = current.indexOf(c);
            
            // If character is found in current substring
            if (index != -1) {
                current = current.substring(index + 1);
            }
            current += c;
            
            // Update maxLength and combinations if necessary
            if (current.length() >= maxLength) {
                if (current.length() > maxLength) {
                    combinations.clear();
                    maxLength = current.length();
                }
                combinations.add(current);
            }
        }

        // Second pass: Find all possible combinations of maxLength
        for (int i = 0; i <= input.length() - maxLength; i++) {
            String substring = input.substring(i, i + maxLength);
            if (hasNoRepeatingChars(substring)) {
                combinations.add(substring);
            }
        }

        // Print results
        printResults(input, maxLength, combinations);
    }

    // Helper method to check if string has no repeating characters
    private static boolean hasNoRepeatingChars(String str) {
        Set<Character> seen = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (!seen.add(c)) return false;
        }
        return true;
    }

    // Helper method to print results
    private static void printResults(String input, int maxLength, Set<String> combinations) {
        System.out.println("Input: " + input);
        System.out.println("Length of longest substring: " + maxLength);
        System.out.println("All possible combinations:");
        combinations.forEach(System.out::println);
        System.out.println();
    }

    // Method to print separator line
    private static void printSeparator() {
        System.out.println("=".repeat(30));
    }

    // Test method
    private static void test(String input) {
        printSeparator();
        findAllLongestSubstrings(input);
    }

    public static void main(String[] args) {
        // Test cases
        test("abcabcbb");
        test("bbbbb");
        test("pwwkew");
        test("dvdf");
        test("abcde");
        test("");                  // Empty string
        test("a");                // Single character
        test("aab");              // Two characters
        test("abba");             // Palindrome
        test("abcdefghijklmn");   // Long string, all unique
        test("aaabbbccc");        // Groups of repeating characters
    }
}

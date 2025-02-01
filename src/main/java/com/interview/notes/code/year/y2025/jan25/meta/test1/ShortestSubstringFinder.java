package com.interview.notes.code.year.y2025.jan25.meta.test1;

import java.util.HashMap;
import java.util.Map;

/*

We have two SORTED arrays of integers: A and B. A has empty slots at the end of it. It has exactly as many empty slots as there are elements in B. Your goal is to merge the elements from B into A so that array A contains all of the elements in sorted order.

Optimize for speed and memory usage.

Input:

A = [1, 2, 3,_, _, _, _]
B = [2, 4, 6, 100]

Expected output:
A = [1, 2, 2, 3, 4, 6, 100]

 */
public class ShortestSubstringFinder {

    public static void main(String[] args) {
        // Test Case 1: Modified expected output
        String input1 = "aacbc";
        String alphabet1 = "abc";
        String expected1 = "acb"; // Assuming corrected output
        testShortestSubstring(input1, alphabet1, expected1, 1);

        // Test Case 2: Multiple occurrences
        String input2 = "abbcac";
        String alphabet2 = "abc";
        String expected2 = "bca";
        testShortestSubstring(input2, alphabet2, expected2, 2);

        // Test Case 3: Single character alphabet
        String input3 = "aaaaaa";
        String alphabet3 = "a";
        String expected3 = "a";
        testShortestSubstring(input3, alphabet3, expected3, 3);

        // Test Case 4: Alphabet letters not in input
        String input4 = "abcdef";
        String alphabet4 = "xyz";
        String expected4 = "";
        testShortestSubstring(input4, alphabet4, expected4, 4);

        // Test Case 5: Large input string
        String input5 = generateLargeInput(100000);
        String alphabet5 = "xyz";
        String expected5 = "xyz";
        testShortestSubstring(input5, alphabet5, expected5, 5);
    }

    public static String findShortestSubstring(String input, String alphabet) {
        if (input == null || alphabet == null || input.length() == 0 || alphabet.length() == 0) {
            return "";
        }

        // Map to store the required frequency of each character in the alphabet
        Map<Character, Integer> requiredChars = new HashMap<>();
        for (char c : alphabet.toCharArray()) {
            requiredChars.put(c, requiredChars.getOrDefault(c, 0) + 1);
        }
        int requiredCount = requiredChars.size();

        // Sliding window pointers and counts
        int left = 0, right = 0;
        int formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();

        // Result variables for the smallest window
        int minLength = Integer.MAX_VALUE;
        int minLeft = 0, minRight = 0;

        while (right < input.length()) {
            char c = input.charAt(right);
            if (requiredChars.containsKey(c)) {
                windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
                if (windowCounts.get(c).intValue() == requiredChars.get(c).intValue()) {
                    formed++;
                }
            }

            // Try to contract the window until it's no longer valid
            while (left <= right && formed == requiredCount) {
                c = input.charAt(left);

                // Update the result if this window is smaller
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                    minRight = right;
                }

                // Remove the leftmost character from window counts
                if (requiredChars.containsKey(c)) {
                    windowCounts.put(c, windowCounts.get(c) - 1);
                    if (windowCounts.get(c) < requiredChars.get(c)) {
                        formed--;
                    }
                }
                left++;
            }
            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : input.substring(minLeft, minRight + 1);
    }

    private static void testShortestSubstring(String input, String alphabet, String expected, int testCaseNumber) {
        String result = findShortestSubstring(input, alphabet);
        boolean passed = result.equals(expected);
        System.out.println("Test Case " + testCaseNumber + ": " + (passed ? "PASSED" : "FAILED"));
        if (!passed) {
            System.out.println("Input String: " + input);
            System.out.println("Alphabet: " + alphabet);
            System.out.println("Expected Output: \"" + expected + "\"");
            System.out.println("Actual Output:   \"" + result + "\"");
            System.out.println("-----------------------------------");
        }
    }

    private static String generateLargeInput(int size) {
        StringBuilder sb = new StringBuilder(size);
        // Generate a long string ending with 'x', 'y', 'z'
        for (int i = 0; i < size - 3; i++) {
            sb.append('a');
        }
        sb.append('x').append('y').append('z');
        return sb.toString();
    }
}
package com.interview.notes.code.year.y2023.june23.test5;

public class LongestSingleCharacterSubstring1 {
    public static int findLongestSubstring(String input) {
        int maxLength = 0;
        int currentLength = 1;
        char currentChar = input.charAt(0);

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == currentChar) {
                currentLength++;
                if (currentLength >= 3) {
                    maxLength = Math.max(maxLength, currentLength);
                }
            } else {
                currentChar = input.charAt(i);
                currentLength = 1;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String input = "aaaabaaa";
        // String input = "aabbcccddddd";
        int longestSubstringLength = findLongestSubstring(input);
        System.out.println("Length of the longest single character substring: " + longestSubstringLength);
    }
}

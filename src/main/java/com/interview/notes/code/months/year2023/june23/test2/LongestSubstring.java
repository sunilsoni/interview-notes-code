package com.interview.notes.code.months.year2023.june23.test2;

public class LongestSubstring {
    public static int findLongestSubstring(String input) {
        int maxSubstringLength = 0;  // Stores the length of the longest substring
        int currentLength = 1;      // Stores the length of the current substring
        char currentChar = input.charAt(0);  // Stores the current character being processed
        int count = 1;              // Stores the count of consecutive occurrences of the current character

        // Iterate over the input string starting from the second character
        for (int i = 1; i < input.length(); i++) {
            // Check if the current character is the same as the previous character
            if (input.charAt(i) == currentChar) {
                currentLength++;  // Increment the length of the current substring
                count++;         // Increment the count of consecutive occurrences
            } else {
                // Current character is different, check if the count is at least 3
                if (count >= 3) {
                    maxSubstringLength = Math.max(maxSubstringLength, currentLength);
                }

                // Reset the current character, length, and count
                currentChar = input.charAt(i);
                currentLength = 1;
                count = 1;
            }
        }

        // Check the last substring
        if (count >= 3) {
            maxSubstringLength = Math.max(maxSubstringLength, currentLength);
        }

        return maxSubstringLength;
    }

    public static void main(String[] args) {
        String[] testCases = {"aaaabaaa", "abbabbabb", "aaa"};

        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i];
            int longestSubstringLength = findLongestSubstring(input);
            System.out.println("Test case " + i + ": Input: " + input + " => Output: " + longestSubstringLength);
        }
    }
}

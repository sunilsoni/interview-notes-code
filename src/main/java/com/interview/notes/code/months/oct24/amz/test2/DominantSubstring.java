package com.interview.notes.code.months.oct24.amz.test2;

import java.util.HashMap;

public class DominantSubstring {

    // Function to calculate the number of dominant substrings
    public static long getDominantStringCount(String s) {
        int n = s.length();
        long dominantCount = 0;

        // Iterate over all start points
        for (int i = 0; i < n; i++) {
            // Frequency map to store character counts
            HashMap<Character, Integer> freqMap = new HashMap<>();
            
            // Iterate over even-length substrings starting from i
            for (int j = i; j < n; j++) {
                // Update frequency map for the current character
                char currentChar = s.charAt(j);
                freqMap.put(currentChar, freqMap.getOrDefault(currentChar, 0) + 1);
                
                // Check only for even-length substrings
                int length = j - i + 1;
                if (length % 2 == 0) {
                    // Check if there's a dominant character
                    if (hasDominantCharacter(freqMap, length)) {
                        dominantCount++;
                    }
                }
            }
        }
        
        return dominantCount;
    }

    // Helper function to check if there's a dominant character
    private static boolean hasDominantCharacter(HashMap<Character, Integer> freqMap, int length) {
        int halfLength = length / 2;
        for (int freq : freqMap.values()) {
            if (freq == halfLength) {
                return true;
            }
        }
        return false;
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // Test case 1
        String s1 = "aaaaid";
        long result1 = getDominantStringCount(s1);
        System.out.println(result1 == 3 ? "PASS" : "FAIL");

        // Test case 2
        String s2 = "aidfg";
        long result2 = getDominantStringCount(s2);
        System.out.println(result2 == 4 ? "PASS" : "FAIL");

        // Additional test case 3
        String s3 = "abcabc";
        long result3 = getDominantStringCount(s3);
        System.out.println(result3 == 0 ? "PASS" : "FAIL");

        // Additional test case 4
        String s4 = "aabbcc";
        long result4 = getDominantStringCount(s4);
        System.out.println(result4 == 3 ? "PASS" : "FAIL");
    }
}

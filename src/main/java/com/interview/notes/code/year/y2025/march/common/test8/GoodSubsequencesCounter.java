package com.interview.notes.code.year.y2025.march.common.test8;

import java.util.*;

public class GoodSubsequencesCounter {
    
    // The modulo value as specified in the problem
    private static final int MOD = 1_000_000_007;
    
    /**
     * Counts the number of good subsequences in a given word.
     * A good subsequence is one where the frequency of each character is the same.
     * 
     * @param word The input string consisting of lowercase Latin letters
     * @return The number of good subsequences modulo (10^9 + 7)
     */
    public static int countGoodSubsequences(String word) {
        // Count the frequency of each character in the word
        int[] charFrequency = new int[26]; // 26 lowercase letters
        for (char c : word.toCharArray()) {
            charFrequency[c - 'a']++;
        }
        
        // Calculate the maximum frequency of any character
        int maxFrequency = 0;
        for (int freq : charFrequency) {
            maxFrequency = Math.max(maxFrequency, freq);
        }
        
        // Calculate the total number of subsequences for each possible frequency
        long result = 0;
        
        // For each possible frequency from 1 to maxFrequency
        for (int freq = 1; freq <= maxFrequency; freq++) {
            // Start with 1 (representing the empty subsequence for this frequency)
            long subseqCount = 1;
            
            // For each character, calculate how many ways we can select it to appear exactly 'freq' times
            for (int i = 0; i < 26; i++) {
                if (charFrequency[i] >= freq) {
                    // Calculate nCr where n = charFrequency[i] and r = freq
                    long ways = calculateCombination(charFrequency[i], freq);
                    
                    // Multiply the current count by (ways + 1)
                    // +1 represents not including this character at all
                    subseqCount = (subseqCount * (ways + 1)) % MOD;
                }
            }
            
            // Subtract 1 to exclude the empty subsequence
            subseqCount = (subseqCount - 1 + MOD) % MOD;
            
            // Add to the total result
            result = (result + subseqCount) % MOD;
        }
        
        return (int) result;
    }
    
    /**
     * Calculates the combination C(n,r) - the number of ways to choose r elements from n elements.
     * 
     * @param n Total number of elements
     * @param r Number of elements to choose
     * @return The combination value C(n,r)
     */
    private static long calculateCombination(int n, int r) {
        // Optimization: choose the smaller of r and n-r
        r = Math.min(r, n - r);
        
        long result = 1;
        for (int i = 0; i < r; i++) {
            result = (result * (n - i)) / (i + 1);
        }
        
        return result;
    }
    
    /**
     * Main method to test the solution with provided examples and additional test cases.
     */
    public static void main(String[] args) {
        // Test cases from the problem statement
        testCase("abcd", 15, "Sample Case 0");
        testCase("baab", 11, "Sample Case 1");
        testCase("abca", 12, "Example Case");
        
        // Additional test cases with corrected expected values
        testCase("a", 1, "Single Character");
        testCase("aa", 3, "Two Same Characters"); // Corrected: "a", "a", "aa" are all good
        testCase("ab", 3, "Two Different Characters"); // "a", "b", "ab" are all good
        testCase("aaa", 7, "Three Same Characters"); // Corrected
        testCase("aaaa", 15, "Four Same Characters"); // Corrected
        
        // For "All Letters Once" test case, all 2^26-1 subsequences are good
        testCase("abcdefghijklmnopqrstuvwxyz", (1 << 26) - 1, "All Letters Once");
        
        // Large input test case
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append((char)('a' + (i % 26)));
        }
        long startTime = System.currentTimeMillis();
        int result = countGoodSubsequences(largeInput.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input (100,000 chars) - Result: " + result + 
                           ", Time: " + (endTime - startTime) + "ms");
    }
    
    /**
     * Helper method to run a test case and check if it passes or fails.
     * 
     * @param input The input string
     * @param expected The expected output
     * @param testName The name of the test case
     */
    private static void testCase(String input, int expected, String testName) {
        int result = countGoodSubsequences(input);
        boolean passed = result == expected;
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL") + 
                          " (Expected: " + expected + ", Got: " + result + ")");
    }
}
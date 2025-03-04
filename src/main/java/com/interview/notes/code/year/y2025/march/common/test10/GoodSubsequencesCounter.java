package com.interview.notes.code.year.y2025.march.common.test10;

import java.util.*;

public class GoodSubsequencesCounter {
    
    private static final int MOD = 1_000_000_007;
    
    public static int countGoodSubsequences(String word) {
        // Count frequency of each character
        int[] charFreq = new int[26];
        for(char c : word.toCharArray()){
            charFreq[c-'a']++;
        }
        
        // Find the maximum frequency
        int maxFreq = 0;
        for(int f : charFreq){
            maxFreq = Math.max(maxFreq, f);
        }
        
        long result = 0;
        
        // For each possible frequency
        for(int freq = 1; freq <= maxFreq; freq++){
            long subseqCount = 1;
            
            // For each character
            for(int i = 0; i < 26; i++){
                if(charFreq[i] >= freq){
                    // Calculate combination C(charFreq[i], freq)
                    long w = calculate(charFreq[i], freq);
                    // Multiply by (w+1) - the +1 is for not choosing this character
                    subseqCount = (subseqCount * (w + 1)) % MOD; 
                }
            }
            
            // Subtract 1 for the empty subsequence
            subseqCount = (subseqCount - 1 + MOD) % MOD;
            result = (result + subseqCount) % MOD;
        }
        return (int)result;
    }
    
    /**
     * Calculates the combination C(n,r) - the number of ways to choose r elements from n elements.
     * 
     * @param n Total number of elements
     * @param r Number of elements to choose
     * @return The combination value C(n,r)
     */
    private static long calculate(int n, int r){
        // Optimization: choose the smaller of r and n-r
        r = Math.min(r, n-r);
        
        long res = 1;
        for(int i = 0; i < r; i++){
            // Be careful with integer division - multiply first, then divide
            res = res * (n - i) / (i + 1);
        }
        return res;
    }
    
    /**
     * Main method to test the solution with provided examples and additional test cases.
     */
    public static void main(String[] args) {
        // Test cases from the problem statement
        testCase("abcd", 15, "Sample Case 0");
        testCase("baab", 11, "Sample Case 1");
        testCase("abca", 12, "Example Case");
        
        // Additional test cases
        testCase("a", 1, "Single Character");
        testCase("aa", 3, "Two Same Characters");
        testCase("ab", 3, "Two Different Characters");
        testCase("aaa", 7, "Three Same Characters");
        testCase("aaaa", 15, "Four Same Characters");
        
        // Large input test case
        StringBuilder largeInput = new StringBuilder();
        Random rand = new Random(42); // Fixed seed for reproducibility
        for (int i = 0; i < 10000; i++) {
            largeInput.append((char)('a' + rand.nextInt(26)));
        }
        long startTime = System.currentTimeMillis();
        int result = countGoodSubsequences(largeInput.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input (10,000 chars) - Result: " + result + 
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
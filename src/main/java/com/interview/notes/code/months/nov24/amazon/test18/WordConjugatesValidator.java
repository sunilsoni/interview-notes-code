package com.interview.notes.code.months.nov24.amazon.test18;

public class WordConjugatesValidator {
    
    public static void main(String[] args) {
        runTestCases();
        runLargeInputTest();
    }
    
    private static long countValidSubstrings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        
        int n = s.length();
        long count = 0;
        
        // Iterate through all possible substring lengths
        for (int len = 2; len <= n; len += 2) {  // Only even lengths can be valid
            // For each possible starting position
            for (int i = 0; i <= n - len; i++) {
                if (isValidSubstring(s, i, i + len)) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private static boolean isValidSubstring(String s, int start, int end) {
        int[] freq = new int[128];  // Count frequency of each character
        int aCount = 0, bCount = 0, cCount = 0, dCount = 0;
        
        // Count frequencies
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            freq[c]++;
            
            switch (c) {
                case 'a': aCount++; break;
                case 'b': bCount++; break;
                case 'c': cCount++; break;
                case 'd': dCount++; break;
            }
        }
        
        // Check for valid conjugate pairs
        boolean hasAB = aCount > 0 || bCount > 0;
        boolean hasCD = cCount > 0 || dCount > 0;
        
        // For a substring to be valid:
        // 1. If it has 'a' or 'b', it must have equal numbers of both
        // 2. If it has 'c' or 'd', it must have equal numbers of both
        boolean abBalanced = (!hasAB) || (aCount == bCount);
        boolean cdBalanced = (!hasCD) || (cCount == dCount);
        
        // At least one conjugate pair must be present
        boolean hasConjugatePair = (aCount == bCount && aCount > 0) || 
                                 (cCount == dCount && cCount > 0);
        
        return abBalanced && cdBalanced && hasConjugatePair;
    }
    
    private static void runTestCases() {
        // Original test cases
        testAndPrint("Test 1", "abdc", 3);
        testAndPrint("Test 2", "adcb", 2);
        testAndPrint("Test 3", "abcdad", 6);
        
        // Edge cases
        testAndPrint("Empty string", "", 0);
        testAndPrint("Single char", "a", 0);
        testAndPrint("Simple pair", "ab", 1);
        
        // Additional test cases
        testAndPrint("Test 4", "abab", 3);
        testAndPrint("Test 5", "cdcd", 3);
        testAndPrint("Test 6", "abcd", 3);
        testAndPrint("Test 7", "aaaa", 0);
    }
    
    private static void runLargeInputTest() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("abcd");
        }
        String largeInput = sb.toString();
        
        System.out.println("\nRunning large input test (length: " + largeInput.length() + ")...");
        long startTime = System.currentTimeMillis();
        long result = countValidSubstrings(largeInput);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Large input test completed in " + (endTime - startTime) + "ms");
        System.out.println("Valid substrings found: " + result);
    }
    
    private static void testAndPrint(String testName, String input, long expected) {
        long result = countValidSubstrings(input);
        boolean passed = result == expected;
        
        System.out.println(testName + ":");
        System.out.println("Input: \"" + input + "\"");
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
}
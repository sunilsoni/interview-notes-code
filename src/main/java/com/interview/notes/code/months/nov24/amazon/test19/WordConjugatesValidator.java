package com.interview.notes.code.months.nov24.amazon.test19;

public class WordConjugatesValidator {
    
    public static void main(String[] args) {
        runTestCases();
        runLargeInputTest();
    }
    
    private static long countValidSubstrings(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        
        int n = s.length();
        long count = 0;
        
        // Check all possible substrings
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Each valid substring must have at least one conjugate pair
                if (isValidSubstring(s, i, j + 1)) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private static boolean isValidSubstring(String s, int start, int end) {
        int len = end - start;
        if (len < 2) return false;
        
        // Count characters
        int[] count = new int[4]; // [a,b,c,d]
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'a': count[0]++; break;
                case 'b': count[1]++; break;
                case 'c': count[2]++; break;
                case 'd': count[3]++; break;
            }
        }
        
        // Check if we have valid conjugate pairs
        boolean hasABPair = false;
        boolean hasCDPair = false;
        
        // Check AB pairs
        if (count[0] > 0 || count[1] > 0) {
            if (count[0] != count[1]) return false;
            hasABPair = true;
        }
        
        // Check CD pairs
        if (count[2] > 0 || count[3] > 0) {
            if (count[2] != count[3]) return false;
            hasCDPair = true;
        }
        
        // Must have at least one valid conjugate pair
        return hasABPair || hasCDPair;
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
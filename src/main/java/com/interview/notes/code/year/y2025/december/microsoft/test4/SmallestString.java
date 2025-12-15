package com.interview.notes.code.year.y2025.december.microsoft.test4;

public class SmallestString {

    /**
     * Optimized solution that avoids creating extra string sequences.
     * Uses a single char array and fills result directly.
     * 
     * Logic: Count what we need, then fill result array in correct order.
     */
    public static String smallestString(String s) {
        
        // Convert input to char array - this is our working copy
        // We need this because Java strings are immutable
        char[] chars = s.toCharArray();
        
        // Get the length once to avoid repeated calls
        int n = chars.length;
        
        // Variables to track counts and position
        int bCount = 0;           // Total number of 'b's in string
        int aBeforeFirstC = 0;    // Count of 'a's before the first 'c'
        int firstCIndex = -1;     // Position of first 'c' (-1 means no 'c' found)
        
        // Single pass through input to gather all needed info
        // This avoids multiple iterations over the string
        for (int i = 0; i < n; i++) {
            
            // Get current character
            char ch = chars[i];
            
            if (ch == 'b') {
                // Count every 'b' we find
                // All b's will be grouped together in result
                bCount++;
            } 
            else if (ch == 'c') {
                // Check if this is the first 'c' we encounter
                // We only care about the first one for positioning
                if (firstCIndex == -1) {
                    firstCIndex = i;    // Mark position of first 'c'
                }
            } 
            else if (ch == 'a') {
                // Count 'a's only if we haven't seen a 'c' yet
                // These 'a's can move to the front
                if (firstCIndex == -1) {
                    aBeforeFirstC++;
                }
            }
        }
        
        // Create result array - same size as input
        // This is the only extra space we need
        char[] result = new char[n];
        
        // Index to track where we are filling in result
        int idx = 0;
        
        // Step 1: Fill all 'a's that were before first 'c'
        // These go at the very beginning for smallest result
        for (int i = 0; i < aBeforeFirstC; i++) {
            result[idx] = 'a';      // Place 'a' at current position
            idx++;                   // Move to next position
        }
        
        // Step 2: Fill all 'b's right after the leading 'a's
        // b's can move freely, so we group them here
        for (int i = 0; i < bCount; i++) {
            result[idx] = 'b';      // Place 'b' at current position
            idx++;                   // Move to next position
        }
        
        // Step 3: Fill remaining 'a's and 'c's from first 'c' onwards
        // These maintain their relative order (can't swap a-c directly)
        if (firstCIndex != -1) {
            // Start from first 'c' position in original array
            for (int i = firstCIndex; i < n; i++) {
                char ch = chars[i];
                // Only copy 'a' and 'c', skip 'b' (already placed)
                if (ch == 'a' || ch == 'c') {
                    result[idx] = ch;   // Copy character to result
                    idx++;               // Move to next position
                }
            }
        }
        
        // Convert char array back to string and return
        return new String(result);
    }
    
    /**
     * Helper method to run a single test case
     */
    public static boolean runTest(String testName, String input, String expected) {
        
        // Get actual result from our method
        String actual = smallestString(input);
        
        // Check if test passed
        boolean passed = actual.equals(expected);
        
        // Print results (truncate long strings for readability)
        System.out.println("----------------------------------------");
        System.out.println("Test: " + testName);
        
        // Show truncated version for long inputs
        if (input.length() > 40) {
            System.out.println("Input length: " + input.length());
        } else {
            System.out.println("Input: " + input);
        }
        
        if (expected.length() > 40) {
            System.out.println("Expected length: " + expected.length());
        } else {
            System.out.println("Expected: " + expected);
        }
        
        if (actual.length() > 40) {
            System.out.println("Actual length: " + actual.length());
        } else {
            System.out.println("Actual: " + actual);
        }
        
        System.out.println("Result: " + (passed ? "PASS ✓" : "FAIL ✗"));
        
        return passed;
    }
    
    /**
     * Main method with all test cases
     */
    public static void main(String[] args) {
        
        System.out.println("========================================");
        System.out.println("   SMALLEST STRING - TEST RESULTS");
        System.out.println("========================================\n");
        
        int passed = 0;    // Count of passed tests
        int total = 0;     // Count of total tests
        
        // ===== PROVIDED TEST CASES =====
        
        // Sample Case 0
        total++;
        if (runTest("Sample Case 0", "baacba", "aabbca")) passed++;
        
        // Sample Case 1
        total++;
        if (runTest("Sample Case 1", "ababbaab", "aaaabbbb")) passed++;
        
        // Example from problem
        total++;
        if (runTest("Example", "abaacbac", "aaabbcac")) passed++;
        
        // ===== EDGE CASES =====
        
        // Single characters
        total++;
        if (runTest("Single a", "a", "a")) passed++;
        
        total++;
        if (runTest("Single b", "b", "b")) passed++;
        
        total++;
        if (runTest("Single c", "c", "c")) passed++;
        
        // Only one type of character
        total++;
        if (runTest("All a's", "aaaa", "aaaa")) passed++;
        
        total++;
        if (runTest("All b's", "bbbb", "bbbb")) passed++;
        
        total++;
        if (runTest("All c's", "cccc", "cccc")) passed++;
        
        // Two character types only
        total++;
        if (runTest("Only a and b", "baba", "aabb")) passed++;
        
        total++;
        if (runTest("Only b and c", "cbcb", "bbcc")) passed++;
        
        total++;
        if (runTest("Only a and c", "caca", "caca")) passed++;
        
        // c blocks a's from moving
        total++;
        if (runTest("c at start", "cab", "cab")) passed++;
        
        total++;
        if (runTest("c blocks a", "cba", "bca")) passed++;
        
        // Already optimal
        total++;
        if (runTest("Already sorted", "aabbcc", "aabbcc")) passed++;
        
        // ===== LARGE DATA TESTS =====
        
        // Large test 1: 10000 chars - b's then a's
        StringBuilder large1In = new StringBuilder();
        StringBuilder large1Out = new StringBuilder();
        for (int i = 0; i < 5000; i++) large1In.append('b');
        for (int i = 0; i < 5000; i++) large1In.append('a');
        for (int i = 0; i < 5000; i++) large1Out.append('a');
        for (int i = 0; i < 5000; i++) large1Out.append('b');
        
        long start1 = System.currentTimeMillis();
        total++;
        if (runTest("Large 1 (10K chars)", large1In.toString(), large1Out.toString())) passed++;
        System.out.println("Time: " + (System.currentTimeMillis() - start1) + " ms\n");
        
        // Large test 2: 100000 chars - alternating ab
        StringBuilder large2In = new StringBuilder();
        StringBuilder large2Out = new StringBuilder();
        for (int i = 0; i < 50000; i++) large2In.append("ab");
        for (int i = 0; i < 50000; i++) large2Out.append('a');
        for (int i = 0; i < 50000; i++) large2Out.append('b');
        
        long start2 = System.currentTimeMillis();
        total++;
        if (runTest("Large 2 (100K chars)", large2In.toString(), large2Out.toString())) passed++;
        System.out.println("Time: " + (System.currentTimeMillis() - start2) + " ms\n");
        
        // Large test 3: c's blocking a's
        StringBuilder large3In = new StringBuilder();
        StringBuilder large3Out = new StringBuilder();
        for (int i = 0; i < 10000; i++) large3In.append('c');
        for (int i = 0; i < 10000; i++) large3In.append('b');
        for (int i = 0; i < 10000; i++) large3In.append('a');
        for (int i = 0; i < 10000; i++) large3Out.append('b');
        for (int i = 0; i < 10000; i++) large3Out.append('c');
        for (int i = 0; i < 10000; i++) large3Out.append('a');
        
        long start3 = System.currentTimeMillis();
        total++;
        if (runTest("Large 3 (30K, c blocks)", large3In.toString(), large3Out.toString())) passed++;
        System.out.println("Time: " + (System.currentTimeMillis() - start3) + " ms\n");
        
        // ===== SUMMARY =====
        
        System.out.println("========================================");
        System.out.println("         FINAL SUMMARY");
        System.out.println("========================================");
        System.out.println("Passed: " + passed + "/" + total);
        System.out.println("Status: " + (passed == total ? "ALL PASS ✓" : "SOME FAILED ✗"));
        System.out.println("========================================");
    }
}
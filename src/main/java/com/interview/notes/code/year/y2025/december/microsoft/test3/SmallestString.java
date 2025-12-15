package com.interview.notes.code.year.y2025.december.microsoft.test3;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SmallestString {

    /**
     * This method finds the alphabetically smallest string possible
     * by swapping adjacent 'a'-'b' or 'b'-'c' pairs.
     * 
     * Key insight: 'a' and 'c' cannot swap directly, so their 
     * relative order is fixed. 'b' can move anywhere freely.
     */
    public static String smallestString(String s) {
        
        // Count how many 'b' characters are in the string
        // We use stream to filter and count all 'b's
        // This is needed because 'b's can be placed anywhere later
        long bCount = s.chars()                          // Convert string to IntStream of char codes
                       .filter(ch -> ch == 'b')          // Keep only 'b' characters
                       .count();                          // Count total 'b's
        
        // Extract only 'a' and 'c' characters while keeping their order
        // This is crucial because 'a' and 'c' cannot swap with each other
        // Their relative positions are fixed throughout any operations
        String acSequence = s.chars()                    // Convert string to IntStream
                             .filter(ch -> ch == 'a' || ch == 'c')  // Keep only 'a' and 'c'
                             .mapToObj(ch -> String.valueOf((char) ch))  // Convert each to String
                             .collect(Collectors.joining());  // Join all into one string
        
        // Find position of first 'c' in the a-c sequence
        // This helps us know where to insert all the 'b's
        // We want 'b's right before first 'c' for smallest result
        int firstCIndex = acSequence.indexOf('c');       // Get index of first 'c'
        
        // Build the string of all 'b's that we need to insert
        // Using IntStream to generate 'b' characters bCount times
        String allBs = IntStream.range(0, (int) bCount)  // Create range from 0 to bCount
                                .mapToObj(i -> "b")       // Map each to string "b"
                                .collect(Collectors.joining());  // Join to get "bbb..."
        
        // Build the final result string
        String result;
        
        if (firstCIndex == -1) {
            // Case 1: No 'c' exists in the string
            // All 'a's come first, then all 'b's at the end
            // This gives us the smallest possible arrangement
            result = acSequence + allBs;                 // Simply append b's at end
        } else {
            // Case 2: There is at least one 'c' in the string
            // We split the a-c sequence at first 'c' position
            // Insert all 'b's right before the first 'c'
            // This ensures smallest lexicographic order
            String beforeFirstC = acSequence.substring(0, firstCIndex);  // Get all chars before first 'c'
            String fromFirstC = acSequence.substring(firstCIndex);        // Get first 'c' and everything after
            result = beforeFirstC + allBs + fromFirstC;  // Combine: a's + b's + c's and remaining a's
        }
        
        // Return the smallest possible string
        return result;
    }
    
    /**
     * Helper method to run a single test case
     * Compares actual output with expected output
     * Prints PASS or FAIL with details
     */
    public static void runTest(String testName, String input, String expected) {
        
        // Call our solution method with the input
        String actual = smallestString(input);           // Get the actual result
        
        // Compare actual result with expected result
        boolean passed = actual.equals(expected);        // Check if they match
        
        // Print the test result in a readable format
        System.out.println("----------------------------------------");
        System.out.println("Test: " + testName);         // Print test name
        System.out.println("Input: " + (input.length() > 50 ? input.substring(0, 50) + "..." : input));  // Print input (truncate if long)
        System.out.println("Expected: " + (expected.length() > 50 ? expected.substring(0, 50) + "..." : expected));  // Print expected
        System.out.println("Actual: " + (actual.length() > 50 ? actual.substring(0, 50) + "..." : actual));  // Print actual
        System.out.println("Result: " + (passed ? "PASS ✓" : "FAIL ✗"));  // Print PASS or FAIL
        System.out.println();
    }
    
    /**
     * Main method to run all test cases
     * Includes provided test cases and additional edge cases
     */
    public static void main(String[] args) {
        
        System.out.println("========================================");
        System.out.println("   SMALLEST STRING - TEST RESULTS");
        System.out.println("========================================\n");
        
        // Track total tests and passed tests
        int totalTests = 0;                              // Counter for total tests
        int passedTests = 0;                             // Counter for passed tests
        
        // ========== PROVIDED TEST CASES ==========
        
        // Test Case 0: From problem statement
        // "baacba" -> swap c with b, then move b's left
        runTest("Sample Case 0", "baacba", "aabbca");
        totalTests++;
        if (smallestString("baacba").equals("aabbca")) passedTests++;
        
        // Test Case 1: From problem statement
        // "ababbaab" -> no c's, so sort a's before b's
        runTest("Sample Case 1", "ababbaab", "aaaabbbb");
        totalTests++;
        if (smallestString("ababbaab").equals("aaaabbbb")) passedTests++;
        
        // Test Case from example: "abaacbac"
        // Explanation shows result is "aaabbcac"
        runTest("Example Case", "abaacbac", "aaabbcac");
        totalTests++;
        if (smallestString("abaacbac").equals("aaabbcac")) passedTests++;
        
        // ========== EDGE CASES ==========
        
        // Edge Case 1: Single character 'a'
        runTest("Single char a", "a", "a");
        totalTests++;
        if (smallestString("a").equals("a")) passedTests++;
        
        // Edge Case 2: Single character 'b'
        runTest("Single char b", "b", "b");
        totalTests++;
        if (smallestString("b").equals("b")) passedTests++;
        
        // Edge Case 3: Single character 'c'
        runTest("Single char c", "c", "c");
        totalTests++;
        if (smallestString("c").equals("c")) passedTests++;
        
        // Edge Case 4: Only 'a's
        runTest("Only a's", "aaaa", "aaaa");
        totalTests++;
        if (smallestString("aaaa").equals("aaaa")) passedTests++;
        
        // Edge Case 5: Only 'b's
        runTest("Only b's", "bbbb", "bbbb");
        totalTests++;
        if (smallestString("bbbb").equals("bbbb")) passedTests++;
        
        // Edge Case 6: Only 'c's
        runTest("Only c's", "cccc", "cccc");
        totalTests++;
        if (smallestString("cccc").equals("cccc")) passedTests++;
        
        // Edge Case 7: 'c' at the beginning - a's can't move past c
        runTest("c at start", "cab", "cab");
        totalTests++;
        if (smallestString("cab").equals("cab")) passedTests++;
        
        // Edge Case 8: Already sorted
        runTest("Already sorted", "aabbcc", "aabbcc");
        totalTests++;
        if (smallestString("aabbcc").equals("aabbcc")) passedTests++;
        
        // Edge Case 9: Reverse order without c
        runTest("Reverse no c", "bbaa", "aabb");
        totalTests++;
        if (smallestString("bbaa").equals("aabb")) passedTests++;
        
        // Edge Case 10: Only a and c (no b)
        // a and c can't swap, so order is fixed
        runTest("Only a and c", "caca", "caca");
        totalTests++;
        if (smallestString("caca").equals("caca")) passedTests++;
        
        // Edge Case 11: Only b and c
        runTest("Only b and c", "cbcb", "bbcc");
        totalTests++;
        if (smallestString("cbcb").equals("bbcc")) passedTests++;
        
        // Edge Case 12: Complex mix
        runTest("Complex mix", "cbabc", "cabbbc");
        totalTests++;
        if (smallestString("cbabc").equals("cabbbc")) passedTests++;
        
        // ========== LARGE DATA TEST CASES ==========
        
        // Large Test 1: 10000 characters - all b's then all a's
        StringBuilder largeInput1 = new StringBuilder();
        StringBuilder largeExpected1 = new StringBuilder();
        for (int i = 0; i < 5000; i++) {                 // Add 5000 b's
            largeInput1.append("b");
        }
        for (int i = 0; i < 5000; i++) {                 // Add 5000 a's
            largeInput1.append("a");
        }
        for (int i = 0; i < 5000; i++) {                 // Expected: 5000 a's first
            largeExpected1.append("a");
        }
        for (int i = 0; i < 5000; i++) {                 // Then 5000 b's
            largeExpected1.append("b");
        }
        long startTime1 = System.currentTimeMillis();    // Start timer
        String result1 = smallestString(largeInput1.toString());
        long endTime1 = System.currentTimeMillis();      // End timer
        runTest("Large Test 1 (10000 chars, only a and b)", 
                largeInput1.toString(), largeExpected1.toString());
        totalTests++;
        if (result1.contentEquals(largeExpected1)) passedTests++;
        System.out.println("Time taken: " + (endTime1 - startTime1) + " ms\n");
        
        // Large Test 2: 50000 characters with all three chars
        StringBuilder largeInput2 = new StringBuilder();
        for (int i = 0; i < 16666; i++) {                // Add c's first
            largeInput2.append("c");
        }
        for (int i = 0; i < 16667; i++) {                // Add b's
            largeInput2.append("b");
        }
        for (int i = 0; i < 16667; i++) {                // Add a's
            largeInput2.append("a");
        }
        // Expected: b's go before c's (a's can't pass c's)
        StringBuilder largeExpected2 = new StringBuilder();
        for (int i = 0; i < 16667; i++) {                // b's first (before c's)
            largeExpected2.append("b");
        }
        for (int i = 0; i < 16666; i++) {                // Then c's
            largeExpected2.append("c");
        }
        for (int i = 0; i < 16667; i++) {                // Then a's (stuck after c's)
            largeExpected2.append("a");
        }
        long startTime2 = System.currentTimeMillis();    // Start timer
        String result2 = smallestString(largeInput2.toString());
        long endTime2 = System.currentTimeMillis();      // End timer
        runTest("Large Test 2 (50000 chars, c's block a's)", 
                largeInput2.toString(), largeExpected2.toString());
        totalTests++;
        if (result2.contentEquals(largeExpected2)) passedTests++;
        System.out.println("Time taken: " + (endTime2 - startTime2) + " ms\n");
        
        // Large Test 3: 100000 characters - maximum constraint
        StringBuilder largeInput3 = new StringBuilder();
        StringBuilder largeExpected3 = new StringBuilder();
        // Pattern: ababab... (no c)
        for (int i = 0; i < 50000; i++) {                // Alternating a and b
            largeInput3.append("ab");
        }
        for (int i = 0; i < 50000; i++) {                // Expected: all a's first
            largeExpected3.append("a");
        }
        for (int i = 0; i < 50000; i++) {                // Then all b's
            largeExpected3.append("b");
        }
        long startTime3 = System.currentTimeMillis();    // Start timer
        String result3 = smallestString(largeInput3.toString());
        long endTime3 = System.currentTimeMillis();      // End timer
        runTest("Large Test 3 (100000 chars, alternating ab)", 
                largeInput3.toString(), largeExpected3.toString());
        totalTests++;
        if (result3.contentEquals(largeExpected3)) passedTests++;
        System.out.println("Time taken: " + (endTime3 - startTime3) + " ms\n");
        
        // ========== FINAL SUMMARY ==========
        
        System.out.println("========================================");
        System.out.println("           FINAL SUMMARY");
        System.out.println("========================================");
        System.out.println("Total Tests: " + totalTests);        // Print total
        System.out.println("Passed: " + passedTests);            // Print passed count
        System.out.println("Failed: " + (totalTests - passedTests));  // Print failed count
        System.out.println("Success Rate: " + (passedTests * 100 / totalTests) + "%");  // Print percentage
        System.out.println("========================================");
        
        // Final verdict
        if (passedTests == totalTests) {
            System.out.println("\n*** ALL TESTS PASSED! ***");
        } else {
            System.out.println("\n*** SOME TESTS FAILED! ***");
        }
    }
}
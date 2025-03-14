package com.interview.notes.code.year.y2025.march.common.test14;

import java.util.HashSet;

public class Solution {

    /**
     * Given a string and size of the tuples, extracts all unique tuples(substrings) of the given size.
     * 
     * @param input The input string to extract tuples from
     * @param len The length of each tuple
     * @return A HashSet containing all unique tuples of the specified length
     */
    public static HashSet<String> uniqueTuples(String input, int len) {
        // Create a new HashSet to store unique tuples
        HashSet<String> result = new HashSet<String>();
        
        // Check for invalid inputs
        if (input == null || len <= 0 || len > input.length()) {
            return result; // Return empty set for invalid inputs
        }
        
        // Iterate through the input string to extract all possible tuples of length 'len'
        for (int i = 0; i <= input.length() - len; i++) {
            // Extract the substring from current position with the specified length
            String tuple = input.substring(i, i + len);
            // Add the tuple to the HashSet (duplicates will be automatically ignored)
            result.add(tuple);
        }
        
        return result;
    }

    /**
     * boolean doTestsPass()
     * Returns true if the tests pass. Otherwise, returns false.
     */
    public static boolean doTestsPass() {
        boolean allTestsPassed = true;
        
        // Test 1: Basic test with "aab" and length 2
        String input1 = "aab";
        HashSet<String> result1 = uniqueTuples(input1, 2);
        if (result1.size() == 2 && result1.contains("aa") && result1.contains("ab")) {
            System.out.println("✓ Test 1 passed");
        } else {
            System.out.println("✗ Test 1 failed");
            allTestsPassed = false;
        }
        
        // Test 2: String with repeated characters
        String input2 = "aaaa";
        HashSet<String> result2 = uniqueTuples(input2, 2);
        if (result2.size() == 1 && result2.contains("aa")) {
            System.out.println("✓ Test 2 passed");
        } else {
            System.out.println("✗ Test 2 failed");
            allTestsPassed = false;
        }
        
        // Test 3: Longer string with more variety
        String input3 = "abcdefg";
        HashSet<String> result3 = uniqueTuples(input3, 3);
        if (result3.size() == 5 && 
            result3.contains("abc") && 
            result3.contains("bcd") && 
            result3.contains("cde") && 
            result3.contains("def") && 
            result3.contains("efg")) {
            System.out.println("✓ Test 3 passed");
        } else {
            System.out.println("✗ Test 3 failed");
            allTestsPassed = false;
        }
        
        // Test 4: Edge case - tuple length equals string length
        String input4 = "xyz";
        HashSet<String> result4 = uniqueTuples(input4, 3);
        if (result4.size() == 1 && result4.contains("xyz")) {
            System.out.println("✓ Test 4 passed");
        } else {
            System.out.println("✗ Test 4 failed");
            allTestsPassed = false;
        }
        
        // Test 5: Edge case - tuple length greater than string length
        String input5 = "ab";
        HashSet<String> result5 = uniqueTuples(input5, 3);
        if (result5.size() == 0) {
            System.out.println("✓ Test 5 passed");
        } else {
            System.out.println("✗ Test 5 failed");
            allTestsPassed = false;
        }
        
        // Test 6: Edge case - null input
        HashSet<String> result6 = uniqueTuples(null, 2);
        if (result6.size() == 0) {
            System.out.println("✓ Test 6 passed");
        } else {
            System.out.println("✗ Test 6 failed");
            allTestsPassed = false;
        }
        
        // Test 7: Edge case - invalid tuple length
        String input7 = "test";
        HashSet<String> result7 = uniqueTuples(input7, 0);
        if (result7.size() == 0) {
            System.out.println("✓ Test 7 passed");
        } else {
            System.out.println("✗ Test 7 failed");
            allTestsPassed = false;
        }
        
        // Test 8: Large input
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeInput.append((char)('a' + i % 26));
        }
        long startTime = System.currentTimeMillis();
        HashSet<String> result8 = uniqueTuples(largeInput.toString(), 5);
        long endTime = System.currentTimeMillis();
        if (result8.size() > 0) {
            System.out.println("✓ Test 8 passed (Processed " + largeInput.length() + 
                              " characters in " + (endTime - startTime) + "ms)");
        } else {
            System.out.println("✗ Test 8 failed");
            allTestsPassed = false;
        }
        
        return allTestsPassed;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}

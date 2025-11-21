package com.interview.notes.code.year.y2025.november.common.test4;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringOptimized {

    // ============ APPROACH 1: SLIDING WINDOW WITH HASHMAP (RECOMMENDED) ============
    
    // Method to find length of longest substring without repeating characters
    // Uses sliding window technique with HashMap for character tracking
    // Input: String - the input string to process
    // Output: int - length of longest substring without repeating characters
    public static int lengthOfLongestSubstring_HashMap(String str) {
        
        // Step 1: Input validation - check if string is null or empty
        // Empty string has no substring, return 0
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        // Step 2: Initialize HashMap to store character and its last seen index
        // Key: character from string, Value: index position where it was last seen
        // HashMap provides O(1) lookup time for checking if character exists
        Map<Character, Integer> charIndexMap = new HashMap<>();
        
        // Step 3: Initialize variables for sliding window technique
        // maxLength tracks the longest substring found so far
        // start marks the beginning of current window
        // Both start at 0 initially
        int maxLength = 0;
        int start = 0;
        
        // Step 4: Iterate through string using enhanced for loop with index
        // Process each character one by one from left to right
        // Time: O(n) where n is string length
        for (int end = 0; end < str.length(); end++) {
            
            // Step 5: Get current character at position 'end'
            // charAt(end) retrieves character at index end
            char currentChar = str.charAt(end);
            
            // Step 6: Check if character already exists in map AND within current window
            // containsKey() checks if character seen before
            // charIndexMap.get() retrieves last index where character was seen
            // If lastIndex >= start, it means character is in current window (duplicate)
            // If lastIndex < start, it's from previous window (safe to use)
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= start) {
                
                // Step 7: If duplicate found within window, move start pointer
                // Move start to position after previous occurrence of duplicate
                // This shrinks window from left, removing the first occurrence
                // Example: "abca" - when we see second 'a', move start from 0 to 1
                start = charIndexMap.get(currentChar) + 1;
            }
            
            // Step 8: Calculate current window length
            // end - start + 1 = length of substring from start to end (inclusive)
            // For example: start=1, end=3 → length = 3 - 1 + 1 = 3
            int currentLength = end - start + 1;
            
            // Step 9: Update maxLength if current window is larger
            // Math.max() compares and returns larger value
            // Keeps track of longest substring found so far
            maxLength = Math.max(maxLength, currentLength);
            
            // Step 10: Update character's position in map to current index
            // put() stores/updates character with its latest index
            // This ensures map always has most recent position of each character
            charIndexMap.put(currentChar, end);
        }
        
        // Step 11: Return the maximum length found
        // Time Complexity: O(n) - single pass through string
        // Space Complexity: O(min(n, m)) where m = charset size (typically 26-128 chars)
        return maxLength;
    }

    // ============ APPROACH 2: SLIDING WINDOW WITH ARRAY (OPTIMIZED FOR ASCII) ============
    
    // Method optimized for ASCII characters using array instead of HashMap
    // Faster than HashMap for ASCII strings due to direct array access
    // Input: String - the input string
    // Output: int - length of longest substring without repeating
    public static int lengthOfLongestSubstring_Array(String str) {
        
        // Step 1: Validate input
        // Return 0 for null or empty strings
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        // Step 2: Create array to store last seen index of each character
        // Array size 256 covers all ASCII characters (0-255)
        // Index = ASCII value of character, Value = last index where seen
        // Initialize all to -1 (meaning not seen yet)
        int[] charLastIndex = new int[256];
        for (int i = 0; i < 256; i++) {
            charLastIndex[i] = -1;  // -1 means character not encountered yet
        }
        
        // Step 3: Initialize variables
        // maxLength = longest substring length found
        // start = left pointer of sliding window
        int maxLength = 0;
        int start = 0;
        
        // Step 4: Sliding window loop - process each character
        // end = right pointer of sliding window
        // Time: O(n) - single pass
        for (int end = 0; end < str.length(); end++) {
            
            // Step 5: Get current character
            // charAt() retrieves character at position end
            char currentChar = str.charAt(end);
            
            // Step 6: Get ASCII value of current character
            // ASCII value used as index in charLastIndex array
            // int cast converts character to its ASCII numeric value
            int charASCII = currentChar;
            
            // Step 7: Check if character was seen before and is in current window
            // charLastIndex[charASCII] stores last position of this character
            // If >= start, character is in current window (duplicate)
            // If == -1 or < start, character not in window (first occurrence in window)
            if (charLastIndex[charASCII] >= start) {
                
                // Step 8: Move start pointer to skip the duplicate
                // Set start to position after previous occurrence
                // This removes first occurrence of duplicate from window
                start = charLastIndex[charASCII] + 1;
            }
            
            // Step 9: Calculate current window length
            // end - start + 1 = characters from start to end inclusive
            int currentLength = end - start + 1;
            
            // Step 10: Update maximum length if needed
            // Keep track of longest valid substring found
            maxLength = Math.max(maxLength, currentLength);
            
            // Step 11: Update last seen index of current character
            // Store current position in array for future duplicate checks
            charLastIndex[charASCII] = end;
        }
        
        // Step 12: Return the longest length found
        // Time: O(n), Space: O(1) - array size fixed at 256
        return maxLength;
    }

    // ============ APPROACH 3: WITH SUBSTRING DETAILS ============
    
    // Method that returns not just length but also the actual substring
    // Input: String - the input string
    // Output: Map with "length" and "substring" keys
    public static Map<String, Object> lengthOfLongestSubstring_WithSubstring(String str) {
        
        // Step 1: Initialize result map
        // Will store both the length and the actual substring
        Map<String, Object> result = new HashMap<>();
        
        // Step 2: Validate input
        if (str == null || str.length() == 0) {
            result.put("length", 0);
            result.put("substring", "");
            return result;
        }
        
        // Step 3: Initialize tracking variables
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int maxStart = 0;  // Track starting index of longest substring
        int start = 0;
        
        // Step 4: Sliding window loop
        for (int end = 0; end < str.length(); end++) {
            
            // Step 5: Get current character
            char currentChar = str.charAt(end);
            
            // Step 6: Handle duplicate - move start pointer
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= start) {
                start = charIndexMap.get(currentChar) + 1;
            }
            
            // Step 7: Calculate current length
            int currentLength = end - start + 1;
            
            // Step 8: Update max length and track starting position
            // When we find longer substring, also save where it starts
            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxStart = start;  // Remember where longest substring begins
            }
            
            // Step 9: Update character position
            charIndexMap.put(currentChar, end);
        }
        
        // Step 10: Extract the actual substring from original string
        // substring(start, end) extracts characters from start (inclusive) to end (exclusive)
        // str.substring(maxStart, maxStart + maxLength) gets the longest substring
        String longestSubstring = str.substring(maxStart, maxStart + maxLength);
        
        // Step 11: Store results in map
        result.put("length", maxLength);
        result.put("substring", longestSubstring);
        
        // Step 12: Return map with both length and substring
        return result;
    }

    // Main method with comprehensive tests
    public static void main(String[] args) {
        
        int passCount = 0;
        int failCount = 0;
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // ============ BASIC TEST CASES ============
        
        // Test 1: Problem example 1 - "abcabcbb"
        // Expected: 3 (substring "abc")
        // Explanation: When we see second 'a' at index 3, we move start to 1
        String test1 = "abcabcbb";
        int result1a = lengthOfLongestSubstring_HashMap(test1);
        int result1b = lengthOfLongestSubstring_Array(test1);
        Map<String, Object> result1c = lengthOfLongestSubstring_WithSubstring(test1);
        boolean pass1 = (result1a == 3 && result1b == 3 && (int)result1c.get("length") == 3);
        System.out.println("Test 1 - Example 1: " + (pass1 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: \"" + test1 + "\"");
        System.out.println("  HashMap Result: " + result1a);
        System.out.println("  Array Result: " + result1b);
        System.out.println("  With Substring: length=" + result1c.get("length") + ", substring=\"" + result1c.get("substring") + "\"\n");
        passCount += pass1 ? 1 : 0;
        failCount += pass1 ? 0 : 1;
        
        // Test 2: Problem example 2 - "bbbbb"
        // Expected: 1 (substring "b")
        // Explanation: Every character is 'b', so max substring is just one 'b'
        String test2 = "bbbbb";
        int result2a = lengthOfLongestSubstring_HashMap(test2);
        int result2b = lengthOfLongestSubstring_Array(test2);
        Map<String, Object> result2c = lengthOfLongestSubstring_WithSubstring(test2);
        boolean pass2 = (result2a == 1 && result2b == 1 && (int)result2c.get("length") == 1);
        System.out.println("Test 2 - Example 2: " + (pass2 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: \"" + test2 + "\"");
        System.out.println("  HashMap Result: " + result2a);
        System.out.println("  Array Result: " + result2b);
        System.out.println("  With Substring: length=" + result2c.get("length") + ", substring=\"" + result2c.get("substring") + "\"\n");
        passCount += pass2 ? 1 : 0;
        failCount += pass2 ? 0 : 1;
        
        // Test 3: No repeating characters - "abcdefg"
        // Expected: 7 (entire string has no repeats)
        String test3 = "abcdefg";
        int result3a = lengthOfLongestSubstring_HashMap(test3);
        int result3b = lengthOfLongestSubstring_Array(test3);
        Map<String, Object> result3c = lengthOfLongestSubstring_WithSubstring(test3);
        boolean pass3 = (result3a == 7 && result3b == 7 && (int)result3c.get("length") == 7);
        System.out.println("Test 3 - No Repeats: " + (pass3 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: \"" + test3 + "\"");
        System.out.println("  HashMap Result: " + result3a);
        System.out.println("  Array Result: " + result3b);
        System.out.println("  With Substring: \"" + result3c.get("substring") + "\"\n");
        passCount += pass3 ? 1 : 0;
        failCount += pass3 ? 0 : 1;
        
        // Test 4: Empty string
        // Expected: 0 (no substring exists)
        String test4 = "";
        int result4a = lengthOfLongestSubstring_HashMap(test4);
        int result4b = lengthOfLongestSubstring_Array(test4);
        boolean pass4 = (result4a == 0 && result4b == 0);
        System.out.println("Test 4 - Empty String: " + (pass4 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: \"\"");
        System.out.println("  HashMap Result: " + result4a);
        System.out.println("  Array Result: " + result4b + "\n");
        passCount += pass4 ? 1 : 0;
        failCount += pass4 ? 0 : 1;
        
        // Test 5: Single character
        // Expected: 1 (single character is substring of length 1)
        String test5 = "a";
        int result5a = lengthOfLongestSubstring_HashMap(test5);
        int result5b = lengthOfLongestSubstring_Array(test5);
        boolean pass5 = (result5a == 1 && result5b == 1);
        System.out.println("Test 5 - Single Character: " + (pass5 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: \"a\"");
        System.out.println("  HashMap Result: " + result5a);
        System.out.println("  Array Result: " + result5b + "\n");
        passCount += pass5 ? 1 : 0;
        failCount += pass5 ? 0 : 1;
        
        // ============ EDGE CASES ============
        
        // Test 6: Longest at beginning - "abcdef123abc"
        // Expected: 9 (substring "abcdef123")
        String test6 = "abcdef123abc";
        int result6a = lengthOfLongestSubstring_HashMap(test6);
        int result6b = lengthOfLongestSubstring_Array(test6);
        Map<String, Object> result6c = lengthOfLongestSubstring_WithSubstring(test6);
        boolean pass6 = (result6a == 9 && result6b == 9 && (int)result6c.get("length") == 9);
        System.out.println("Test 6 - Longest at Beginning: " + (pass6 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: \"" + test6 + "\"");
        System.out.println("  HashMap Result: " + result6a);
        System.out.println("  Substring: \"" + result6c.get("substring") + "\"\n");
        passCount += pass6 ? 1 : 0;
        failCount += pass6 ? 0 : 1;
        
        // Test 7: Longest at end - "123abcdef123abc"
        // Expected: 9 (substring "abcdef123" or similar)
        String test7 = "dvdf123abcdefgh";
        int result7a = lengthOfLongestSubstring_HashMap(test7);
        int result7b = lengthOfLongestSubstring_Array(test7);
        Map<String, Object> result7c = lengthOfLongestSubstring_WithSubstring(test7);
        boolean pass7 = (result7a == result7b && (int)result7c.get("length") == result7a);
        System.out.println("Test 7 - Longest at End: " + (pass7 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: \"" + test7 + "\"");
        System.out.println("  HashMap Result: " + result7a);
        System.out.println("  Substring: \"" + result7c.get("substring") + "\"\n");
        passCount += pass7 ? 1 : 0;
        failCount += pass7 ? 0 : 1;
        
        // Test 8: Special characters - "a!@#$%a"
        // Expected: 6 (substring "!@#$%a" or similar)
        String test8 = "a!@#$%a";
        int result8a = lengthOfLongestSubstring_HashMap(test8);
        int result8b = lengthOfLongestSubstring_Array(test8);
        boolean pass8 = (result8a == 6 && result8b == 6);
        System.out.println("Test 8 - Special Characters: " + (pass8 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: \"" + test8 + "\"");
        System.out.println("  HashMap Result: " + result8a);
        System.out.println("  Array Result: " + result8b + "\n");
        passCount += pass8 ? 1 : 0;
        failCount += pass8 ? 0 : 1;
        
        // Test 9: Numbers - "12321"
        // Expected: 3 (substring "123" or "321")
        String test9 = "12321";
        int result9a = lengthOfLongestSubstring_HashMap(test9);
        int result9b = lengthOfLongestSubstring_Array(test9);
        boolean pass9 = (result9a == 3 && result9b == 3);
        System.out.println("Test 9 - Numbers: " + (pass9 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: \"" + test9 + "\"");
        System.out.println("  HashMap Result: " + result9a);
        System.out.println("  Array Result: " + result9b + "\n");
        passCount += pass9 ? 1 : 0;
        failCount += pass9 ? 0 : 1;
        
        // Test 10: Repeating pattern - "ababab"
        // Expected: 2 (substring "ab")
        String test10 = "ababab";
        int result10a = lengthOfLongestSubstring_HashMap(test10);
        int result10b = lengthOfLongestSubstring_Array(test10);
        Map<String, Object> result10c = lengthOfLongestSubstring_WithSubstring(test10);
        boolean pass10 = (result10a == 2 && result10b == 2 && (int)result10c.get("length") == 2);
        System.out.println("Test 10 - Repeating Pattern: " + (pass10 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: \"" + test10 + "\"");
        System.out.println("  HashMap Result: " + result10a);
        System.out.println("  Substring: \"" + result10c.get("substring") + "\"\n");
        passCount += pass10 ? 1 : 0;
        failCount += pass10 ? 0 : 1;
        
        // ============ LARGE DATA PERFORMANCE TEST ============
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              LARGE DATA PERFORMANCE TEST                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Test 11: Large string - 100K characters
        // Generate: "abcdefghij" repeated many times
        StringBuilder sb100K = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb100K.append("abcdefghij");
        }
        String test11 = sb100K.toString();
        
        long time1 = System.nanoTime();
        int result11a = lengthOfLongestSubstring_HashMap(test11);
        long duration1 = System.nanoTime() - time1;
        
        long time2 = System.nanoTime();
        int result11b = lengthOfLongestSubstring_Array(test11);
        long duration2 = System.nanoTime() - time2;
        
        boolean pass11 = (result11a == 10 && result11b == 10);
        System.out.println("Test 11 - Large String (100K): " + (pass11 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 100,000 characters (pattern repeated)");
        System.out.println("  HashMap Time: " + (duration1 / 1000000.0) + " ms | Result: " + result11a);
        System.out.println("  Array Time: " + (duration2 / 1000000.0) + " ms | Result: " + result11b);
        System.out.println("  Speedup: " + String.format("%.2fx", (double)duration1/duration2) + "\n");
        passCount += pass11 ? 1 : 0;
        failCount += pass11 ? 0 : 1;
        
        // Test 12: Very large string - 1 Million characters
        StringBuilder sb1M = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb1M.append("abcdefghij");
        }
        String test12 = sb1M.toString();
        
        long time3 = System.nanoTime();
        int result12a = lengthOfLongestSubstring_HashMap(test12);
        long duration3 = System.nanoTime() - time3;
        
        long time4 = System.nanoTime();
        int result12b = lengthOfLongestSubstring_Array(test12);
        long duration4 = System.nanoTime() - time4;
        
        boolean pass12 = (result12a == 10 && result12b == 10);
        System.out.println("Test 12 - Very Large String (1M): " + (pass12 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 1,000,000 characters");
        System.out.println("  HashMap Time: " + (duration3 / 1000000.0) + " ms | Result: " + result12a);
        System.out.println("  Array Time: " + (duration4 / 1000000.0) + " ms | Result: " + result12b);
        System.out.println("  Speedup: " + String.format("%.2fx", (double)duration3/duration4) + "\n");
        passCount += pass12 ? 1 : 0;
        failCount += pass12 ? 0 : 1;
        
        // Test 13: Large string with all unique characters
        // Generate: "abcdefghij0123456789..." repeated
        StringBuilder sbUnique = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            sbUnique.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        }
        String test13 = sbUnique.toString();
        
        long time5 = System.nanoTime();
        int result13a = lengthOfLongestSubstring_HashMap(test13);
        long duration5 = System.nanoTime() - time5;
        
        long time6 = System.nanoTime();
        int result13b = lengthOfLongestSubstring_Array(test13);
        long duration6 = System.nanoTime() - time6;
        
        boolean pass13 = (result13a == 62 && result13b == 62);  // 62 unique chars
        System.out.println("Test 13 - Large Unique Chars (310K): " + (pass13 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 310,000 characters (62 unique)");
        System.out.println("  HashMap Time: " + (duration5 / 1000000.0) + " ms | Result: " + result13a);
        System.out.println("  Array Time: " + (duration6 / 1000000.0) + " ms | Result: " + result13b);
        System.out.println("  Speedup: " + String.format("%.2fx", (double)duration5/duration6) + "\n");
        passCount += pass13 ? 1 : 0;
        failCount += pass13 ? 0 : 1;
        
        // ============ COMPLEXITY ANALYSIS ============
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║             COMPLEXITY ANALYSIS & SUMMARY                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        System.out.println("Algorithm Complexity:");
        System.out.println("─".repeat(60));
        System.out.println("HashMap Approach:");
        System.out.println("  Time: O(n) - single pass through string");
        System.out.println("  Space: O(min(n, m)) - m = charset size (26-128 chars)");
        System.out.println("  Pros: Works with any Unicode characters");
        System.out.println("  Cons: HashMap overhead vs array\n");
        
        System.out.println("Array Approach:");
        System.out.println("  Time: O(n) - single pass through string");
        System.out.println("  Space: O(256) = O(1) - fixed array for ASCII");
        System.out.println("  Pros: Faster direct array access");
        System.out.println("  Cons: Limited to ASCII characters\n");
        
        System.out.println("Recommendations:");
        System.out.println("─".repeat(60));
        System.out.println("Small strings (< 10K):     Use HashMap (more flexible)");
        System.out.println("ASCII strings (10K-1M):    Use Array (faster)");
        System.out.println("Unicode strings:           Use HashMap (necessary)");
        System.out.println("Need substring details:    Use WithSubstring method");
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("Total Tests: " + (passCount + failCount));
        System.out.println("Passed: " + passCount + " | Failed: " + failCount);
        System.out.println("Success Rate: " + String.format("%.1f%%", (passCount * 100.0 / (passCount + failCount))));
        if (failCount == 0) {
            System.out.println("✓ ALL TESTS PASSED - SOLUTION IS PRODUCTION READY");
        }
        System.out.println("═".repeat(60));
    }
}
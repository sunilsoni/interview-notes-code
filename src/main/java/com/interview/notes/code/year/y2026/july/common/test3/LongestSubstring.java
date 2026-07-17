package com.interview.notes.code.year.y2026.july.common.test3;

public class LongestSubstring { // Main class to encapsulate our logic and test runner.

    public static int lengthOfLongestSubstring(String s) { // Method accepts the input string and returns the maximum substring length.
        
        var lastSeen = new int[128]; // Creates a lightweight array to track all 128 standard ASCII characters. Java defaults all values to 0.
        var maxLength = 0; // Variable to store the highest valid substring length we have found so far.
        var left = 0; // The left boundary pointer of our sliding window.
        
        for (var right = 0; right < s.length(); right++) { // Loop moves the right pointer through every single character in the string.
            
            var c = s.charAt(right); // Extracts the current character at the right pointer's position.
            
            // If we've seen this character before, 'lastSeen' tells us where it was. 
            // We instantly jump the 'left' pointer past that duplicate character to keep our current window valid.
            left = Math.max(left, lastSeen[c]); 
            
            var currentWindowSize = right - left + 1; // Calculates the exact size of our current valid window (inclusive).
            maxLength = Math.max(maxLength, currentWindowSize); // If this new window is bigger than our previous best, we save it.
            
            lastSeen[c] = right + 1; // Saves the NEXT index (right + 1) so if we see this character again, the left pointer knows exactly where to jump.
        }
        
        return maxLength; // Returns the absolute highest length we calculated during the entire loop.
    }

    // --- Testing Section Below ---
    
    public static void main(String[] args) { // Main method serves as our lightweight, dependency-free test execution framework.
        
        runTest("abcabcbb", 3); // LeetCode Case 1: Standard repeating pattern. Longest is "abc" (3).
        runTest("bbbbb", 1); // LeetCode Case 2: All identical characters. Longest is "b" (1).
        runTest("pwwkew", 3); // LeetCode Case 3: Pattern with jumps. Longest is "wke" (3). Note that "pwke" is a subsequence, not a substring.
        runTest("", 0); // Edge Case: Empty string should mathematically return a length of 0.
        runTest(" ", 1); // Edge Case: A single space is a valid character and should return a length of 1.
        runTest("au", 2); // Edge Case: Two unique characters, bounds checking.
        
        // Large Data Test: We generate a string of 50,000 characters repeating the alphabet to simulate heavy load.
        var largeInput = "abcdefghijklmnopqrstuvwxyz".repeat(2000); 
        runTest(largeInput, 26); // The longest unique substring in a repeating alphabet is 26 characters.
    }

    private static void runTest(String input, int expected) { // Helper function to execute tests and print visual PASS/FAIL outputs.
        
        var result = lengthOfLongestSubstring(input); // Executes our core logic method using the provided test string.
        var status = (result == expected) ? "PASS" : "FAIL"; // Ternary operator checks if actual output matches expected, formatting the status.
        
        var displayInput = input.length() > 50 ? "Large Input Data (Truncated)" : input; // Prevents console flooding by hiding massive strings from the printout.
        System.out.println("Status: " + status + " | Expected: " + expected + " | Actual: " + result + " | Input: '" + displayInput + "'"); // Prints the clean, readable test report to the console.
    }
}
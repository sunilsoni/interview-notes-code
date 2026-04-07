package com.interview.notes.code.year.y2026.april.common.test2;

public class VowelSubsequenceBruteForce {

    // A constant string holding our strict target sequence
    private static final String VOWELS = "aeiou";

    // Simple main method for testing (No JUnit)
    public static void main(String[] args) {
        // Test Case 1: Sample 0
        runTest("aeiaaioooaauuaeiu", 10);
        
        // Test Case 2: Sample 1 (Missing 'u')
        runTest("aeiaaioooaa", 0);
        
        // Test Case 3: Example 1
        runTest("aeeiiouu", 8);

        // Test Case 4: Example 2
        runTest("aeeiiaouu", 8);

        // NOTE: We are NOT running the 500,000 character test here.
        // A brute force recursive approach would take years to process that much data!
    }

    // Custom test executor to format and print PASS/FAIL results
    private static void runTest(String s, int expected) {
        int result = longestVowelSubsequenceBruteForce(s); // Execute brute force
        String status = (result == expected) ? "PASS" : "FAIL"; // Verify against expected
        System.out.println(status + " | Expected: " + expected + " | Got: " + result + " | Input: " + s);
    }

    // Main entry point for the brute force logic
    public static int longestVowelSubsequenceBruteForce(String s) {
        // Start recursive tree at index 0 of string, looking for the 0th vowel ('a')
        int result = exploreAllPaths(s, 0, 0);
        
        // If the result is heavily negative, it means a full 'aeiou' sequence was never completed
        return result < 0 ? 0 : result; 
    }

    // Recursive method that brute-forces every single valid combination
    private static int exploreAllPaths(String s, int strIdx, int vowelIdx) {
        // Base Condition: We have reached the very end of the input string
        if (strIdx == s.length()) {
            // Check if we reached the final vowel 'u' (index 4). 
            // If yes, this path is valid (return 0 to stop adding length). 
            // If no, this path failed to get all vowels, return a massive negative number to invalidate it.
            return vowelIdx == 4 ? 0 : -999999; 
        }

        // Grab the current character from the string
        char currentChar = s.charAt(strIdx);
        // Grab the vowel we are currently allowed to look for
        char currentVowelTarget = VOWELS.charAt(vowelIdx);

        // OPTION 1: We choose to completely ignore the current character and move to the next one
        int maxLen = exploreAllPaths(s, strIdx + 1, vowelIdx);

        // OPTION 2: The character matches our current needed vowel
        if (currentChar == currentVowelTarget) {
            // We take this character (add 1 to length) and STAY on the current vowel state
            int takeAndStay = 1 + exploreAllPaths(s, strIdx + 1, vowelIdx);
            // Update maxLen if this choice yielded a longer sequence
            maxLen = Math.max(maxLen, takeAndStay); 
        }

        // OPTION 3: The character matches the NEXT required vowel in the sequence
        // (We also ensure vowelIdx < 4 so we don't look past 'u')
        if (vowelIdx < 4 && currentChar == VOWELS.charAt(vowelIdx + 1)) {
            // We take this character (add 1 to length) and ADVANCE to the next vowel state
            int takeAndMove = 1 + exploreAllPaths(s, strIdx + 1, vowelIdx + 1);
            // Update maxLen if this forward transition yielded a longer sequence
            maxLen = Math.max(maxLen, takeAndMove);
        }

        // Return the absolute longest length found from all the branching choices made above
        return maxLen; 
    }
}
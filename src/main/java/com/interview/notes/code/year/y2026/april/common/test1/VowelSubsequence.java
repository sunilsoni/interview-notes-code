package com.interview.notes.code.year.y2026.april.common.test1;

public class VowelSubsequence {

    // Main method to act as our simple test runner (No JUnit used)
    public static void main(String[] args) {
        // Test Case 1: Sample 0 from instructions
        runTest("aeiaaioooaauuaeiu", 10);
        
        // Test Case 2: Sample 1 from instructions (Missing 'u')
        runTest("aeiaaioooaa", 0);
        
        // Test Case 3: Example 1 from Description
        runTest("aeeiiouu", 8);
        
        // Test Case 4: Example 2 from Description
        runTest("aeeiiaouu", 8);

        // Test Case 5: Large data constraint test to ensure O(N) performance doesn't time out
        StringBuilder largeData = new StringBuilder();
        // Append "aeiou" 100,000 times to create a 500,000 character string
        for (int i = 0; i < 100000; i++) {
            largeData.append("aeiou"); 
        }
        runTest(largeData.toString(), 500000);
    }

    // Custom test executor to format and print PASS/FAIL results
    private static void runTest(String s, int expected) {
        // Execute the solution logic
        int result = longestVowelSubsequence(s); 
        // Determine if output matches expected value
        String status = (result == expected) ? "PASS" : "FAIL"; 
        // Truncate long strings for console readability
        String displayString = s.length() > 25 ? s.substring(0, 25) + "...(length: " + s.length() + ")" : s;
        // Print the formatted test summary
        System.out.println(status + " | Expected: " + expected + " | Got: " + result + " | Input: " + displayString);
    }

    // Primary function to evaluate the longest vowel subsequence
    public static int longestVowelSubsequence(String s) {
        // Initialize an integer array of size 5 to hold the max lengths for each vowel state
        int[] dp = new int[5]; // dp[0] = 'a', dp[1] = 'e', dp[2] = 'i', dp[3] = 'o', dp[4] = 'u'
        
        // Convert string to an IntStream utilizing the Java 8 Stream API
        s.chars().forEach(c -> { // Iterate over each character's ASCII value in the stream sequence
            // Use Java 21 enhanced switch expression for clean, minimal syntax
            switch (c) { 
                // If character is 'a', it can always start or extend the 'a' subsequence
                case 'a' -> dp[0]++; // Increment the count of 'a', as it is the foundation
                
                // If character is 'e', we execute a code block to verify state
                case 'e' -> { // Open execution block for 'e'
                    // Check if we have seen at least one 'a' (dp[0] > 0) to allow a valid transition
                    if (dp[0] > 0) { // Ensures 'e' is legally following an 'a'
                        // Update 'e' length by choosing the longest path (prior 'a' or current 'e') and adding 1
                        dp[1] = Math.max(dp[0], dp[1]) + 1; // Stores the absolute longest valid sequence ending in 'e'
                    } // Close validation block for 'e'
                } // Close switch case for 'e'
                
                // If character is 'i', we execute a code block to verify state
                case 'i' -> { // Open execution block for 'i'
                    // Check if we have seen at least one valid 'e' sequence (dp[1] > 0)
                    if (dp[1] > 0) { // Ensures 'i' is legally following an 'e'
                        // Update 'i' length by choosing the longest path (prior 'e' or current 'i') and adding 1
                        dp[2] = Math.max(dp[1], dp[2]) + 1; // Stores the absolute longest valid sequence ending in 'i'
                    } // Close validation block for 'i'
                } // Close switch case for 'i'
                
                // If character is 'o', we execute a code block to verify state
                case 'o' -> { // Open execution block for 'o'
                    // Check if we have seen at least one valid 'i' sequence (dp[2] > 0)
                    if (dp[2] > 0) { // Ensures 'o' is legally following an 'i'
                        // Update 'o' length by choosing the longest path (prior 'i' or current 'o') and adding 1
                        dp[3] = Math.max(dp[2], dp[3]) + 1; // Stores the absolute longest valid sequence ending in 'o'
                    } // Close validation block for 'o'
                } // Close switch case for 'o'
                
                // If character is 'u', we execute a code block to verify state
                case 'u' -> { // Open execution block for 'u'
                    // Check if we have seen at least one valid 'o' sequence (dp[3] > 0)
                    if (dp[3] > 0) { // Ensures 'u' is legally following an 'o'
                        // Update 'u' length by choosing the longest path (prior 'o' or current 'u') and adding 1
                        dp[4] = Math.max(dp[3], dp[4]) + 1; // Stores the absolute longest valid sequence ending in 'u'
                    } // Close validation block for 'u'
                } // Close switch case for 'u'
            } // Close Java 21 switch block
        }); // Close the Java 8 Stream lambda processing loop
        
        // Output the value at index 4, representing the longest fully valid 'aeiou' sequence
        return dp[4]; // If a complete sequence never formed, this naturally defaults to returning 0
    }
}
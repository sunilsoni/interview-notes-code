package com.interview.notes.code.year.y2025.may.common.test13;

public class EditDistance {
    public static void main(String[] args) {
        // Test cases to verify solution
        testEditDistance("horse", "ros", 3);
        testEditDistance("intention", "execution", 5);
        testEditDistance("", "a", 1);
        testEditDistance("abc", "", 3);
        testEditDistance("", "", 0);
        testEditDistance("plasma", "altruism", 6);
        
        // Large input test case
        testEditDistance("a".repeat(1000), "b".repeat(1000), 1000);
    }

    public static int minDistance(String word1, String word2) {
        // Get lengths of both strings
        int m = word1.length();
        int n = word2.length();
        
        // Create DP table with size (m+1) x (n+1)
        // +1 is needed to handle empty string cases
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize first row - transforming empty string to word2
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        // Initialize first column - transforming word1 to empty string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match, no operation needed
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Take minimum of three operations:
                    // 1. Replace (dp[i-1][j-1])
                    // 2. Delete (dp[i-1][j])
                    // 3. Insert (dp[i][j-1])
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], 
                                  Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        
        // Return bottom-right cell containing minimum operations
        return dp[m][n];
    }
    
    // Test method to verify solutions
    private static void testEditDistance(String word1, String word2, int expected) {
        int result = minDistance(word1, word2);
        System.out.printf("Input: word1 = \"%s\", word2 = \"%s\"%n", word1, word2);
        System.out.printf("Expected: %d, Got: %d, Test: %s%n", 
            expected, result, (result == expected ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }
}

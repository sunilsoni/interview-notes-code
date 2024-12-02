package com.interview.notes.code.year.y2024.may24.test6;

import java.util.HashMap;
import java.util.Map;

public class GoodSubsequences2 {

    public static int countGoodSubsequences(String word) {
        int mod = 1000000007; // Modulo value (10^9 + 7)

        // Create a hash map to store character frequencies
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : word.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Initialize dp array to store good subsequence counts
        int[][] dp = new int[word.length() + 1][word.length() + 1];

        // Base case: single character subsequence is good if frequency is 1
        for (int i = 1; i <= word.length(); i++) {
            dp[i][i] = freqMap.get(word.charAt(i - 1)) == 1 ? 1 : 0;
        }

        // Build dynamic programming table
        for (int len = 2; len <= word.length(); len++) {
            for (int i = 1; i <= word.length() - len + 1; i++) {
                int j = i + len - 1; // Calculate end index for the subsequence

                // Case 1: Include current character if it forms a good subsequence
                int includeCase = 0;
                if (freqMap.get(word.charAt(j - 1)) >= len) {
                    includeCase = dp[i][j - 1] % mod; // Use previously calculated count
                }

                // Case 2: Exclude current character
                int excludeCase = dp[i][j - 1] % mod;

                dp[i][j] = (includeCase + excludeCase) % mod;
            }
        }

        // Total non-empty subsequences = all subsequences - bad subsequences
        int totalSubsequences = (1 << word.length()) - 1; // Calculate 2^n - 1 (all possible subsequences)
        int badSubsequences = totalSubsequences - dp[1][word.length()]; // Subtract good subsequences

        // Handle negative result due to modulo operation
        return badSubsequences < 0 ? badSubsequences + mod : badSubsequences;
    }

    public static void main(String[] args) {
        System.out.println(countGoodSubsequences("abcd")); // Output: 15
        System.out.println(countGoodSubsequences("baab")); // Output: 11
        System.out.println(countGoodSubsequences("abbabc")); // Output: 31
    }
}

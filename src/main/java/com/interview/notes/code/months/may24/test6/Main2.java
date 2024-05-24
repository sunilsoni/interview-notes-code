package com.interview.notes.code.months.may24.test6;

public class Main2 {

    public static int countGoodSubsequences(String word) {
        // Initialize an array to store the frequency of each character in the word
        int[] freq = new int[26];
        // Count the frequency of each character
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Initialize dp array to store the number of good subsequences ending with each character
        long[] dp = new long[word.length() + 1];
        // Initialize dp[0] to 1, representing an empty subsequence
        dp[0] = 1;

        // Iterate through each character in the word
        for (int i = 0; i < word.length(); i++) {
            // Current character
            char currentChar = word.charAt(i);
            // Update dp[i+1] based on whether including current character or not
            dp[i + 1] = (2 * dp[i]) % 1000000007;
            // If the frequency of current character is greater than 1, add dp[i - frequency] to dp[i+1]
            if (freq[currentChar - 'a'] > 1) {
                // Check if the index is valid before accessing it
                if (i - freq[currentChar - 'a'] >= 0) {
                    dp[i + 1] = (dp[i + 1] + dp[i - freq[currentChar - 'a']]) % 1000000007;
                }
            }
        }
        // Return the number of good subsequences modulo (10^9 + 7)
        return (int) (dp[word.length()] - 1) % 1000000007;
    }

    public static void main(String[] args) {
        // Sample test cases
        System.out.println(countGoodSubsequences("abcd")); // Output: 15
        System.out.println(countGoodSubsequences("baab")); // Output: 11
    }
}

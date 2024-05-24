package com.interview.notes.code.months.may24.test6;

public class GoodSubsequences1 {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        System.out.println(countGoodSubsequences("abcd")); // Should print 15
        System.out.println(countGoodSubsequences("baab")); // Should print 11
    }

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
        return (int) (dp[word.length()] - 1);
    }

    public static int countGoodSubsequences3(String word) {
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
                dp[i + 1] = (dp[i + 1] + dp[i - freq[currentChar - 'a']]) % 1000000007;
            }
        }
        // Return the number of good subsequences modulo (10^9 + 7)
        return (int) (dp[word.length()] - 1);
    }

    public static int countGoodSubsequences2(String word) {
        int n = word.length();
        int MOD = (int) 1e9 + 7;

        // Initialize dp array
        int[] dp = new int[n + 1];
        dp[0] = 1;

        // Initialize frequency array
        int[] freq = new int[26];

        for (int i = 1; i <= n; i++) {
            char c = word.charAt(i - 1);
            int idx = c - 'a';

            // Update dp[i]
            dp[i] = (2 * dp[i - 1]) % MOD;
            dp[i] = (dp[i] - dp[freq[idx]] + MOD) % MOD;

            // Update frequency of character c
            freq[idx] = i;
        }

        // Subtract 1 to exclude empty subsequence
        return (dp[n] - 1 + MOD) % MOD;
    }

    public static int countGoodSubsequences1(String word) {
        long totalSubsequences = (1L << word.length()) - 1; // 2^n - 1, subtract the empty subsequence

        // Frequency array for each character
        int[] frequency = new int[26];
        for (int i = 0; i < word.length(); i++) {
            frequency[word.charAt(i) - 'a']++;
        }

        // We need to calculate bad subsequences where frequencies are not uniform
        // For each set of characters with the same frequency, compute subsequences
        int[] freqCount = new int[word.length() + 1];
        for (int f : frequency) {
            if (f > 0) {
                freqCount[f]++;
            }
        }

        long badSubsequences = 0;
        for (int i = 1; i <= word.length(); i++) {
            if (freqCount[i] > 1) {
                // Calculate all subsequences with this frequency but are not good
                badSubsequences += (1L << (i * freqCount[i])) - (freqCount[i] * (1L << i));
            }
        }

        if (badSubsequences < 0) {
            badSubsequences += MOD;
        }
        badSubsequences %= MOD;

        long goodSubsequences = totalSubsequences - badSubsequences;
        if (goodSubsequences < 0) {
            goodSubsequences += MOD;
        }

        return (int) (goodSubsequences % MOD);
    }
}

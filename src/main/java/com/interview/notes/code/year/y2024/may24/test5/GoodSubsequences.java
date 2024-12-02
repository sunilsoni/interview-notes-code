package com.interview.notes.code.year.y2024.may24.test5;

public class GoodSubsequences {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        System.out.println(countGoodSubsequences("abcd")); // Should print 15
        System.out.println(countGoodSubsequences("baab")); // Should print 11
    }

    public static int countGoodSubsequences(String word) {
        int n = word.length();
        long totalSubsequences = (1L << n) - 1; // Total subsequences is 2^n - 1 (subtract the empty subsequence)

        int[] frequency = new int[26]; // Frequency array for each character
        for (int i = 0; i < n; i++) {
            frequency[word.charAt(i) - 'a']++;
        }

        // Calculate bad subsequences
        long badSubsequences = 0;
        for (int f : frequency) {
            if (f > 0) {
                // The number of subsequences that include this character only
                // This is the set of all subsets of a particular character occurrences
                badSubsequences += (1L << f) - 1;
                if (badSubsequences >= MOD) badSubsequences -= MOD;
            }
        }

        long goodSubsequences = totalSubsequences - badSubsequences;
        if (goodSubsequences < 0) goodSubsequences += MOD; // Handle negative result due to modulo subtraction
        return (int) (goodSubsequences % MOD);
    }
}

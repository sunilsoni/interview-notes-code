package com.interview.notes.code.year.y2025.march.common.test9;

import java.util.Arrays;

public class GoodSubsequencesSolution {

    // A large prime for modulo operations
    private static final int MOD = 1_000_000_007;

    // Precomputed factorials and inverse factorials
    private static long[] fact;
    private static long[] invFact;

    /**
     * Precompute factorials and inverse factorials up to `maxN` (inclusive).
     * This allows us to quickly compute nCk % MOD in O(1) time.
     *
     * @param maxN The maximum value for which we need factorials.
     */
    private static void precomputeFactorials(int maxN) {
        fact = new long[maxN + 1];
        invFact = new long[maxN + 1];

        // Compute fact[0..maxN]
        fact[0] = 1;
        for (int i = 1; i <= maxN; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        // Compute invFact[maxN] using Fermat's little theorem
        invFact[maxN] = modInverse(fact[maxN], MOD);

        // Compute invFact[maxN-1..0]
        for (int i = maxN - 1; i >= 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    /**
     * Computes the modular inverse of x under modulo p using fast exponentiation.
     * (x^(p-2) mod p) when p is prime (Fermat's little theorem).
     *
     * @param x The number to invert.
     * @param p The prime modulus.
     * @return The modular inverse of x modulo p.
     */
    private static long modInverse(long x, long p) {
        return fastPow(x, p - 2, p);
    }

    /**
     * Fast exponentiation: (base^exp) % mod.
     *
     * @param base Base of the exponentiation.
     * @param exp  Exponent.
     * @param mod  Modulus.
     * @return (base ^ exp) % mod.
     */
    private static long fastPow(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    /**
     * Computes nCk % MOD using precomputed factorials.
     *
     * @param n The size of the set.
     * @param k The size of the subset.
     * @return The binomial coefficient nCk modulo MOD.
     */
    private static long nCr(int n, int k) {
        if (k < 0 || k > n) {
            return 0;
        }
        return (fact[n] * ((invFact[k] * invFact[n - k]) % MOD)) % MOD;
    }

    /**
     * Main method to count the number of good subsequences modulo 1,000,000,007.
     * A good subsequence is one where all characters appear the same number of times.
     *
     * @param word The input string.
     * @return The number of good subsequences modulo MOD.
     */
    public static int countGoodSubsequences(String word) {
        // Step 1: Count frequency of each character (only a-z, so size 26).
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2: Find maximum frequency among all characters.
        int maxFreq = Arrays.stream(freq).max().getAsInt();

        // Step 3: Precompute factorials up to the length of the string (for safety).
        //         This allows nCr calculations for up to word.length().
        precomputeFactorials(word.length());

        // Step 4: Calculate the number of good subsequences.
        long totalGood = 0;
        // Iterate over each possible x = 1..maxFreq
        for (int x = 1; x <= maxFreq; x++) {
            long waysForX = 1;
            // Multiply (1 + C(freq[i], x)) for each character i with freq[i] >= x
            for (int f : freq) {
                if (f >= x) {
                    long chooseX = nCr(f, x);    // Number of ways to pick x from f
                    long term = (1 + chooseX) % MOD;
                    waysForX = (waysForX * term) % MOD;
                }
            }
            // Subtract 1 to exclude the empty subsequence
            waysForX = (waysForX - 1 + MOD) % MOD;
            totalGood = (totalGood + waysForX) % MOD;
        }

        // Return the total modulo 1,000,000,007
        return (int) totalGood;
    }

    /**
     * A simple test method (no JUnit) that runs multiple test cases
     * and prints PASS/FAIL for each.
     */
    public static void main(String[] args) {
        // Provided Test Cases
        testCase("abcd", 15);  // Sample case 0
        testCase("baab", 11);  // Sample case 1

        // Additional Tests
        testCase("aaa", 3);    // Good subsequences: "a", "a", "a", "aa", "aa", "aa", "aaa"
        // Actually let's see which are "good":
        // Non-empty subsequences = 7 total: "a"(3 ways), "aa"(3 ways), "aaa"(1 way).
        // But "a", "aa", "aaa" are all good. Summation = 7.
        // Check result:
        // For x=1: freq=3 => (1 + C(3,1)=1+3=4) -1=3
        // For x=2: freq=3 => (1 + C(3,2)=1+3=4) -1=3
        // For x=3: freq=3 => (1 + C(3,3)=1+1=2) -1=1
        // total=3+3+1=7
        testCase("aaa", 7);

        // Edge Case: Empty string (though not explicitly stated if it can be empty)
        // If empty is allowed, the number of non-empty subsequences is 0.
        testCase("", 0);

        // Large Input Test (just conceptual check here)
        // We'll create a large string of repeated characters
        // to see if it runs within a reasonable time
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20000; i++) {
            sb.append('a');
        }
        // We won't know the exact number easily by hand, 
        // but we just want to ensure it doesn't crash or timeout
        int resultLarge = countGoodSubsequences(sb.toString());
        System.out.println("Large test (20000 'a's) => " + resultLarge + " (PASS if it completes quickly)");
    }

    /**
     * Helper method to check the result of `countGoodSubsequences` against an expected value.
     *
     * @param input    The input string.
     * @param expected The expected output.
     */
    private static void testCase(String input, int expected) {
        int result = countGoodSubsequences(input);
        if (result == expected) {
            System.out.println("Input: \"" + input + "\" => " + result + " (PASS)");
        } else {
            System.out.println("Input: \"" + input + "\" => " + result + " (FAIL) [Expected: " + expected + "]");
        }
    }
}
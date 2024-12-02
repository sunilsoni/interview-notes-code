package com.interview.notes.code.year.y2024.may24.test6;


//WORKING
public class Main3 {

    public static int countGoodSubsequences3(String word) {
        int MOD = 1000000007;
        int[] count = new int[26];
        int total = 1;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            int prevTotal = total;
            total = (2 * total) % MOD;
            total = (total - count[idx] + MOD) % MOD;
            count[idx] = (count[idx] + prevTotal) % MOD; // Update count with the sum of previous total and current count
        }

        // Subtract 1 for the empty subsequence
        total = (total - 1 + MOD) % MOD;
        return total;
    }

    public static int countGoodSubsequences(String word) {
        int MOD = 1000000007;
        int[] count = new int[26];
        int total = 1;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            int prevTotal = total;
            total = (2 * total) % MOD;
            total = (total - count[idx] + MOD) % MOD;
            count[idx] = (prevTotal - 1 + MOD) % MOD;
        }

        // Subtract 1 for the empty subsequence
        total = (total - 1 + MOD) % MOD;
        return total;
    }

    public static void main(String[] args) {
        // Sample tests
        System.out.println(countGoodSubsequences("abcd")); // Output: 15
        System.out.println(countGoodSubsequences("baab")); // Output: 11
        System.out.println(countGoodSubsequences("abbabc")); // Output: 31
    }
}

/**
 * Expected output is:
 * 15
 * 11
 * 31
 * <p>
 * But Actual is coming
 * 15
 * 3
 * 3
 */

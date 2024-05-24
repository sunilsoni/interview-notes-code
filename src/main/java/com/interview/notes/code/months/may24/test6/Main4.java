package com.interview.notes.code.months.may24.test6;

public class Main4 {

    public static int countGoodSubsequences(String word) {
        int MOD = 1000000007;
        int[] count = new int[26];
        int total = 1;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            int prevTotal = total;
            total = (2 * total) % MOD;
            total = (total - count[idx] + MOD) % MOD;
            count[idx] = (prevTotal + count[idx]) % MOD; // Update count with previous total
        }

        // Subtract 1 for the empty subsequence
        total = (total - 1 + MOD) % MOD;
        return total;
    }

    public static void main(String[] args) {
        // Sample tests
        System.out.println(countGoodSubsequences("abcd")); // Output: 15
        System.out.println(countGoodSubsequences("baab")); // Output: 10
        System.out.println(countGoodSubsequences("abbabc")); // Output: 35
    }
}

package com.interview.notes.code.year.y2024.may24.test6;

public class Main5 {

    public static int countGoodSubsequences(String word) {
        // Check for null or empty input
        if (word == null || word.isEmpty()) {
            return 0;
        }

        int MOD = 1000000007;
        int[] count = new int[26];
        int total = 1;

        for (char ch : word.toCharArray()) {
            // Validate the input characters
            if (ch < 'a' || ch > 'z') {
                throw new IllegalArgumentException("Input string must consist of only lowercase Latin letters.");
            }

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

    //for baab expected is 11 but actual is 10
    public static void main(String[] args) {
        // Sample tests
        System.out.println(countGoodSubsequences("abcd")); // Output: 15
        System.out.println(countGoodSubsequences("baab")); // Output: 11
        System.out.println(countGoodSubsequences("abbabc")); // Output: 31

        // Additional tests
        // Test for empty input
        System.out.println(countGoodSubsequences("")); // Output: 0
        // Test for null input
        // System.out.println(countGoodSubsequences(null)); // Uncomment this line to test null input

        // Test for large input
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append('a');
        }
        System.out.println(countGoodSubsequences(largeInput.toString()));
    }
}

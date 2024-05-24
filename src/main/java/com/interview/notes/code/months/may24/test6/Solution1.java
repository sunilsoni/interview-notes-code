package com.interview.notes.code.months.may24.test6;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    private static final int MOD = 1000000007;

    public static int countGoodSubsequences1(String word) {
        Map<Character, Integer> frequency = new HashMap<>();
        // Count frequency of each character
        for (char c : word.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        Map<Integer, Integer> freqCount = new HashMap<>();
        // Count how many characters have the same frequency
        for (int f : frequency.values()) {
            freqCount.put(f, freqCount.getOrDefault(f, 0) + 1);
        }

        long totalSubsequences = 0;
        // Calculate total number of subsequences
        for (int count : frequency.values()) {
            totalSubsequences += (1L << count) - 1;
            totalSubsequences %= MOD;
        }

        long nonGoodSubsequences = 0;
        // Calculate non-good subsequences
        for (Map.Entry<Integer, Integer> entry : freqCount.entrySet()) {
            int f = entry.getKey(); // frequency
            int numChars = entry.getValue(); // number of characters with this frequency
            if (numChars > 1) {
                long allSubsequencesWithF = (1L << (f * numChars)) - 1;
                long goodSubsequencesWithF = (1L << f) - 1;
                nonGoodSubsequences += (allSubsequencesWithF - numChars * goodSubsequencesWithF) % MOD;
                nonGoodSubsequences %= MOD;
            }
        }

        long goodSubsequences = (totalSubsequences - nonGoodSubsequences + MOD) % MOD;
        return (int) goodSubsequences;
    }

    public static int countGoodSubsequences(String word) {
        int n = word.length();
        int[] freq = new int[26];

        for (int i = 0; i < n; i++) {
            freq[word.charAt(i) - 'a']++;
        }

        long result = 1;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                result = (result * (freq[i] + 1)) % 1000000007;
            }
        }

        return (int) (result - 1) % 1000000007;
    }

    public static void main(String[] args) {
        System.out.println(countGoodSubsequences("abcd")); // Output: 15
        System.out.println(countGoodSubsequences("baab")); // Output: 11
    }
}

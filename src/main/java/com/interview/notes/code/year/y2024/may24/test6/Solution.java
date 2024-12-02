package com.interview.notes.code.year.y2024.may24.test6;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private static final int MOD = 1000000007;

    public static int countGoodSubsequences(String word) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        Map<Integer, Integer> freqCount = new HashMap<>();
        for (int f : frequency.values()) {
            freqCount.put(f, freqCount.getOrDefault(f, 0) + 1);
        }

        long total = 0;
        for (Map.Entry<Integer, Integer> entry : freqCount.entrySet()) {
            int f = entry.getKey();
            int count = entry.getValue();
            // Calculate 2^count - 1, all non-empty subsequences of characters with frequency f
            long subseqCount = (pow(2, count) - 1 + MOD) % MOD;
            total = (total + subseqCount) % MOD;
        }

        return (int) total;
    }

    private static long pow(long base, int exp) {
        long result = 1;
        long b = base % MOD;
        while (exp > 0) {
            if ((exp & 1) != 0) {
                result = (result * b) % MOD;
            }
            b = (b * b) % MOD;
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(countGoodSubsequences("abcd")); // 15
        System.out.println(countGoodSubsequences("baab")); // 11
        System.out.println(countGoodSubsequences("abbabc")); // 31
    }
}

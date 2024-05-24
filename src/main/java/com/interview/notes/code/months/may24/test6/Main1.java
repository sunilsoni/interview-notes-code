package com.interview.notes.code.months.may24.test6;

public class Main1 {
    public static void main(String[] args) {
        System.out.println(countGoodSubsequences("abcd")); // Output: 15
        System.out.println(countGoodSubsequences("baab")); // Output: 11
    }

    public static int countGoodSubsequences(String word) {
        int n = word.length();
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        long result = 1;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                result = (result * (freq[i] + 1)) % 1000000007;
            }
        }

        return (int) ((result - 1) % 1000000007);
    }
}
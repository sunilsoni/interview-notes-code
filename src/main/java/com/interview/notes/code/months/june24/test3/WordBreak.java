package com.interview.notes.code.months.june24.test3;

import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        // Test cases
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        System.out.println(wordBreak(s1, wordDict1)); // Output: true

        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        System.out.println(wordBreak(s2, wordDict2)); // Output: true

        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak(s3, wordDict3)); // Output: false
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<String, Boolean> memo = new HashMap<>();
        return canBreak(s, wordSet, memo);
    }

    private static boolean canBreak(String s, Set<String> wordSet, Map<String, Boolean> memo) {
        if (s.isEmpty()) {
            return true;
        }
        
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            if (wordSet.contains(prefix) && canBreak(s.substring(i), wordSet, memo)) {
                memo.put(s, true);
                return true;
            }
        }

        memo.put(s, false);
        return false;
    }
}

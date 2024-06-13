package com.interview.notes.code.months.june24.test3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        System.out.println(solution.wordBreak(s1, wordDict1));  // Output: true

        // Example 2
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        System.out.println(solution.wordBreak(s2, wordDict2));  // Output: true

        // Example 3
        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(solution.wordBreak(s3, wordDict3));  // Output: false
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert list to a hash set for faster lookups.
        Set<String> wordSet = new HashSet<>(wordDict);

        // Create a memoization array to store results of subproblems.
        Boolean[] memo = new Boolean[s.length()];

        return wordBreakRecursive(s, wordSet, 0, memo);
    }

    private boolean wordBreakRecursive(String s, Set<String> wordSet, int start, Boolean[] memo) {
        // Base case: If start index reaches the end of the string, return true.
        if (start == s.length()) {
            return true;
        }

        // If we have already computed this subproblem, return the stored result.
        if (memo[start] != null) {
            return memo[start];
        }

        // Attempt to break the string using every index from start to end
        for (int end = start + 1; end <= s.length(); end++) {
            // Check if the current substring is in the dictionary
            if (wordSet.contains(s.substring(start, end))) {
                // Recursively check for the rest of the string
                if (wordBreakRecursive(s, wordSet, end, memo)) {
                    memo[start] = true;
                    return true;
                }
            }
        }

        // Store the result in memoization array
        memo[start] = false;
        return false;
    }
}

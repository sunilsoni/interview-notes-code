package com.interview.notes.code.year.y2023.nov23.test5;

import java.util.List;

/**
 * - **Objective**: Write a program that determines if a given string (`S`) can be segmented into a space-separated sequence of one or more words that are present in a dictionary (`wordDict`). The program should return `true` if such segmentation is possible, otherwise `false`.
 * <p>
 * - **Dictionary Usage**: The same word in the dictionary can be used multiple times in the segmentation.
 * <p>
 * - **Examples**:
 * - Example #1: For the string `applepenapple` and the dictionary `["apple", "pen"]`, the output should be `true` because the string can be segmented into `"apple pen apple"`.
 * - Example #2: For the string `catsandog` and the dictionary `["cats", "dog", "sand", "and", "cat"]`, the output should be `false` because there's no complete segmentation of the string that uses only the words in the dictionary.
 * <p>
 * - **Constraints**: The number of words in the dictionary `N` is between 1 and 25.
 * <p>
 * - **Function Signature**:
 * ```java
 * public static String solve(String S, List<String> wordDict)
 * ```
 * <p>
 * - **Input**:
 * - The first line contains the string `S`.
 * - The second line contains an integer `N`, representing the size of `wordDict`.
 * - The third line contains `N` space-separated strings, representing the words in the dictionary.
 * <p>
 * - **Output**: Print `true` if `S` can be segmented into a space-separated sequence using the words from `wordDict`, otherwise print `false`.
 */
public class Solution {
    public static boolean solve(String S, List<String> wordDict) {
        // Build Trie from dictionary
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            root.insert(word);
        }

        boolean[] dp = new boolean[S.length() + 1];
        dp[0] = true;

        for (int i = 0; i < S.length(); i++) {
            if (!dp[i]) continue;

            TrieNode node = root;
            for (int j = i; j < S.length(); j++) {
                int index = S.charAt(j) - 'a';
                if (node.children[index] == null) break;

                node = node.children[index];
                if (node.isWord) {
                    dp[j + 1] = true;
                }
            }
        }

        return dp[S.length()];
    }
}

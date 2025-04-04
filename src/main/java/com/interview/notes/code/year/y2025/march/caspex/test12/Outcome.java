package com.interview.notes.code.year.y2025.march.caspex.test12;

import java.util.*;

//WORKING
public class Outcome {

    public static String solve(String S, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[S.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= S.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(S.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[S.length()] ? "true" : "false";
    }

    public static void main(String[] args) {
        List<TestCase> testCases = Arrays.asList(
            new TestCase("applepenapple", Arrays.asList("apple", "pen"), "true"),
            new TestCase("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"), "false"),
            new TestCase("leetcode", Arrays.asList("leet", "code"), "true"),
            new TestCase("aaaaaaa", Arrays.asList("aaaa", "aaa"), "true"),
            new TestCase("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                         Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"), "false")
        );

        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            String result = solve(tc.input, tc.dict);
            System.out.println("Test Case " + (i + 1) + ": " + (result.equals(tc.expected) ? "PASS" : "FAIL"));
        }
    }

    static class TestCase {
        String input;
        List<String> dict;
        String expected;

        TestCase(String input, List<String> dict, String expected) {
            this.input = input;
            this.dict = dict;
            this.expected = expected;
        }
    }
}

package com.interview.notes.code.year.y2025.may.common.test10;

import java.util.*;

/**
 * WordBreakSentences
 * Java 8 solution that returns all valid sentences formed by inserting spaces.
 */
public class WordBreakSentences {

    // *** Public API method ***
    public static List<String> wordBreak(String s, List<String> wordDict) {
        // Trim input to remove accidental leading/trailing spaces               // why: matches sample input
        String input = s.trim();

        // Convert list to HashSet for O(1) prefix look-ups                       // why: faster word checks
        Set<String> dict = new HashSet<>(wordDict);

        // Memoisation cache: startIndex -> sentences formed from that index      // why: avoids recomputation
        Map<Integer, List<String>> memo = new HashMap<>();

        // Kick off DFS from index 0                                              // why: build sentences for whole string
        return dfs(0, input, dict, memo);
    }

    // *** Recursive helper ***
    private static List<String> dfs(int start, String s, Set<String> dict,
                                    Map<Integer, List<String>> memo) {

        // Return cached result if we solved this suffix before                   // why: DP memoisation
        if (memo.containsKey(start)) return memo.get(start);

        List<String> res = new ArrayList<>();

        // Base case: reached end of string                                       // why: completed one valid sentence
        if (start == s.length()) {
            res.add("");                                                         // empty str to enable joining later
            return res;
        }

        // Explore every possible end position extending from 'start'
        for (int end = start + 1; end <= s.length(); end++) {
            String prefix = s.substring(start, end);

            // Check if current prefix is a dictionary word                       // why: only valid words allowed
            if (dict.contains(prefix)) {

                // Recursively solve the remainder                                // why: assemble complete sentence
                for (String sub : dfs(end, s, dict, memo)) {

                    // If suffix is empty, avoid extra space; else add space      // why: correct formatting
                    res.add(sub.isEmpty() ? prefix : prefix + " " + sub);
                }
            }
        }

        // Save result in memo before returning                                  // why: DP optimisation
        memo.put(start, res);
        return res;
    }

    // *** Minimal reproducible example & test harness (no JUnit) ***
    public static void main(String[] args) {

        // Sample Test 1
        runTest("stockgodown", Arrays.asList("stock", "go", "down", "money"),
                List.of("stock go down"));

        // Sample Test 2
        runTest("pineapplepenapple",
                Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"),
                Arrays.asList("pine apple pen apple",
                        "pineapple pen apple",
                        "pine applepen apple"));

        // Edge-case: no solution
        runTest("catsandog",
                Arrays.asList("cats", "dog", "sand", "and", "cat"),
                Collections.emptyList());

        // Stress test: long input of 25 'a's, dict = {"a", "aa", ... "aaaaa"}
        StringBuilder longStr = new StringBuilder();
        for (int i = 0; i < 25; i++) longStr.append('a');
        List<String> dict = new ArrayList<>();
        for (int len = 1; len <= 5; len++)
            dict.add(String.join("",
                    Collections.nCopies(len, "a")));
        // We only verify non-emptiness to avoid printing millions of sentences
        System.out.println("Stress-test result count = " +
                wordBreak(longStr.toString(), dict).size());
    }

    /**
     * Helper to execute one test and print PASS/FAIL with expected output.
     */
    private static void runTest(String s, List<String> dict, List<String> expected) {
        List<String> actual = wordBreak(s, dict);

        // Sort for comparison because order is not important                     // why: matches 'any order' rule
        Collections.sort(actual);
        Collections.sort(expected);

        boolean pass = actual.equals(expected);
        System.out.println("Input: \"" + s + "\" -> " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("Expected: " + expected);
            System.out.println("Actual  : " + actual);
        }
    }
}

package com.interview.notes.code.year.y2025.september.assesment.test1;

import java.util.*;
import java.util.stream.*;

public class WordBreakRunner {
    static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        List<String> words = wordDict.stream().distinct().collect(Collectors.toList());
        for (int i = 0; i < n; i++) {
            if (!dp[i]) continue;
            for (String w : words) {
                int end = i + w.length();
                if (end <= n && s.startsWith(w, i)) dp[end] = true;
            }
        }
        return dp[n];
    }

    static String repeat(char c, int times) {
        char[] a = new char[times];
        Arrays.fill(a, c);
        return new String(a);
    }

    static class TestCase {
        final String name;
        final String s;
        final List<String> dict;
        final boolean expected;
        TestCase(String name, String s, List<String> dict, boolean expected) {
            this.name = name; this.s = s; this.dict = dict; this.expected = expected;
        }
    }

    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>();

        tests.add(new TestCase(
                "Example-1",
                "applepenapple",
                Arrays.asList("apple", "pen"),
                true
        ));

        tests.add(new TestCase(
                "Example-2",
                "catsandog",
                Arrays.asList("cats", "dog", "sand", "and", "cat"),
                false
        ));

        tests.add(new TestCase(
                "LeetCode-Basic",
                "leetcode",
                Arrays.asList("leet", "code"),
                true
        ));

        tests.add(new TestCase(
                "Empty-String",
                "",
                Arrays.asList("a", "b"),
                true
        ));

        tests.add(new TestCase(
                "No-Dict",
                "abc",
                Collections.emptyList(),
                false
        ));

        tests.add(new TestCase(
                "Overlap-Choices",
                "pineapplepenapple",
                Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"),
                true
        ));

        String bigA = repeat('a', 100000);
        List<String> dictA = IntStream.rangeClosed(1, 10).mapToObj(i -> repeat('a', i)).collect(Collectors.toList());

        tests.add(new TestCase(
                "Large-True-100k-a",
                bigA,
                dictA,
                true
        ));

        tests.add(new TestCase(
                "Large-False-100k-a-plus-b",
                bigA + "b",
                dictA,
                false
        ));

        tests.add(new TestCase(
                "Edge-Single-Char",
                "aaaaab",
                Arrays.asList("a", "aa", "aaa"),
                false
        ));

        tests.add(new TestCase(
                "Edge-Reuse-Word",
                "aaaaaaa",
                Arrays.asList("aaaa", "aaa"),
                true
        ));

        int pass = 0;
        for (TestCase t : tests) {
            boolean got = wordBreak(t.s, t.dict);
            boolean ok = got == t.expected;
            if (ok) pass++;
            System.out.println(t.name + " | Expected: " + t.expected + " | Got: " + got + " | " + (ok ? "PASS" : "FAIL"));
        }
        System.out.println("Summary: " + pass + "/" + tests.size() + " PASS");
    }
}

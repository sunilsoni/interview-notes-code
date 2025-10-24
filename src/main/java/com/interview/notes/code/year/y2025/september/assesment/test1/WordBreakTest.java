package com.interview.notes.code.year.y2025.september.assesment.test1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordBreakTest { // main class containing solution and tests

    // dynamic programming solution to check if string can be segmented into dictionary words
    public static boolean wordBreak(String s, List<String> wordDict) { // method signature: input string and list of dictionary words
        Set<String> dict = new HashSet<>(wordDict); // convert list to HashSet for O(1) lookups
        int n = s.length(); // length of input string
        boolean[] dp = new boolean[n + 1]; // dp[i] means s[0..i-1] can be segmented
        dp[0] = true; // empty prefix is segmentable by definition
        int maxLen = wordDict.stream() // compute maximum word length to limit inner loop
                .mapToInt(String::length) // map words to their lengths
                .max() // get maximum length
                .orElse(0); // default 0 if dictionary empty

        for (int i = 1; i <= n; i++) { // iterate over prefix lengths from 1..n
            int start = Math.max(0, i - maxLen); // only check j in [i-maxLen, i)
            for (int j = start; j < i; j++) { // check possible split point j
                if (!dp[j]) { // if prefix up to j is not segmentable, skip
                    continue; // move to next j
                }
                // check if substring s[j..i-1] is a dictionary word
                if (dict.contains(s.substring(j, i))) { // substring lookup in set
                    dp[i] = true; // mark prefix up to i as segmentable
                    break; // break inner loop early
                }
            }
        }
        return dp[n]; // final answer whether full string is segmentable
    }

    // main method for running tests and printing PASS/FAIL results
    public static void main(String[] args) { // program entry point
        List<TestCase> tests = new ArrayList<>(); // list to collect test cases

        // Example 1: "applepenapple" -> true
        tests.add(new TestCase(
                "example-apple-pen",
                "applepenapple",
                Arrays.asList("apple", "pen"),
                true
        ));

        // Example 2: "catsandog" -> false
        tests.add(new TestCase(
                "example-cats-and-dog",
                "catsandog",
                Arrays.asList("cats", "dog", "sand", "and", "cat"),
                false
        ));

        // Edge case: empty string should be true (zero words)
        tests.add(new TestCase(
                "empty-string",
                "",
                Arrays.asList("a", "b"),
                true
        ));

        // Single character present in dict -> true
        tests.add(new TestCase(
                "single-char-true",
                "a",
                List.of("a"),
                true
        ));

        // Single character not present -> false
        tests.add(new TestCase(
                "single-char-false",
                "b",
                List.of("a"),
                false
        ));

        // Moderate case: repeated pattern with varying dictionary
        tests.add(new TestCase(
                "repeat-ab",
                "abababab",
                Arrays.asList("a", "b", "ab"),
                true
        ));

        // Large input stress test: long string of 'a' with small-word dictionary (maxLen small)
        int largeN = 200_000; // length of large test string chosen to stress performance but remain practical
        String largeA = IntStream.range(0, largeN) // create stream of indices 0..largeN-1
                .mapToObj(i -> "a") // map each index to "a"
                .collect(Collectors.joining()); // join them into one long string of 'a's

        // dictionary words "a", "aa", ..., up to length 10 to keep maxLen small and complexity O(n * maxLen)
        List<String> smallDict = IntStream.rangeClosed(1, 10) // 1..10
                .mapToObj(k -> String.join("", Collections.nCopies(k, "a"))) // create "a"*k
                .collect(Collectors.toList()); // collect to list

        tests.add(new TestCase(
                "large-all-a",
                largeA,
                smallDict,
                true
        ));

        // Another large negative test: long string ending with 'b' where dict only contains 'a' variations -> false
        String largeA_with_b = largeA + "b"; // append a 'b' to make it impossible to segment
        tests.add(new TestCase(
                "large-all-a-with-b",
                largeA_with_b,
                smallDict,
                false
        ));

        // run tests and collect results
        List<String> results = tests.stream() // stream over test cases
                .map(tc -> { // map each test case to a result string
                    boolean actual = wordBreak(tc.s, tc.dict); // compute actual result
                    String status = (actual == tc.expected) ? "PASS" : "FAIL"; // determine pass/fail
                    // prepare readable output for this test
                    return String.format("%s: %s (expected=%s, actual=%s, len=%d)",
                            tc.name, status, tc.expected, actual, tc.s.length());
                })
                .collect(Collectors.toList()); // collect all result strings

        // print each test result line by line
        results.forEach(System.out::println); // print results using method reference

        // print summary counts: number of passed tests and total tests
        long passCount = results.stream() // stream results
                .filter(line -> line.contains("PASS")) // filter passing lines
                .count(); // count them
        System.out.printf("Summary: Passed %d out of %d tests%n", passCount, tests.size()); // print summary
    }

    /**
     * @param name     test name
     * @param s        input string
     * @param dict     dictionary words
     * @param expected expected boolean result
     */ // simple container class for test cases
    record TestCase(String name, String s, List<String> dict,
                    boolean expected) { // holds input, dictionary, expected result and a name
        // constructor
        // assign test name
        // assign input string
        // assign dictionary list
        // assign expected result
    }
}
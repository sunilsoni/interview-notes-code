package com.interview.notes.code.year.y2025.may.common.test7;

import java.util.*; // import List, Arrays, Collections, etc.

public class LongestCommonPrefix {

    // Compute LCP of a list of strings using Java 8 Streams
    public static String longestCommonPrefix(List<String> strings) {
        return strings.stream()                           // create a Stream<String>
            .reduce((s1, s2) -> {                        // pairwise reduce to common prefix
                int minLen = Math.min(s1.length(), s2.length()); // avoid index overflow
                int i = 0;                              // index for matching chars
                // loop while within bounds AND chars match
                while (i < minLen && s1.charAt(i) == s2.charAt(i)) {
                    i++;                                // advance through matching prefix
                }
                return s1.substring(0, i);              // return the common prefix so far
            })
            .orElse("");                                 // empty list ⇒ empty string
    }

    // Simple main() to run PASS/FAIL tests (no JUnit)
    public static void main(String[] args) {
        // 1) Define test cases: (input list, expected LCP)
        List<TestCase> tests = Arrays.asList(
            new TestCase(Arrays.asList("flower","flow","flight"), "fl"),
            new TestCase(Arrays.asList("dog","racecar","car"), ""),
            new TestCase(Arrays.asList("interview","internet","internal"), "inte"),
            new TestCase(Arrays.asList("single"), "single"),
            new TestCase(Collections.emptyList(), ""),
            new TestCase(Arrays.asList("", "prefix"), ""),
            new TestCase(Arrays.asList("same","same","same"), "same")
        );

        // 2) Execute each test and print PASS or FAIL
        for (TestCase test : tests) {
            String result = longestCommonPrefix(test.words); // compute LCP
            if (result.equals(test.expected)) {
                System.out.println("PASS: " + test.words + " → \"" + result + "\"");
            } else {
                System.out.println("FAIL: " + test.words +
                    " → \"" + result + "\" (expected \"" + test.expected + "\")");
            }
        }

        // 3) Large-data performance check
        // build a 1,000-char prefix of 'a'
        String largePrefix = new String(new char[1000]).replace('\0', 'a');
        // create 100,000 copies of (prefix + "suffix")
        List<String> bigList = Collections.nCopies(100_000, largePrefix + "suffix");
        long start = System.currentTimeMillis();
        String prefix = longestCommonPrefix(bigList);      // should be the 1,000-char prefix
        long duration = System.currentTimeMillis() - start;
        System.out.println("Large-data test: prefix length=" + prefix.length() +
            ", time=" + duration + "ms");
    }

    // Helper to bundle each test case
    static class TestCase {
        List<String> words;
        String expected;
        TestCase(List<String> w, String e) { words = w; expected = e; }
    }
}
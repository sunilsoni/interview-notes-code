package com.interview.notes.code.year.y2025.June.common.test7;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class LongestUniqueSubstring {

    // --- Core logic wrapped in a utility method -------------------------
    private static int lengthOfLongestSubstring(String s) {
        // HashMap stores the latest position of each character we have seen so far
        Map<Character, Integer> lastSeen = new HashMap<>();
        // 'start' marks the left edge of our current window with unique characters
        final AtomicInteger[] start = {new AtomicInteger()};
        // 'maxLen' remembers the best window size found during the scan
        final int[] maxLen = {0};

        /*  Stream over all indices [0 .. s.length()-1] inclusive.
            We cannot use forEachOrdered on IntStream(range) because we need
            to mutate 'start', 'maxLen', and 'lastSeen' during iteration.
            This is still perfectly legal, though side-effects in streams
            should be used sparingly; here they give us concise Java-8 code.  */
        IntStream.range(0, s.length()).forEach(i -> {
            char c = s.charAt(i);                          // current character
            Integer prev = lastSeen.put(c, i);             // store / update index
            if (prev != null && prev >= start[0].get()) {           // duplicate inside window?
                start[0].set(prev + 1);                          // shrink window after last duplicate
            }
            int currentLen = i - start[0].get() + 1;                // window size = right - left + 1
            if (currentLen > maxLen[0]) maxLen[0] = currentLen;  // update best answer
        });

        return maxLen[0];
    }

    // --- Minimal reproducible example + manual test harness -------------
    public static void main(String[] args) {
        // Put every test case in this list: input, expected output
        List<String[]> tests = Arrays.asList(
            new String[]{"abcabcbb", "3"},
            new String[]{"bbbbb",     "1"},
            new String[]{"",          "0"},        // edge: empty
            new String[]{"abcdef",    "6"},        // edge: all unique
            new String[]{"pwwkew",    "3"}         // classic LeetCode case
        );

        // Run tests and print simple PASS / FAIL lines
        tests.forEach(t -> {
            String input = t[0];
            int expected = Integer.parseInt(t[1]);
            int actual   = lengthOfLongestSubstring(input);
            String status = (actual == expected) ? "PASS" : "FAIL";
            System.out.println(String.format(
                "Input: %-10s  Expected: %d  Actual: %d  -> %s",
                "\"" + input + "\"", expected, actual, status));
        });

        // Large-data sanity check: 100 000 unique chars (prints length only)
        int bigLen = 100_000;
        StringBuilder sb = new StringBuilder(bigLen);
        IntStream.range(0, bigLen).forEach(i -> sb.append((char) ('a' + (i % 26))));
        long start = System.nanoTime();
        int result = lengthOfLongestSubstring(sb.toString());
        long end   = System.nanoTime();
        System.out.println("Large input length: " + bigLen
                         + ", answer: " + result
                         + ", time: " + (end - start)/1_000_000 + " ms");
    }
}

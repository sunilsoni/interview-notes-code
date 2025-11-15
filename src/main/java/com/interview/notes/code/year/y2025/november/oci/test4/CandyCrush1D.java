package com.interview.notes.code.year.y2025.november.oci.test4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CandyCrush1D {

    /**
     * Removes groups of 3 or more same consecutive characters repeatedly
     * until no more crushes can be made.
     */
    public static String candyCrush(String ipStr) {
        // Repeat until the string stabilizes (no crush in a round)
        while (true) {
            StringBuilder sb = new StringBuilder(); // holds crushed result
            int i = 0;
            boolean crushed = false; // tracks if any crush happened this round

            // Traverse through characters
            while (i < ipStr.length()) {
                int j = i;

                // Move j ahead while the same character repeats
                while (j < ipStr.length() && ipStr.charAt(j) == ipStr.charAt(i)) {
                    j++;
                }

                int count = j - i; // length of this group

                if (count >= 3) {
                    crushed = true; // mark a crush occurred
                    // Skip adding these chars (they’re crushed)
                } else {
                    // keep chars that are not part of a 3+ sequence
                    sb.append(ipStr, i, j);
                }

                i = j; // move to next group
            }

            // If no crush happened in this round, string is stable → return it
            if (!crushed) {
                return sb.toString();
            }

            // Otherwise, repeat on the newly formed string
            ipStr = sb.toString();
        }
    }

    /**
     * Main test runner: executes multiple test cases and reports PASS/FAIL.
     */
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
                new TestCase("aaabbbacd", "acd"),   // full crush chain
                new TestCase("aabbbacd", "cd"),     // corrected per Candy Crush rule
                new TestCase("aaabbbccc", ""),      // all crushed
                new TestCase("abcdddcba", "abccba"),// middle crush only
                new TestCase("a", "a"),             // single char
                new TestCase("aaa", ""),            // single crush group
                new TestCase("aabbaaa", "aabb"),    // trailing crush
                new TestCase("aabbaaacccddd", "aabb"), // multiple chain
                new TestCase("bbbbabbccccd", "abbd"), // multiple chain
                new TestCase(generateLargeInput(), "") // stress test
        );

        // Iterate and print test results
        IntStream.range(0, tests.size()).forEach(i -> {
            TestCase test = tests.get(i);
            String result = candyCrush(test.input);
            boolean pass = result.equals(test.expected);

            System.out.println("Test " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("  Input:    " + test.input);
                System.out.println("  Expected: " + test.expected);
                System.out.println("  Got:      " + result);
            }
        });
    }

    /**
     * Generates large input string (100000 'a's) for performance testing.
     */
    static String generateLargeInput() {
        char[] arr = new char[100000];
        Arrays.fill(arr, 'a');
        return new String(arr); // Expected output is ""
    }

    /**
     * Simple helper class for holding test cases.
     */
    static class TestCase {
        String input;
        String expected;

        TestCase(String input, String expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}

package com.interview.notes.code.year.y2025.may.BNYM.test3;

import java.util.*;
import java.util.stream.IntStream;

public class LongestSubstringWithoutRepeating {

    /**
     *  lengthOfLongestSubstring:
     *  - This method uses a Java 8 IntStream to try every possible
     *    starting index i in the string, and asks lengthFromIndex()
     *    “how many characters can we go from i before hitting a repeat?”
     *  - Then it takes the maximum of all those lengths.
     *  - If the input is empty, it returns 0 by orElse(0).
     */
    public static int lengthOfLongestSubstring(String s) {
        // 1. Compute the string length.
        int n = s.length();

        // 2. Use IntStream.range(0, n) to produce a stream of start indices: 0,1,2,...,n-1
        //    For each i, we call lengthFromIndex(s, i), which returns length of maximal unique substring from i.
        //    Then we pick the max. If the stream is empty (n == 0), return 0 by orElse(0).
        return IntStream
                .range(0, n)                     // generate 0,1,...,n-1 as possible starts
                .map(i -> lengthFromIndex(s, i))// for each start i, find how far we can go without repeats
                .max()                           // pick the maximum length among all starts
                .orElse(0);                      // if string is empty, return 0
    }

    /**
     *  lengthFromIndex:
     *  - Starting at index 'start' in string s, expand forward one character at a time.
     *  - Keep a HashSet 'seen' of characters we've seen in this current substring.
     *  - As soon as we hit a character already in 'seen', we must stop and return the current length (j - start).
     *  - If we never hit a repeat until the end, return (n - start).
     */
    private static int lengthFromIndex(String s, int start) {
        // 1. Create a HashSet to track which characters we've encountered so far.
        Set<Character> seen = new HashSet<>();

        // 2. The total length of the string.
        int n = s.length();

        // 3. For j = start, start+1, start+2, ... up to n-1:
        for (int j = start; j < n; j++) {
            // 3.a. Get the character at position j.
            char c = s.charAt(j);

            // 3.b. If we've already seen 'c', we must stop; the substring [start .. j-1] was unique.
            if (seen.contains(c)) {
                // Return how many characters we had so far: j - start
                return j - start;
            }

            // 3.c. Otherwise, add 'c' to 'seen' and continue scanning.
            seen.add(c);
        }

        // 4. If we never saw a duplicate up to the end of s, the substring length is n - start.
        return n - start;
    }

    /**
     *  main method:
     *  - Contains some predefined test cases (small strings with known answers).
     *  - Also generates one large random test to show that our code handles bigger input without crash.
     *  - For each small test, we print PASS or FAIL based on expected vs. actual.
     */
    public static void main(String[] args) {
        // ====== PART 1: SMALL TEST CASES WITH EXPECTED RESULTS ======

        // We store inputs and expected outputs in two parallel arrays.
        String[] testInputs = {
            "",             // empty string
            "a",            // single char
            "abcabcbb",     // classic example → "abc"
            "bbbbb",        // all same → "b"
            "pwwkew",       // → "wke"
            "dvdf",         // tricky: "vdf" = 3
            "anviaj",       // tricky: "nviaj" = 5
            "abcde",        // all unique → length 5
            "abba",         // tricky: "ab" or "ba" = 2
            "abcbb"         // the example from the prompt → longest is "abc" = 3
        };
        int[] expected = {
            0,  // for ""
            1,  // for "a"
            3,  // for "abcabcbb"
            1,  // for "bbbbb"
            3,  // for "pwwkew"
            3,  // for "dvdf"
            5,  // for "anviaj"
            5,  // for "abcde"
            2,  // for "abba"
            3   // for "abcbb"
        };

        // Print a header for clarity.
        System.out.println("===== Small Test Cases =====");
        // Loop over all predefined tests.
        for (int i = 0; i < testInputs.length; i++) {
            String input = testInputs[i];              // the test string
            int exp = expected[i];                     // the expected result
            int actual = lengthOfLongestSubstring(input);// call our function

            // Print details and PASS/FAIL status.
            if (actual == exp) {
                System.out.printf("Test #%d: Input = \"%s\", Expected = %d, Got = %d → PASS%n",
                                  i+1, input, exp, actual);
            } else {
                System.out.printf("Test #%d: Input = \"%s\", Expected = %d, Got = %d → FAIL%n",
                                  i+1, input, exp, actual);
            }
        }

        // ====== PART 2: LARGE RANDOM TEST ======
        // We'll generate a random string of length 100000 using lowercase letters.
        // Then just call our method once and print how long it took and what it returned.
        System.out.println("\n===== Large Random Test =====");
        int largeN = 100_000;                       // size of random string
        Random rand = new Random(12345);            // fixed seed for reproducibility
        StringBuilder sb = new StringBuilder(largeN);
        for (int i = 0; i < largeN; i++) {
            // pick a random lowercase letter
            char c = (char) ('a' + rand.nextInt(26));
            sb.append(c);
        }
        String largeInput = sb.toString();

        long startTime = System.currentTimeMillis();
        int resultLarge = lengthOfLongestSubstring(largeInput);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Print the length we found and how long the computation took.
        System.out.printf("Large input length = %d, Result = %d, Time = %d ms%n",
                          largeN, resultLarge, duration);
    }
}
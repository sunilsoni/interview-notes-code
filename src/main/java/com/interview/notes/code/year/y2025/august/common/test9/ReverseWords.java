package com.interview.notes.code.year.y2025.august.common.test9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReverseWords {                  // main class
    /**
     * Reverse the order of words in the sentence, remove punctuation,
     * and convert to lowercase.
     */
    public static String reverseWords(String sentence) {
        // check for null or empty to avoid extra work or NPE
        if (sentence == null || sentence.isEmpty()) {
            return sentence;                 // return as-is for null or empty
        }
        // split on any sequence of non-word characters (removes punctuation)
        String[] words = sentence.split("\\W+");
        // process stream: filter out blanks, lowercase, collect to list
        List<String> processed = Arrays.stream(words)
                .filter(w -> !w.isEmpty())       // drop any empty strings
                .map(String::toLowerCase)        // convert each word to lowercase
                .collect(Collectors.toList());   // gather into a mutable List
        // reverse the list in-place to get last→first
        Collections.reverse(processed);
        // join back into one string with spaces
        return String.join(" ", processed);
    }

    /**
     * Simple main method to test reverseWords against several cases,
     * printing PASS or FAIL, plus a large-input performance check.
     */
    public static void main(String[] args) {
        // array of {input, expectedOutput} pairs
        String[][] tests = {
                {"England. Beats India.", "india beats england"},
                {"Hello World!", "world hello"},
                {"Single", "single"},
                {"", ""},
                {null, null}
        };
        // run each test
        for (int i = 0; i < tests.length; i++) {
            String input = tests[i][0];          // test input
            String expected = tests[i][1];          // expected output
            String actual = reverseWords(input);  // run method
            boolean pass = Objects.equals(expected, actual);
            // print result
            System.out.printf(
                    "Test %d: %s | expected='%s' actual='%s'%n",
                    i + 1,
                    pass ? "PASS" : "FAIL",
                    expected,
                    actual
            );
        }
        // performance test on a large input
        StringBuilder sb = new StringBuilder();     // build a big string
        for (int i = 0; i < 100_000; i++) {
            sb.append("Hello ");                    // repeat "Hello "
        }
        String largeInput = sb.toString().trim();   // remove trailing space
        long start = System.currentTimeMillis();    // start timer
        String largeOutput = reverseWords(largeInput);
        long duration = System.currentTimeMillis() - start; // compute duration
        // print how long it took and the length of the output
        System.out.printf(
                "Large input: %d ms, output length: %d%n",
                duration,
                largeOutput.length()
        );
    }
}
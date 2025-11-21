package com.interview.notes.code.year.y2025.august.common.test13;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Balanced angle-brackets with limited replacements.
 * Rule:
 * - Scan left to right.
 * - Keep `open` = unmatched '<' count.
 * - When we see '>', if open>0, pair it (open--).
 * Else, if we still have replacement budget, replace this '>' with "<>" (use++), which fixes the prefix.
 * Else fail immediately.
 * - At the end, string is balanced iff open==0 (leftover '<' cannot be fixed by replacements).
 * <p>
 * The program prints PASS/FAIL for multiple test suites, including large random data.
 */
public class BalancedExpressions {  // define class to contain solution and tests

    // ---------- SOLUTION API ----------

    public static List<Integer> balancedOrNot(List<String> expressions, List<Integer> maxReplacements) {
        // For each index i, call canBalance on expressions[i] with maxReplacements[i], map boolean to 1/0, collect as list
        return IntStream.range(0, expressions.size())                               // stream indices 0..n-1
                .mapToObj(i -> canBalance(expressions.get(i), maxReplacements.get(i)) ? 1 : 0) // boolean -> 1/0
                .collect(Collectors.toList());                                      // collect to List<Integer>
    }

    private static boolean canBalance(String expr, int maxr) {
        int open = 0;             // count of unmatched '<'
        int used = 0;             // replacements used so far
        for (char c : expr.toCharArray()) {               // iterate characters left->right
            if (c == '<') {                                // if we see an opening bracket
                open++;                                    // increase unmatched opens
            } else {                                       // c == '>'
                if (open > 0) {                            // if we have an open to pair with
                    open--;                                // consume one open
                } else if (used < maxr) {                  // otherwise we need to spend a replacement
                    used++;                                // spend one replacement: convert this '>' into "<>"
                    // net effect keeps open at 0 (the inserted '<' pairs with this '>'), so nothing else to do
                } else {                                   // no open and no replacements left
                    return false;                          // cannot keep prefix balanced -> fail immediately
                }
            }
        }
        return open == 0;           // success only if no leftover '<' remains
    }

    // ---------- TEST HARNESS (no JUnit, simple main) ----------

    public static void main(String[] args) {
        // Suite 1: Basic correctness on simple cases
        runTest("Suite 1 - Basics",
                Arrays.asList("<>", "<>", "<><", ">>", "<<>", "><><"),     // expressions
                Arrays.asList(0, 1, 2, 2, 2, 2),        // max replacements
                Arrays.asList(1, 1, 0, 1, 0, 0));       // expected (note: "<><" -> 0)

        // Suite 2: Example where a string needs exactly k replacements
        runTest("Suite 2 - Exact replacements",
                Arrays.asList(">", ">>", ">>>", "<<>>", ">><<"),           // expressions
                Arrays.asList(1, 2, 3, 0, 2),                // max replacements
                Arrays.asList(1, 1, 1, 1, 0));               // expected results

        // Suite 3: Already balanced or impossible because of leftover '<'
        runTest("Suite 3 - Balanced vs leftover opens",
                Arrays.asList("<><>", "<<><", "<<<>>>", "<<><>>", "<<>"),  // expressions
                Arrays.asList(0, 5, 0, 0, 10),     // max replacements
                Arrays.asList(1, 0, 1, 1, 0));     // expected results

        // Suite 4: Edge cases (empty, all opens, all closes)
        runTest("Suite 4 - Edge cases",
                Arrays.asList("", "<<<", ">>>", "><", "<>"),               // expressions
                Arrays.asList(0, 5, 2, 1, 0),                  // max replacements
                Arrays.asList(1, 0, 1, 1, 1));                 // expected results

        // Suite 5: Large random data to check performance and stability
        largeDataTest(200_000, 5); // generate 200k-length random string, allow up to 5 replacements
    }

    // Helper to run one test suite and print PASS/FAIL with timing
    private static void runTest(String title, List<String> expressions, List<Integer> maxReplacements, List<Integer> expected) {
        long start = System.nanoTime();                                              // start timer
        List<Integer> got = balancedOrNot(expressions, maxReplacements);             // run solution
        long end = System.nanoTime();                                                // end timer
        boolean pass = got.equals(expected);                                         // compare with expected
        System.out.println("\n" + title + " -> " + (pass ? "PASS" : "FAIL"));        // print status
        System.out.println("Expected: " + expected);                                  // show expected
        System.out.println("Got     : " + got);                                       // show actual
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);           // timing
    }

    // Generate a single large random expression and test it for performance
    private static void largeDataTest(int length, int maxr) {
        Random rnd = new Random(42);                         // fixed seed for reproducibility
        StringBuilder sb = new StringBuilder(length);        // build a long random string
        for (int i = 0; i < length; i++) {                   // loop length times
            sb.append(rnd.nextBoolean() ? '<' : '>');        // append '<' or '>' randomly
        }
        String expr = sb.toString();                         // finalize the string

        long start = System.nanoTime();                      // start timer
        boolean ok = canBalance(expr, maxr);                 // run the core function
        long end = System.nanoTime();                        // end timer

        System.out.println("\nSuite 5 - Large data (" + length + " chars) -> " + ("Computed"));
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0); // print millis
        // Note: we don't have a ground-truth expected for random input; objective here is speed/stability.
    }
}
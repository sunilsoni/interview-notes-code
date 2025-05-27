package com.interview.notes.code.year.y2025.may.amazon.test4;

import java.util.*;

/**
 * Amazon genome-mutation task.
 * Author: hand-written, no external libraries, Java 8 compatible.
 */
public class GenomeMutation {

    // ---------- Core logic ----------
    public static int findTime(String genome, char mutation) {
        int firstMut = genome.indexOf(mutation);
        if (firstMut == -1) {
            return 0;                 // no mutation => nothing ever vanishes
        }

        int maxTime = firstMut;       // letters before the first mutation
        int prevMut = firstMut;       // position of the last mutation we met

        for (int i = firstMut + 1; i < genome.length(); i++) {
            if (genome.charAt(i) == mutation) {
                int gap = i - prevMut - 1;   // normal letters between two mutations
                maxTime = Math.max(maxTime, gap + 1);
                prevMut = i;
            }
        }
        return maxTime;
    }

    // ---------- Simple test harness ----------
    private static class TestCase {
        String genome;
        char mutation;
        int expected;
        TestCase(String g, char m, int e) { genome = g; mutation = m; expected = e; }
    }

    public static void main(String[] args) {

        List<TestCase> tests = Arrays.asList(
            new TestCase("tamem",   'm', 2),  // sample 0
            new TestCase("momoz",   'm', 2),  // sample 1
            new TestCase("luvzliz", 'z', 3),  // visual example
            new TestCase("abc",     'm', 0),  // no mutation at all
            new TestCase("m",       'm', 0),  // single mutation, nothing to its left
            new TestCase("mm",      'm', 1),  // two adjoining mutations
            new TestCase("abababm", 'm', 6)   // long run before a single mutation
        );

        // extra large test: 100 000 'a' then one 'm'
        StringBuilder sb = new StringBuilder(100_001);
        for (int i = 0; i < 100_000; i++) sb.append('a');
        sb.append('m');
        tests.add(new TestCase(sb.toString(), 'm', 100_000));

        // run all tests
        long failures = tests.stream()
            .map(t -> {
                int got = findTime(t.genome, t.mutation);
                boolean pass = got == t.expected;
                System.out.printf("Test %-10s | expected=%d got=%d | %s%n",
                        shorten(t.genome), t.expected, got, pass ? "PASS" : "FAIL");
                return pass;
            })
            .filter(pass -> !pass)
            .count();

        System.out.println("\nSummary: " +
                (failures == 0 ? "all tests passed ✔" : failures + " test(s) failed ✖"));
    }

    // helper: trim long genomes for tidy console output
    private static String shorten(String s) {
        return s.length() <= 10 ? "\"" + s + "\"" : "\"" + s.substring(0, 7) + "...\"";
    }
}

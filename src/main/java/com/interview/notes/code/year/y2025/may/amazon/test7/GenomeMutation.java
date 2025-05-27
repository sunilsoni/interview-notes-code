package com.interview.notes.code.year.y2025.may.amazon.test7;

import java.util.*;
import java.util.stream.*;

public class GenomeMutation {

    public static int findTime(String genome, char mutation) {
        int n = genome.length();
        int wave = 0, maxTime = 0;

        for (int i = n - 1; i >= 0; i--) {
            char c = genome.charAt(i);
            if (c == mutation) {
                // if there was an ongoing wave, it stops here but we record its time
                if (wave > 0) {
                    maxTime = Math.max(maxTime, wave);
                }
                // start a new wave from this mutation
                wave = 1;
            } else if (wave > 0) {
                // this character gets removed in the current wave
                maxTime = Math.max(maxTime, wave);
                wave++;
            }
            // else: no wave, no change
        }

        return maxTime;
    }

    public static void main(String[] args) {
        class Test {
            final String genome;
            final char mut;
            final int expected;
            Test(String g, char m, int e) { genome = g; mut = m; expected = e; }
        }

        List<Test> tests = new ArrayList<>(Arrays.asList(
            new Test("tamem", 'm', 2),
            new Test("momoz", 'm', 2),
            new Test("luvzliz", 'z', 3),
            new Test("aaaaa", 'a', 4),    // every 'a' removes the one to its left
            new Test("xxxxx", 'm', 0),    // no mutation at all
            new Test("m", 'm', 0)         // single mutation, nothing to its left
        ));

        // large test: 100_000 a’s then one 'm' at the end → time = 100_000
        String largeGenome = Stream.generate(() -> "a").limit(100_000).collect(Collectors.joining()) + "m";
        tests.add(new Test(largeGenome, 'm', 100_000));

        int pass = 0;
        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int result = findTime(t.genome, t.mut);
            boolean ok = result == t.expected;
            System.out.printf("Test %2d: %s (got=%d, expected=%d)%n",
                              i + 1, ok ? "PASS" : "FAIL", result, t.expected);
            if (ok) pass++;
        }
        System.out.printf("%n%d/%d tests passed.%n", pass, tests.size());
    }
}

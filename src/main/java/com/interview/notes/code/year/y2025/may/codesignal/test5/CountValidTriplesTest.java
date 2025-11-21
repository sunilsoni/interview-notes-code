package com.interview.notes.code.year.y2025.may.codesignal.test5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class CountValidTriplesTest {

    // The solution method under test
    public static int[] solution(String[] queries, int diff) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = queries.length;
        int[] result = new int[n];
        long count = 0;
        for (int i = 0; i < n; i++) {
            String q = queries[i];
            int x = Integer.parseInt(q.substring(1));
            if (q.charAt(0) == '+') {
                // For each new x, any y = x - diff and z = x - 2*diff already present
                count += (long) freq.getOrDefault(x - diff, 0) * freq.getOrDefault(x - 2 * diff, 0);
                // And existing triples where x fills z or y position
                count += (long) freq.getOrDefault(x + diff, 0) * freq.getOrDefault(x + 2 * diff, 0);
                // Also y in middle
                count += (long) freq.getOrDefault(x + diff, 0) * freq.getOrDefault(x - diff, 0);
                freq.put(x, freq.getOrDefault(x, 0) + 1);
            } else {
                // Removing all occurrences of x: rebuild count naively
                freq.remove(x);
                count = 0;
                for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
                    int y = e.getKey();
                    long fy = e.getValue();
                    long fx = freq.getOrDefault(y + diff, 0);
                    long fz = freq.getOrDefault(y - diff, 0);
                    count += fx * fy * fz;
                }
            }
            result[i] = (int) count;
        }
        return result;
    }

    // Helper to compare arrays and print PASS/FAIL
    private static void runTest(String name, String[] queries, int diff, int[] expected) {
        int[] actual = solution(queries, diff);
        boolean ok = Arrays.equals(actual, expected);
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL"));
        if (!ok) {
            System.out.println("  expected: " + Arrays.toString(expected));
            System.out.println("  actual:   " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        // Example from prompt
        runTest("Example",
                new String[]{"+4", "+5", "+6", "+4", "+3", "-4"},
                1,
                new int[]{0, 0, 1, 2, 4, 0}
        );

        // Edge cases
        runTest("Single Add",
                new String[]{"+10"},
                5,
                new int[]{0}
        );
        runTest("Add and Remove",
                new String[]{"+1", "+2", "+3", "-2", "+4"},
                1,
                new int[]{0, 0, 1, 0, 0}
        );

        // Large random test for performance
        int N = 100_000;
        Random rnd = new Random(0);
        String[] bigQueries = IntStream.range(0, N)
                .mapToObj(i -> (rnd.nextBoolean() ? "+" : "-") + rnd.nextInt(1000))
                .toArray(String[]::new);
        // Ensure first few are adds so removals are valid
        for (int i = 0; i < 10; i++) bigQueries[i] = "+" + rnd.nextInt(1000);

        int diff = 1;
        long start = System.nanoTime();
        int[] bigResult = solution(bigQueries, diff);
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Large random test: done in " + ms + " ms");
    }
}
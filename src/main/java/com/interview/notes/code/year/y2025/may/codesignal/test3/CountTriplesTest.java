package com.interview.notes.code.year.y2025.may.codesignal.test3;

import java.util.*;

public class CountTriplesTest {

    // Core solution
    public static int[] solution(String[] queries, int diff) {
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        long count = 0;
        for (String q : queries) {
            int x = Integer.parseInt(q.substring(1));
            if (q.charAt(0) == '+') {
                // for new x, any y = x - diff and z = x - 2*diff
                count += (long) freq.getOrDefault(x - diff, 0)
                        * freq.getOrDefault(x - 2 * diff, 0);
                // also existing pairs (y,z) where y = x + diff, z = x
                // but removing all occurrences on '-' means no duplicates beyond freq
                freq.merge(x, 1, Integer::sum);
            } else {
                // remove all x: must subtract all triples that involved x
                int removed = freq.getOrDefault(x, 0);
                if (removed > 0) {
                    // subtract triples where x was the largest: x, y=x-diff, z=x-2*diff
                    count -= (long) removed
                            * freq.getOrDefault(x - diff, 0)
                            * freq.getOrDefault(x - 2 * diff, 0);
                    freq.remove(x);
                }
            }
            result.add((int) count);
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    // Test runner
    private static void runTest(String name, String[] queries, int diff, int[] expected) {
        int[] got = solution(queries, diff);
        boolean pass = Arrays.equals(got, expected);
        System.out.printf("%s: %s%n", name, pass ? "PASS" : "FAIL");
        if (!pass) {
            System.out.println("  Expected: " + Arrays.toString(expected));
            System.out.println("  Got     : " + Arrays.toString(got));
        }
    }

    public static void main(String[] args) {
        // 1) Provided example
        String[] q1 = {"+4", "+5", "+6", "+4", "+3", "-4"};
        int diff1 = 1;
        int[] exp1 = {0, 0, 1, 2, 4, 0};
        runTest("Example Test", q1, diff1, exp1);

        // 2) Edge tests
        runTest("Single Add", new String[]{"+1"}, 5, new int[]{0});
        runTest("Add & Remove Same", new String[]{"+2", "-2"}, 1, new int[]{0, 0});

        // 3) Large random test for performance
        int n = 10000;
        String[] bigQ = new String[n];
        int[] expBig = new int[n];
        for (int i = 0; i < n; i++) {
            bigQ[i] = "+" + (i + 1);
            expBig[i] = i < 2 ? 0 : i - 1;  // with diff=1, count = max(0, i-1)
        }
        long start = System.nanoTime();
        int[] gotBig = solution(bigQ, 1);
        long durationMs = (System.nanoTime() - start) / 1_000_000;
        boolean correct = Arrays.equals(gotBig, expBig);
        System.out.printf("Large Test (%d ops): %s in %d ms%n",
                n, correct ? "PASS" : "FAIL", durationMs);
    }
}

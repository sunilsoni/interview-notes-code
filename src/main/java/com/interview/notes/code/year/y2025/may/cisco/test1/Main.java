package com.interview.notes.code.year.y2025.may.cisco.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    // ---------- core logic ----------
    public static int funcDrop(int[] x, int[] y) {
        if (x == null || y == null || x.length != y.length) return 0;

        // count how many times each x appears
        Map<Integer, Long> freqX = Arrays.stream(x)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // count how many times each y appears
        Map<Integer, Long> freqY = Arrays.stream(y)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long bestX = freqX.values().stream().mapToLong(Long::longValue).max().orElse(0);
        long bestY = freqY.values().stream().mapToLong(Long::longValue).max().orElse(0);

        int answer = (int) Math.max(bestX, bestY);
        return answer > 1 ? answer : 0;   // path must connect >1 points
    }

    // ---------- quick helper to print arrays nicely ----------
    private static String arr(int[] a) {
        return Arrays.toString(a);
    }

    // ---------- test driver in plain main (no JUnit) ----------
    //  …same imports and funcDrop() as before …

    public static void main(String[] args) {

        class TC {
            int[] x, y;
            int expect;

            TC(int[] x, int[] y, int expect) {
                this.x = x;
                this.y = y;
                this.expect = expect;
            }
        }
        List<TC> tests = new ArrayList<>();

        tests.add(new TC(                    // 0 – sample
                new int[]{2, 3, 2, 4, 2},
                new int[]{2, 2, 6, 5, 8}, 3));

        tests.add(new TC(                    // 1 – all unique
                new int[]{1, 2, 3},
                new int[]{4, 5, 6}, 0));

        tests.add(new TC(                    // 2 – same x
                new int[]{7, 7, 7, 7, 7},
                new int[]{1, 2, 3, 4, 5}, 5));

        tests.add(new TC(                    // 3 – same y
                new int[]{1, 2, 3, 4},
                new int[]{9, 9, 9, 9}, 4));

        tests.add(new TC(                    // 4 – tie case, max=2
                new int[]{1, 1, 2, 2, 3},
                new int[]{5, 6, 6, 7, 7}, 2));   // ← fixed

        int n = 700;                         // 5 – large case, max=10
        int[] bigX = IntStream.range(0, n).map(i -> i / 10).toArray();
        int[] bigY = IntStream.range(0, n).map(i -> i / 7).toArray();
        tests.add(new TC(bigX, bigY, 10));   // ← fixed

        int id = 0, pass = 0;
        for (TC t : tests) {
            int got = funcDrop(t.x, t.y);
            boolean ok = got == t.expect;
            if (ok) pass++;
            System.out.printf("Test %d: expected=%d got=%d → %s%n",
                    id++, t.expect, got, ok ? "PASS" : "FAIL");
        }
        System.out.printf("%nSummary: %d / %d tests passed%n",
                pass, tests.size());
    }

}

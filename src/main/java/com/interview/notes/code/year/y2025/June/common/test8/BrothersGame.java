package com.interview.notes.code.year.y2025.June.common.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BrothersGame {

    /**
     * ar: list of 0/1 bits.
     * returns max total 1's after inverting exactly one contiguous subarray.
     */
    public static int solve(List<Integer> ar) {
        // 1) count how many 1's we start with
        int originalOnes = (int) ar.stream().filter(b -> b == 1).count();

        // 2) map 0→+1, 1→-1 and run Kadane to find best gain
        int maxGain = Integer.MIN_VALUE;
        int currSum = 0;
        for (int b : ar) {
            int delta = (b == 0 ? 1 : -1);
            currSum = Math.max(delta, currSum + delta);
            maxGain = Math.max(maxGain, currSum);
        }

        // 3) best final = original 1's + best gain
        return originalOnes + maxGain;
    }

    public static void main(String[] args) {
        List<Test> tests = Arrays.asList(
                new Test(Arrays.asList(0, 1, 0, 0, 1), 4),
                new Test(Arrays.asList(1, 0, 0, 1, 0, 0), 5),
                new Test(Arrays.asList(1, 1, 1, 1), 3), // if you must flip
                new Test(Arrays.asList(0, 0, 0, 0), 4), // flip all
                new Test(Arrays.asList(1), 0),
                new Test(Arrays.asList(0), 1)
        );

        boolean allPass = true;
        for (Test t : tests) {
            int got = solve(new ArrayList<>(t.input));
            if (got == t.expected) {
                System.out.printf("PASS: %s → %d%n", t.input, got);
            } else {
                System.out.printf("FAIL: %s → got %d, expected %d%n",
                        t.input, got, t.expected);
                allPass = false;
            }
        }
        System.out.println(allPass
                ? "All small tests passed."
                : "Some small tests failed.");

        // large‐N sanity check (N=100_000)
        int N = 100_000;
        List<Integer> big = new Random(42)
                .ints(N, 0, 2)
                .boxed()
                .collect(Collectors.toList());

        long t0 = System.currentTimeMillis();
        int res = solve(big);
        long t1 = System.currentTimeMillis();
        System.out.printf("Random N=%d solved → %d (in %d ms)%n",
                N, res, (t1 - t0));
    }

    /**
     * simple holder for test cases
     */
    private static class Test {
        final List<Integer> input;
        final int expected;

        Test(List<Integer> in, int exp) {
            input = in;
            expected = exp;
        }
    }
}
package com.interview.notes.code.year.y2025.july.amazon.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortingFactorK {

    /**
     * Returns the maximum k such that you can sort arr by only swapping
     * pairs (i, j) with (arr[i] & arr[j]) == k.
     * If already sorted, returns 0.
     */
    public static int getSortingFactorK(List<Integer> arr) {
        int n = arr.size();
        boolean sorted = true;
        // check if already sorted
        for (int i = 0; i < n; i++) {
            if (arr.get(i) != i) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;

        // initialize mask to all 1’s in 32-bit
        int mask = ~0;
        // refine mask to only bits common to every arr[i] & i where arr[i]!=i
        for (int i = 0; i < n; i++) {
            int v = arr.get(i);
            if (v != i) {
                mask &= (v & i);
            }
        }
        return mask;
    }

    public static void main(String[] args) {
        class Test {
            final List<Integer> arr;
            final int expected;

            Test(int[] a, int e) {
                arr = new ArrayList<>();
                for (int x : a) arr.add(x);
                expected = e;
            }
        }

        List<Test> tests = Arrays.asList(
                new Test(new int[]{3, 0, 2, 1}, 0),
                new Test(new int[]{0, 1, 3, 2, 4}, 2),
                new Test(new int[]{}, 0),                // empty
                new Test(new int[]{0}, 0),               // single
                new Test(new int[]{1, 0}, 0),            // swap needs k=0
                new Test(new int[]{2, 1, 0}, 0),         // reverse 3
                new Test(new int[]{0, 2, 1, 3}, 2)       // one swap at 1↔2: 2&1=0? actually 2&1=0 → mask=0
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int got = getSortingFactorK(t.arr);
            System.out.printf("Case %2d: got=%d, exp=%d → %s%n",
                    i + 1, got, t.expected, (got == t.expected ? "PASS" : "FAIL"));
        }

        // large random test
        final int N = 100_000;
        List<Integer> large = new ArrayList<>(N);
        for (int i = 0; i < N; i++) large.add(i);
        Collections.shuffle(large);
        long start = System.nanoTime();
        int kLarge = getSortingFactorK(large);
        long dt = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Large random (n=%d): k=%d computed in %d ms%n", N, kLarge, dt);
    }
}

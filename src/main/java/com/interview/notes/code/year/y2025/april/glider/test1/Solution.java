package com.interview.notes.code.year.y2025.april.glider.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    // compute how many times to add B to make ar a rising sequence
    public static int solve(int B, List<Integer> ar) {
        int times = 0;
        int prev = ar.get(0);
        for (int i = 1; i < ar.size(); i++) {
            int curr = ar.get(i);
            if (curr <= prev) {
                int req = prev + 1 - curr;                 // how much we need to exceed prev
                int adds = (req + B - 1) / B;              // number of B-adds (ceiling)
                times += adds;
                curr += adds * B;                          // new value after adds
            }
            prev = curr;
        }
        return times;
    }

    // simple main to run tests and report PASS/FAIL, plus a large random case
    public static void main(String[] args) {
        List<Test> tests = Arrays.asList(
                new Test(2, Arrays.asList(1, 5, 5, 4), 3),
                new Test(1, Arrays.asList(1, 1), 1),
                new Test(2, Arrays.asList(1, 3, 3, 2), 3),
                new Test(2, Arrays.asList(1, 2, 3), 0)
        );

        for (Test t : tests) {
            int result = solve(t.B, t.ar);
            String status = result == t.expected ? "PASS" : "FAIL";
            System.out.printf("B=%d, ar=%s -> got %d, exp %d: %s%n",
                    t.B, t.ar, result, t.expected, status);
        }

        // large random test to check performance
        int N = 2000;
        Random rnd = new Random();
        List<Integer> large = IntStream.range(0, N)
                .map(i -> rnd.nextInt(1_000_000) + 1)
                .boxed()
                .collect(Collectors.toList());

        long start = System.nanoTime();
        solve(1_000_000, large);
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Large test time: " + ms + " ms");
    }

    // helper class for tests
    static class Test {
        int B, expected;
        List<Integer> ar;

        Test(int B, List<Integer> ar, int expected) {
            this.B = B;
            this.ar = ar;
            this.expected = expected;
        }
    }
}
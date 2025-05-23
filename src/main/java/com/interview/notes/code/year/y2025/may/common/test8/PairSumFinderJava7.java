package com.interview.notes.code.year.y2025.may.common.test8;

import java.util.*;

public class PairSumFinderJava7 {

    // Finds distinct pairs summing to target in one pass
    public static List<Pair> findPairs(int[] arr, int target) {
        Set<Integer> seen = new HashSet<Integer>();
        Set<Pair> result = new HashSet<Pair>();

        for (int x : arr) {
            int comp = target - x;              // complement needed to reach target
            if (seen.contains(comp)) {
                int small = Math.min(x, comp);
                int large = Math.max(x, comp);
                result.add(new Pair(small, large));
            }
            seen.add(x);                        // mark this number as seen
        }

        return new ArrayList<Pair>(result);
    }

    // Runs a single test and prints PASS/FAIL
    public static void runTest(int[] arr, int target, Set<Pair> expected) {
        List<Pair> found = findPairs(arr, target);
        Set<Pair> foundSet = new HashSet<Pair>(found);

        boolean pass = foundSet.equals(expected);
        System.out.printf("Target=%d, Size=%d: %s%n",
                target, arr.length, pass ? "PASS" : "FAIL");
        if (!pass) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Found   : " + foundSet);
        }
    }

    public static void main(String[] args) {
        // Test 1: given example
        runTest(
                new int[]{72, 8, 15, 63, 17, 10, 70, 1, 2, 3, 8},
                80,
                new HashSet<Pair>(Arrays.asList(
                        new Pair(8, 72),
                        new Pair(17, 63),
                        new Pair(10, 70)
                ))
        );

        // Test 2: same-value pair
        runTest(
                new int[]{5, 5, 5},
                10,
                new HashSet<Pair>(Collections.singletonList(
                        new Pair(5, 5)
                ))
        );

        // Test 3: no pairs
        runTest(
                new int[]{1, 2, 3},
                7,
                new HashSet<Pair>()
        );

        // Test 4: empty array
        runTest(
                new int[]{},
                5,
                new HashSet<Pair>()
        );

        // Test 5: large input of zeros
        int n = 1_000_000;
        int[] large = new int[n];
        Arrays.fill(large, 0);
        runTest(
                large,
                0,
                new HashSet<Pair>(Collections.singletonList(
                        new Pair(0, 0)
                ))
        );
    }

    // Simple Pair class to hold two integers and support equality
    static class Pair {
        final int first;
        final int second;

        Pair(int a, int b) {
            this.first = a;
            this.second = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return first == p.first && second == p.second;
        }

        @Override
        public int hashCode() {
            return 31 * first + second;
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }
}
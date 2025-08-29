package com.interview.notes.code.year.y2025.august.HackerRank.test3;

import java.util.*;

public class SprintBrute {

    // Brute‐force version: simply walk every segment
    public static int getMostVisitedBrute(int n, List<Integer> sprints) {
        // visits[i] = how many times marker i was visited
        int[] visits = new int[n + 1];

        // For each sprint from sprints[i-1] to sprints[i]
        for (int i = 1; i < sprints.size(); i++) {
            int a = sprints.get(i - 1);
            int b = sprints.get(i);

            if (a <= b) {
                // move forwards
                for (int pos = a; pos <= b; pos++) {
                    visits[pos]++;                // count each visit
                }
            } else {
                // move backwards
                for (int pos = a; pos >= b; pos--) {
                    visits[pos]++;                // count each visit
                }
            }
        }

        // Find the marker with highest visits (tie → lowest index)
        int bestIdx = 1;
        for (int i = 2; i <= n; i++) {
            if (visits[i] > visits[bestIdx]) {
                bestIdx = i;
            }
        }
        return bestIdx;
    }

    // Simple main() to test brute‐force version on small cases
    public static void main(String[] args) {
        // a few quick tests
        List<Test> tests = Arrays.asList(
            new Test(10, Arrays.asList(1,5,10,3), 5),
            new Test(5,  Arrays.asList(1,5),       1),
            new Test(9,  Arrays.asList(9,7,3,1),   3)
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int got = getMostVisitedBrute(t.n, t.sprints);
            System.out.printf("Test %d: %s (got %d, exp %d)%n",
                              i,
                              got == t.expected ? "PASS" : "FAIL",
                              got, t.expected);
        }
    }

    // Helper class for our small manual tests
    static class Test {
        int n, expected;
        List<Integer> sprints;
        Test(int n, List<Integer> s, int e) {
            this.n = n; this.sprints = s; this.expected = e;
        }
    }
}
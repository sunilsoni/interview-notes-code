package com.interview.notes.code.year.y2025.august.HackerRank.test4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SprintTraining {

    /**
     * Compute the lowest‐numbered marker that Pat visits most often.
     */
    public static int getMostVisited(int n, List<Integer> sprints) {
        // Create an array of length n+2 to hold our difference updates.
        // We use n+2 so that visits[b+1] is always valid when b == n.
        long[] diff = new long[n + 2];

        // Apply the difference-array updates for each sprint segment.
        for (int i = 1; i < sprints.size(); i++) {
            // a = starting marker of this sprint
            int a = sprints.get(i - 1);
            // b = ending marker of this sprint
            int b = sprints.get(i);
            // ensure a <= b by swapping if needed
            int start = Math.min(a, b);
            int end = Math.max(a, b);
            diff[start] += 1;      // every marker from start..end gets +1 visit
            diff[end + 1] -= 1;    // marker end+1 undoes that visit count
        }

        // Build the prefix sums so that visits[i] = total visits at marker i
        long[] visits = new long[n + 1];
        long running = 0;
        for (int i = 1; i <= n; i++) {
            running += diff[i];    // accumulate the difference
            visits[i] = running;   // record the actual visit count
        }

        // Find the maximum visit count
        long maxCount = IntStream.rangeClosed(1, n)
                .mapToLong(i -> visits[i])
                .max()
                .orElse(0);

        // Return the smallest index whose visit count equals maxCount
        return IntStream.rangeClosed(1, n)
                .filter(i -> visits[i] == maxCount)
                .findFirst()
                .orElse(1);
    }

    /**
     * A simple test harness in main() to check PASS/FAIL for each case,
     * including a large-data test to confirm performance.
     */
    public static void main(String[] args) {
        // Define our test cases: each is (n, sprints, expectedResult).
        class TestCase {
            int n;
            List<Integer> sprints;
            int expected;

            TestCase(int n, List<Integer> s, int e) {
                this.n = n;
                this.sprints = s;
                this.expected = e;
            }
        }

        List<TestCase> tests = Arrays.asList(
                // Sample Case 0
                new TestCase(10, Arrays.asList(1, 5, 10, 3), 5),
                // Sample Case 1
                new TestCase(5, Arrays.asList(1, 5), 1),
                // Sample Case 2
                new TestCase(9, Arrays.asList(9, 7, 3, 1), 3),
                // Edge: all sprints are back‐and‐forth between 1 and 2
                new TestCase(100000,
                        IntStream.range(0, 100000)
                                .map(i -> i % 2 == 0 ? 1 : 2)
                                .boxed()
                                .collect(Collectors.toList()),
                        1)
        );

        // Run each test and print PASS or FAIL
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            int result = getMostVisited(tc.n, tc.sprints);
            String status = (result == tc.expected) ? "PASS" : "FAIL";
            System.out.printf("Test %d: %s (got %d, expected %d)%n",
                    i, status, result, tc.expected);
        }
    }
}
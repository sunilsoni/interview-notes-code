package com.interview.notes.code.year.y2025.july.codesignal.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PackageDistributionNetwork {

    public static int solution(int[] centerCapacities, String[] dailyLog) {
        int n = centerCapacities.length;
        int[] maxCap = Arrays.copyOf(centerCapacities, n);
        int[] curCap = Arrays.copyOf(centerCapacities, n);
        int[] processed = new int[n];
        boolean[] closed = new boolean[n];

        int pos = 0; // current center pointer (will always find next open)
        int openCount = n;

        for (String log : dailyLog) {
            if (log.startsWith("CLOSURE")) {
                int idx = Integer.parseInt(log.split(" ")[1]);
                if (!closed[idx]) {
                    closed[idx] = true;
                    openCount--;
                }
                continue;
            }

            // PACKAGE
            if (openCount == 0) break; // no open centers left (theoretical guard)
            // Find next open center with capacity
            int steps = 0;
            while ((closed[pos] || curCap[pos] == 0) && steps < n) {
                pos = (pos + 1) % n;
                steps++;
            }

            // Now at next open center with available cap
            processed[pos]++;
            curCap[pos]--;

            // Prepare for next package: advance pointer (but don't advance if we're at 0 capacity, as above)
            int tmp = pos;
            pos = (pos + 1) % n;

            // Check if this was the end of a rotation (all open centers are at 0 cap)
            boolean rotationEnd = IntStream.range(0, n)
                    .filter(i -> !closed[i])
                    .allMatch(i -> curCap[i] == 0);

            if (rotationEnd) {
                for (int i = 0; i < n; i++)
                    if (!closed[i])
                        curCap[i] = maxCap[i];
            }
        }

        // Find max processed count, prefer highest index in case of tie
        int max = Arrays.stream(processed).max().orElse(0);
        int ans = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (processed[i] == max) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    // Simple main method for tests
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
                new TestCase(
                        new int[]{1, 2, 1, 2, 1},
                        new String[]{
                                "PACKAGE", "PACKAGE", "CLOSURE 2", "PACKAGE",
                                "CLOSURE 3", "PACKAGE", "PACKAGE"
                        },
                        1
                ),
                // Test: All centers same cap, many packages, no closure
                new TestCase(
                        new int[]{2, 2, 2},
                        new String[]{
                                "PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE"
                        },
                        2 // all get 3, prefer 2
                ),
                // Test: all centers but one are closed early
                new TestCase(
                        new int[]{2, 1, 1},
                        new String[]{
                                "PACKAGE", "CLOSURE 1", "CLOSURE 2", "PACKAGE", "PACKAGE", "PACKAGE"
                        },
                        0 // only center 0 processes all
                ),
                // Test: Large, no closure
                new TestCase(
                        new int[100],
                        IntStream.range(0, 1000).mapToObj(i -> "PACKAGE").toArray(String[]::new),
                        99
                ),
                // Test: Large, close all but last
                new TestCase(
                        new int[100],
                        Stream.concat(
                                IntStream.range(0, 99).mapToObj(i -> "CLOSURE " + i),
                                IntStream.range(0, 1000).mapToObj(i -> "PACKAGE")
                        ).toArray(String[]::new),
                        99
                ),
                // Test: Only one center, mixed logs
                new TestCase(
                        new int[]{5},
                        new String[]{"PACKAGE", "PACKAGE", "PACKAGE", "CLOSURE 0", "PACKAGE"},
                        0
                )
        );

        // Fill in capacities for large test
        for (TestCase t : tests) {
            if (t.centerCapacities.length > 1 && Arrays.stream(t.centerCapacities).sum() == 0)
                Arrays.fill(t.centerCapacities, 1 + (t.expected % 5));
        }

        int passCount = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase test = tests.get(i);
            int result = solution(test.centerCapacities, test.dailyLog);
            boolean pass = result == test.expected;
            System.out.println("Test #" + (i + 1) + ": " + (pass ? "PASS" : "FAIL")
                    + " (Expected: " + test.expected + ", Got: " + result + ")");
            if (pass) passCount++;
        }
        System.out.println("Total Passed: " + passCount + "/" + tests.size());
    }

    // Test case class
    static class TestCase {
        int[] centerCapacities;
        String[] dailyLog;
        int expected;

        TestCase(int[] c, String[] d, int e) {
            this.centerCapacities = c;
            this.dailyLog = d;
            this.expected = e;
        }
    }
}

package com.interview.notes.code.year.y2025.july.codesignal.test3;

import java.util.Arrays;
import java.util.List;

public class DistributionCenters {

    public static int solution(int[] centerCapacities, String[] dailyLog) {
        int n = centerCapacities.length;
        // how many packages each center actually processed
        int[] processed = new int[n];
        // current remaining capacity before reset
        int[] remaining = Arrays.copyOf(centerCapacities, n);
        // track closed centers
        boolean[] closed = new boolean[n];

        int current = 0;      // where we’ll try to send the next package
        // loop through each event in the log
        for (String event : dailyLog) {
            if (event.startsWith("PACKAGE")) {
                // find the next open center with remaining > 0
                while (true) {
                    // if we wrapped past the last center → reset all open centers
                    if (current == n) {
                        current = 0;
                        for (int i = 0; i < n; i++) {
                            if (!closed[i]) {
                                remaining[i] = centerCapacities[i];
                            }
                        }
                    }
                    // skip closed or full centers
                    if (!closed[current] && remaining[current] > 0) {
                        // assign package here
                        processed[current]++;
                        remaining[current]--;
                        // if that center is now full, advance pointer for next time
                        if (remaining[current] == 0) {
                            current++;
                        }
                        break;
                    } else {
                        current++;
                    }
                }
            } else if (event.startsWith("CLOSURE")) {
                // mark the given center closed
                int idx = Integer.parseInt(event.split("\\s+")[1]);
                closed[idx] = true;
                // if we just closed the center we're pointing at, 
                // leave current where it is – the PACKAGE logic will skip it next time
            }
        }

        // find which center processed the most (tie → pick highest index)
        int bestIdx = 0, bestCount = processed[0];
        for (int i = 1; i < n; i++) {
            if (processed[i] >= bestCount) {
                bestCount = processed[i];
                bestIdx = i;
            }
        }
        return bestIdx;
    }

    public static void main(String[] args) {
        List<Test> tests = Arrays.asList(
                new Test(
                        new int[]{1, 2, 1, 2, 1},
                        new String[]{
                                "PACKAGE",
                                "PACKAGE",
                                "CLOSURE 2",
                                "PACKAGE",
                                "CLOSURE 3",
                                "PACKAGE",
                                "PACKAGE"
                        },
                        1   // from prompt example
                )
        );

        for (Test t : tests) {
            int out = solution(t.caps, t.log);
            if (out == t.expect) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL: expected="
                        + t.expect + " got=" + out);
            }
        }
    }

    // simple test harness
    private static class Test {
        int[] caps;
        String[] log;
        int expect;

        Test(int[] c, String[] l, int e) {
            caps = c;
            log = l;
            expect = e;
        }
    }
}
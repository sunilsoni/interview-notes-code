package com.interview.notes.code.year.y2025.may.ibm.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinZeroSegmentOperations {
    // Returns minimum operations to eliminate exactly m-length zero segments
    public static int getMinOperations(String s, int m, int k) {
        List<Integer> starts = new ArrayList<>();
        int zeroCount = 0;
        int n = s.length();
        // Identify blocks exactly length m
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            } else {
                if (zeroCount == m) {
                    starts.add(i - m);
                }
                zeroCount = 0;
            }
        }
        // Trailing block
        if (zeroCount == m) {
            starts.add(n - m);
        }
        // Greedy cover these intervals [start, start+m-1] with length-k flips
        int ops = 0;
        int lastCoverEnd = -1;
        for (int st : starts) {
            int blockEnd = st + m - 1;
            if (blockEnd <= lastCoverEnd) continue;
            // Place flip window as far right as possible to cover block
            int flipStart = blockEnd - k + 1;
            if (flipStart < 0) flipStart = 0;
            int flipEnd = flipStart + k - 1;
            lastCoverEnd = flipEnd;
            ops++;
        }
        return ops;
    }

    // Simple main for testing
    public static void main(String[] args) {
        class Test {
            String s;
            int m, k, expected;
        }
        List<Test> tests = Arrays.asList(
                new Test() {{
                    s = "000000";
                    m = 3;
                    k = 2;
                    expected = 1;
                }},
                new Test() {{
                    s = "10101";
                    m = 1;
                    k = 1;
                    expected = 2;
                }},
                new Test() {{
                    s = "10101";
                    m = 2;
                    k = 3;
                    expected = 0;
                }},
                new Test() {{
                    s = "000";
                    m = 3;
                    k = 3;
                    expected = 1;
                }},
                new Test() {{
                    s = "0000";
                    m = 3;
                    k = 1;
                    expected = 1;
                }},
                new Test() {{
                    s = "11111";
                    m = 1;
                    k = 2;
                    expected = 0;
                }},
                new Test() {{
                    s = "000000000110011100";
                    m = 2;
                    k = 7;
                    expected = 2;
                }}
        );

        System.out.println("Running predefined tests...");
        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int result = getMinOperations(t.s, t.m, t.k);
            String status = (result == t.expected) ? "PASS" : "FAIL";
            System.out.printf("Test %d: %s (got %d, expected %d)\n", i, status, result, t.expected);
        }
    }
}

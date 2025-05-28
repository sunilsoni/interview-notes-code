package com.interview.notes.code.year.y2025.may.ibm.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MinZeroSegmentOperations {
    // Returns minimum operations to eliminate exactly m-length zero segments
    public static int getMinOperations(String s, int m, int k) {
        int n = s.length();
        int ops = 0;
        int zeroCount = 0;
        int blockThreshold = m + k - 1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            } else {
                if (zeroCount >= m) {
                    int forbiddenCount = zeroCount - m + 1;
                    ops += (forbiddenCount + blockThreshold - 1) / blockThreshold;
                }
                zeroCount = 0;
            }
        }
        // Handle trailing zero block
        if (zeroCount >= m) {
            int forbiddenCount = zeroCount - m + 1;
            ops += (forbiddenCount + blockThreshold - 1) / blockThreshold;
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
                }}
        );

        System.out.println("Running predefined tests...");
        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int result = getMinOperations(t.s, t.m, t.k);
            String status = (result == t.expected) ? "PASS" : "FAIL";
            System.out.printf("Test %d: %s (got %d, expected %d)\n", i, status, result, t.expected);
        }

        // Large test for performance
        System.out.println("\nRunning large performance test...");
        int n = 200_000;
        // single zero block
        StringBuilder sb = new StringBuilder(n);
        IntStream.range(0, n).forEach(i -> sb.append('0'));
        String large = sb.toString();
        long startTime = System.currentTimeMillis();
        int ops = getMinOperations(large, 1000, 100);
        long duration = System.currentTimeMillis() - startTime;
        System.out.printf("Large test: n=%d, m=1000, k=100 -> ops=%d, time=%dms\n",
                n, ops, duration);
    }
}

package com.interview.notes.code.year.y2025.may.ibm.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinOperationsToRemoveZeroSegments {

    /**
     * Main logic to calculate minimum operations.
     */
    public static int getMinOperations(String s, int m, int k) {
        int n = s.length();
        int ans = 0;
        char[] arr = s.toCharArray();
        int i = 0;

        while (i < n) {
            if (arr[i] == '0') {
                int start = i;
                // Move to end of current zero segment
                while (i < n && arr[i] == '0') i++;
                int len = i - start;
                // Process all segments of exactly m zeroes in this run
                if (len >= m) {
                    // For every possible segment of exactly m zeroes
                    for (int j = start; j + m <= i; j++) {
                        // Isolated segment check: must not be part of a longer zero-run
                        boolean isExact = (j == start || arr[j - 1] == '1') &&
                                (j + m == i || arr[j + m] == '1');
                        if (isExact) {
                            // One operation: flip k chars starting at j (covering at least part of m-segment)
                            ans++;
                            // To avoid overlapping, skip next k chars
                            int endToFlip = Math.min(n, j + k);
                            // Set the flipped range to '1' so it doesn't get re-counted
                            for (int x = j; x < endToFlip; x++) arr[x] = '1';
                            break; // after flipping, this segment can't form another exact m segment
                        }
                    }
                }
            } else {
                i++;
            }
        }
        return ans;
    }

    /**
     * Test runner for sample and edge test cases.
     */
    public static void main(String[] args) {
        // List of test cases: {s, m, k, expected}
        List<Object[]> tests = Arrays.asList(
                new Object[]{"000000", 3, 2, 1},
                new Object[]{"10101", 1, 1, 2},
                new Object[]{"10101", 2, 3, 0},
                new Object[]{"1111", 1, 2, 0},
                new Object[]{"00000", 2, 1, 2},
                new Object[]{"00000", 2, 3, 1},
                new Object[]{"000000", 2, 2, 2},
                // Large test cases
                new Object[]{repeat("0", 200000), 4, 5, 0}, // only one segment of length 200000, no exact 4
                new Object[]{IntStream.range(0, 200000).mapToObj(i -> i % 10 == 0 ? "1" : "0").collect(Collectors.joining()), 3, 2, 0}, // no exact 3
                new Object[]{repeat("1", 199999) + "0", 1, 1, 1} // only last char is 0
        );

        int idx = 1;
        for (Object[] t : tests) {
            String s = (String) t[0];
            int m = (int) t[1];
            int k = (int) t[2];
            int expected = (int) t[3];
            int actual = getMinOperations(s, m, k);
            if (actual == expected) {
                System.out.println("Test " + idx + ": PASS");
            } else {
                System.out.println("Test " + idx + ": FAIL | Expected: " + expected + " Got: " + actual +
                        " | s=" + (s.length() > 30 ? s.substring(0, 27) + "..." : s) + " m=" + m + " k=" + k);
            }
            idx++;
        }
    }

    // Helper to repeat a string
    public static String repeat(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }
}

package com.interview.notes.code.year.y2025.march.tiktok.test1;

import java.util.Arrays;

public class Solution {

    // 1) We define a maximum size based on the problem constraints (x + y <= 10000).
    //    But in this example, we use a smaller precomputation for clarity. If you need
    //    more than 5000 + 5000, you might adapt memory usage or logic for partial results.
    private static final int MAX = 10000;
    private static long[][] binomial = new long[MAX + 1][MAX + 1];

    static {
        // 2) Precompute all binomial coefficients up to (MAX, MAX).
        //    We'll clamp values above 10^18 to prevent overflow (long limit).
        for (int n = 0; n <= MAX; n++) {
            binomial[n][0] = 1L;  // C(n, 0) = 1
            for (int r = 1; r <= n; r++) {
                // Compute: C(n, r) = C(n-1, r-1) + C(n-1, r)
                long val = binomial[n - 1][r - 1] + binomial[n - 1][r];
                // Clamp to something above 10^18 so we don't overflow.
                if (val < 0 || val > 1000000000000000000L) {
                    val = 1000000000000000000L; // sentinel to indicate ">= 1e18"
                }
                binomial[n][r] = val;
            }
        }
    }

    /**
     * Decompress the video to get the k-th lexicographically smallest
     * binary string with x zeroes and y ones.
     *
     * @param x number of zeroes
     * @param y number of ones
     * @param k lexicographic rank (1-based)
     * @return k-th smallest binary string
     */
    public static String decompressVideo(int x, int y, long k) {
        // Use a StringBuilder for efficient string building
        StringBuilder result = new StringBuilder(x + y);

        // Build each character left to right
        while (x > 0 && y > 0) {
            // 3) Calculate how many sequences begin with '0' if we place '0' next
            long countIfZeroFirst = binomial[x + y - 1][x - 1];

            if (countIfZeroFirst >= k) {
                // 4) If that count is >= k, then our desired sequence is among these
                //    that start with '0'. Place '0' and decrement x.
                result.append('0');
                x--;
            } else {
                // 5) Otherwise, skip these 'countIfZeroFirst' combinations,
                //    place '1' instead, decrement y and adjust k.
                result.append('1');
                y--;
                k -= countIfZeroFirst;
            }
        }

        // 6) If any zeroes or ones remain, append them all (only one of these loops will run)
        while (x-- > 0) {
            result.append('0');
        }
        while (y-- > 0) {
            result.append('1');
        }

        return result.toString();
    }

    /**
     * Minimal reproducible example with testing approach.
     */
    public static void main(String[] args) {
        // We'll run a small set of sample tests.
        // For real testing, you'd add many more, including edge cases.

        // 1) Simple known test: x=2, y=2, k=3 -> from sample: "0110"
        testDecompressVideo(2, 2, 3, "0110");

        // 2) Another sample: x=3, y=4, k=35 -> expected: "1111000"
        testDecompressVideo(3, 4, 35, "1111000");

        // 3) Edge case: minimal k
        testDecompressVideo(1, 1, 1, "01"); // smallest among 2 zeros+ones => "01"
        testDecompressVideo(1, 1, 2, "10"); // largest => "10"

        // 4) Another check: x=3, y=4, k=2 -> "0010111" from problem example
        testDecompressVideo(3, 4, 2, "0010111");
    }

    /**
     * Helper method to print Pass/Fail for each test.
     * @param x #zeroes
     * @param y #ones
     * @param k rank
     * @param expected the expected string
     */
    private static void testDecompressVideo(int x, int y, long k, String expected) {
        String actual = decompressVideo(x, y, k);
        boolean pass = actual.equals(expected);
        System.out.printf("Test (x=%d, y=%d, k=%d): expected=%s, got=%s -> %s%n",
                x, y, k, expected, actual, (pass ? "PASS" : "FAIL"));
    }
}

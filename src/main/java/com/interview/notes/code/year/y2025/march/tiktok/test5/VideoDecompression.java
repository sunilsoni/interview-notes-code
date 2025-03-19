package com.interview.notes.code.year.y2025.march.tiktok.test5;

import java.math.BigInteger;

public class VideoDecompression {

    // Precompute binomial coefficients using Pascal's Triangle
    private static BigInteger[][] binomial;

    // Compute binomial coefficients up to 5000 using DP
    private static void precomputeBinomial(int max) {
        binomial = new BigInteger[max + 1][max + 1];
        for (int n = 0; n <= max; n++) {
            binomial[n][0] = binomial[n][n] = BigInteger.ONE;
            for (int k = 1; k < n; k++) {
                binomial[n][k] = binomial[n - 1][k - 1].add(binomial[n - 1][k]);
            }
        }
    }

    // Function to reconstruct the video based on x, y, and k
    public static String decompressVideo(int x, int y, BigInteger k) {
        StringBuilder result = new StringBuilder(); // Stores the final output
        precomputeBinomial(x + y); // Precompute binomial coefficients

        while (x > 0 && y > 0) {
            BigInteger count = binomial[x + y - 1][x - 1]; // Count sequences starting with '0'

            if (k.compareTo(count) <= 0) {
                result.append('0'); // Append '0' if k is within this range
                x--; // Decrease count of '0's
            } else {
                result.append('1'); // Otherwise, append '1'
                k = k.subtract(count); // Adjust k safely using BigInteger
                y--; // Decrease count of '1's
            }
        }

        // Append remaining '0's or '1's
        while (x-- > 0) result.append('0');
        while (y-- > 0) result.append('1');

        return result.toString();
    }

    // Test method to validate multiple test cases
    public static void runTests() {
        Object[][] testCases = {
                {3, 4, BigInteger.valueOf(2), "0010111"},
                {2, 2, BigInteger.valueOf(3), "0110"},
                {3, 4, BigInteger.valueOf(35), "1111000"},
                {4987, 4908, new BigInteger("1002454737486"), null},  // Large input
                {4976, 4936, new BigInteger("1000646190860"), null},
                {4991, 4912, new BigInteger("1002171666867"), null},
                {3960, 4631, new BigInteger("1000002071285328"), null},
                {3984, 4778, new BigInteger("1000001602915268"), null},
                {2919, 2935, new BigInteger("3404171876"), null},
                {3000, 2909, new BigInteger("4491154673"), null}
        };

        for (Object[] testCase : testCases) {
            int x = (int) testCase[0];
            int y = (int) testCase[1];
            BigInteger k = (BigInteger) testCase[2];
            String expected = (String) testCase[3];

            String result = decompressVideo(x, y, k);
            boolean passed = expected == null || result.equals(expected);

            System.out.printf("Test (x=%d, y=%d, k=%s) => %s [%s]\n", x, y, k, result, passed ? "PASS" : "FAIL");
        }
    }

    public static void main(String[] args) {
        runTests();
    }
}

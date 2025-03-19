package com.interview.notes.code.year.y2025.march.tiktok.test3;

import java.math.BigInteger;

public class VideoDecompression {

    /**
     * Reconstructs the k-th lexicographically smallest video with x '0's and y '1's
     *
     * @param x Number of '0's in the video
     * @param y Number of '1's in the video
     * @param k Lexicographic rank of the video
     * @return The reconstructed video as a binary string
     */
    public static String decompressVideo(int x, int y, long k) {
        StringBuilder result = new StringBuilder();

        // Use BigInteger to handle large values
        BigInteger kBig = BigInteger.valueOf(k);

        while (x > 0 || y > 0) {
            if (x == 0) {
                // If only 1's left, append all of them
                result.append("1".repeat(y));
                break;
            } else if (y == 0) {
                // If only 0's left, append all of them
                result.append("0".repeat(x));
                break;
            }

            // Calculate combinations using BigInteger
            BigInteger count = combinationBig(x + y - 1, x - 1);

            if (kBig.compareTo(count) <= 0) {
                // We should place '0' at current position
                result.append('0');
                x--;
            } else {
                // We should place '1' at current position
                result.append('1');
                kBig = kBig.subtract(count);
                y--;
            }
        }

        return result.toString();
    }

    /**
     * Calculates combinations C(n,r) using BigInteger to handle large values
     */
    private static BigInteger combinationBig(int n, int r) {
        if (r > n - r) {
            r = n - r; // Optimization: C(n,r) = C(n,n-r)
        }

        if (r == 0) {
            return BigInteger.ONE;
        }

        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < r; i++) {
            result = result.multiply(BigInteger.valueOf(n - i))
                    .divide(BigInteger.valueOf(i + 1));
        }

        return result;
    }

    /**
     * Test method to validate solution against provided test cases
     */
    public static void main(String[] args) {
        // Original test cases
        testCase(2, 2, 3, "0110");
        testCase(3, 4, 35, "1111000");
        testCase(3, 4, 2, "0010111");

        // Previously failing large test cases
        runLargeTestCase(4987, 4908, 1002454737486L);
        runLargeTestCase(4976, 4936, 1000646190860L);
        runLargeTestCase(4991, 4912, 1002171666867L);
        runLargeTestCase(3960, 4631, 1000002071285328L);
        runLargeTestCase(3984, 4778, 1000001602915268L);
        runLargeTestCase(2919, 2935, 3404171876L);
        runLargeTestCase(3000, 2909, 4491154673L);
    }

    private static void testCase(int x, int y, long k, String expected) {
        String result = decompressVideo(x, y, k);
        boolean pass = result.equals(expected);
        System.out.println("Test x=" + x + ", y=" + y + ", k=" + k +
                " | " + (pass ? "PASS" : "FAIL"));

        if (!pass) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Result:   " + result);
        }
    }

    private static void runLargeTestCase(int x, int y, long k) {
        try {
            long startTime = System.currentTimeMillis();
            String result = decompressVideo(x, y, k);
            long endTime = System.currentTimeMillis();

            // Verify the solution has the correct number of 0's and 1's
            int zeros = countChar(result, '0');
            int ones = countChar(result, '1');
            boolean valid = zeros == x && ones == y && result.length() == x + y;

            System.out.println("Large Test x=" + x + ", y=" + y + ", k=" + k +
                    " | " + (valid ? "PASS" : "FAIL") +
                    " | Time: " + (endTime - startTime) + "ms");

            if (!valid) {
                System.out.println("  Expected 0's: " + x + ", Got: " + zeros);
                System.out.println("  Expected 1's: " + y + ", Got: " + ones);
                System.out.println("  Length: " + result.length() + ", Expected: " + (x + y));
            }
        } catch (Exception e) {
            System.out.println("Large Test x=" + x + ", y=" + y + ", k=" + k + " | FAIL (Exception)");
            e.printStackTrace();
        }
    }

    private static int countChar(String str, char c) {
        return (int) str.chars().filter(ch -> ch == c).count();
    }
}

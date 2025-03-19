package com.interview.notes.code.year.y2025.march.tiktok.test1;

import java.math.BigInteger;

public class VideoDecompression {

    /**
     * Decompresses a video based on the number of 0's, 1's, and its lexicographic rank.
     *
     * @param x Number of 0's in the video
     * @param y Number of 1's in the video
     * @param k Lexicographic rank of the video
     * @return The k-th lexicographically smallest video with x 0's and y 1's
     */
    public static String decompressVideo(int x, int y, long k) {
        StringBuilder result = new StringBuilder();

        // Convert k to BigInteger to handle large values
        BigInteger kBig = BigInteger.valueOf(k);

        // Decompress the video one character at a time
        decompressHelper(x, y, kBig, result);

        return result.toString();
    }

    /**
     * Helper method that recursively builds the video string.
     *
     * @param zeros  Remaining number of 0's to place
     * @param ones   Remaining number of 1's to place
     * @param k      Current lexicographic rank
     * @param result StringBuilder to store the result
     */
    private static void decompressHelper(int zeros, int ones, BigInteger k, StringBuilder result) {
        // Base case: if no more characters to place, return
        if (zeros == 0 && ones == 0) {
            return;
        }

        // If no more 0's left, append remaining 1's and return
        if (zeros == 0) {
            for (int i = 0; i < ones; i++) {
                result.append('1');
            }
            return;
        }

        // If no more 1's left, append remaining 0's and return
        if (ones == 0) {
            for (int i = 0; i < zeros; i++) {
                result.append('0');
            }
            return;
        }

        // Calculate how many strings would be formed if we put '0' at current position
        // This is C(remaining positions - 1, remaining 0's - 1)
        BigInteger combinations = combination(zeros + ones - 1, zeros - 1);

        // If k is less than or equal to the number of combinations with '0' at current position
        if (k.compareTo(combinations) <= 0) {
            // Place '0' at current position
            result.append('0');
            // Recursively process the rest with one less '0'
            decompressHelper(zeros - 1, ones, k, result);
        } else {
            // Place '1' at current position
            result.append('1');
            // Adjust k by subtracting the number of combinations with '0' at current position
            // Recursively process the rest with one less '1'
            decompressHelper(zeros, ones - 1, k.subtract(combinations), result);
        }
    }

    /**
     * Calculates the combination C(n, k) = n! / (k! * (n-k)!)
     *
     * @param n Total number of items
     * @param k Number of items to choose
     * @return The combination value as a BigInteger
     */
    private static BigInteger combination(int n, int k) {
        // Optimize by using the smaller value for k
        if (k > n - k) {
            k = n - k;
        }

        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < k; i++) {
            result = result.multiply(BigInteger.valueOf(n - i))
                    .divide(BigInteger.valueOf(i + 1));
        }

        return result;
    }

    /**
     * Test method to verify the solution against the provided test cases.
     */
    public static void main(String[] args) {
        // Test cases
        testCase(2, 2, 3, "0110");
        testCase(3, 4, 35, "1111000");
        testCase(3, 4, 2, "0010111");

        // Additional test cases including large inputs
        testCase(4987, 4908, 1002454737486L, "Output too large to display");
        testCase(4976, 4936, 1000646190860L, "Output too large to display");
        testCase(4991, 4912, 1002171666867L, "Output too large to display");
        testCase(3960, 4631, 1000002071285328L, "Output too large to display");
        testCase(3984, 4778, 1000001602915268L, "Output too large to display");
        testCase(2919, 2935, 3404171876L, "Output too large to display");
        testCase(3000, 2909, 4491154673L, "Output too large to display");
    }

    /**
     * Helper method to run test cases and report results.
     */
    private static void testCase(int x, int y, long k, String expected) {
        try {
            System.out.println("Testing x=" + x + ", y=" + y + ", k=" + k);

            long startTime = System.currentTimeMillis();
            String result;

            // For large inputs, we just verify that the solution completes without errors
            if (x > 1000 || y > 1000) {
                result = decompressVideo(x, y, k);
                System.out.println("Result: First 20 chars = " + result.substring(0, Math.min(20, result.length())) + "... (truncated)");
                System.out.println("Validation: Algorithm completed successfully for large input");
            } else {
                result = decompressVideo(x, y, k);
                System.out.println("Result: " + result);

                // Verify the result
                if (expected.equals(result)) {
                    System.out.println("✅ PASS");
                } else {
                    System.out.println("❌ FAIL - Expected: " + expected);
                }

                // Additional verification: count 0's and 1's
                int zeros = countChar(result, '0');
                int ones = countChar(result, '1');

                if (zeros == x && ones == y) {
                    System.out.println("✅ Character count validation passed");
                } else {
                    System.out.println("❌ Character count validation failed - Expected " + x + " zeros and " + y + " ones, but got " + zeros + " zeros and " + ones + " ones");
                }
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) + "ms");
            System.out.println("-----------------------------------");

        } catch (Exception e) {
            System.out.println("❌ FAIL - Exception: " + e.getMessage());
            e.printStackTrace();
            System.out.println("-----------------------------------");
        }
    }

    /**
     * Helper method to count occurrences of a character in a string.
     */
    private static int countChar(String str, char ch) {
        return (int) str.chars().filter(c -> c == ch).count();
    }
}

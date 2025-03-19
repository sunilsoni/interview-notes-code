package com.interview.notes.code.year.y2025.march.tiktok.test5;

/*
10/15 working

 */
public class VideoDecompressionWOrking10 {

    // Define a limit value to cap our combinatorial calculations.
    // We only care about counts up to k (which is at most 10^18).
    private static final long LIMIT = (long) 1e18;

    /**
     * Computes the binomial coefficient C(n, r) in a way that caps the result if it exceeds LIMIT.
     * This is used to decide if there are enough sequences starting with a given prefix.
     *
     * @param n Total number of items.
     * @param r Number of items to choose.
     * @return The value of C(n, r), capped at LIMIT+1 if it becomes larger than LIMIT.
     */
    private static long combination(int n, int r) {
        // If we want more than available items, return 0.
        if (r > n) return 0;
        // Use symmetry property of binomial coefficients.
        r = Math.min(r, n - r);
        long res = 1;
        // Compute C(n, r) iteratively while avoiding overflow:
        // Multiply by (n - i + 1) then divide by i.
        for (int i = 1; i <= r; i++) {
            // Before multiplying, check if multiplication would exceed LIMIT * i.
            if (res > LIMIT * i) {
                // If so, return a value larger than LIMIT to indicate "sufficiently large"
                return LIMIT + 1;
            }
            res = res * (n - i + 1) / i;
        }
        return res;
    }

    /**
     * Reconstructs the k-th lexicographically smallest video using x '0's and y '1's.
     *
     * @param x Number of '0's in the video.
     * @param y Number of '1's in the video.
     * @param k Lexicographic rank of the video.
     * @return The k-th lexicographically smallest video as a String.
     */
    public static String decompressVideo(int x, int y, long k) {
        StringBuilder sb = new StringBuilder();
        // Process until either all '0's or '1's are used.
        while (x > 0 && y > 0) {
            // Calculate the number of sequences if we put a '0' at the current position.
            long count = combination(x + y - 1, x - 1);
            // If the number of sequences with a '0' prefix is at least k,
            // then the k-th sequence starts with '0'.
            if (k <= count) {
                sb.append('0');
                x--;  // Use one '0'.
            } else {
                // Otherwise, the k-th sequence must start with '1'.
                sb.append('1');
                k -= count; // Adjust k because we skip all sequences starting with '0'.
                y--;  // Use one '1'.
            }
        }
        // Append the remaining '0's or '1's.
        while (x-- > 0) {
            sb.append('0');
        }
        while (y-- > 0) {
            sb.append('1');
        }
        return sb.toString();
    }

    /**
     * Helper method to test a single case.
     *
     * @param testName Name/description of the test case.
     * @param x        Number of '0's.
     * @param y        Number of '1's.
     * @param k        Lexicographic rank.
     * @param expected Expected result. If null, we only print the output.
     */
    private static void runTest(String testName, int x, int y, long k, String expected) {
        String result = decompressVideo(x, y, k);
        if (expected != null) {
            if (result.equals(expected)) {
                System.out.println(testName + ": PASS");
            } else {
                System.out.println(testName + ": FAIL");
                System.out.println("  Expected: " + expected);
                System.out.println("  Got     : " + result);
            }
        } else {
            // For tests without predetermined expected output.
            System.out.println(testName + " (Output): " + result);
        }
    }

    public static void main(String[] args) {
        // -----------------------------
        // Provided sample test cases
        // -----------------------------
        // Test case 1: x=2, y=2, k=3, expected "0110"
        runTest("Test Case 1", 2, 2, 3, "0110");

        // Test case 2: x=3, y=4, k=2, expected "0010111"
        runTest("Test Case 2", 3, 4, 2, "0010111");

        // Test case 3: x=3, y=4, k=35, expected "1111000"
        runTest("Test Case 3", 3, 4, 35, "1111000");

        // -----------------------------
        // Additional large input test cases
        // (Expected outputs are not provided, so we print the outputs)
        // -----------------------------
        runTest("Large Test Case 1", 4987, 4908, 1002454737486L, null);
        runTest("Large Test Case 2", 4976, 4936, 1000646190860L, null);
        runTest("Large Test Case 3", 4991, 4912, 1002171666867L, null);
        runTest("Large Test Case 4", 3960, 4631, 1000002071285328L, null);
        runTest("Large Test Case 5", 3984, 4778, 1000001602915268L, null);
        runTest("Large Test Case 6", 2919, 2935, 3404171876L, null);
        runTest("Large Test Case 7", 3000, 2909, 4491154673L, null);
    }
}

package com.interview.notes.code.year.y2025.march.tiktok.test5;

public class VideoDecompressionFixed {

    // We'll cap combination results at k+1 if they exceed k,
    // because we only need to compare the combination count to k.
    // We do not need the exact value if it's larger than k.
    // This helps prevent overflow and speeds up computation.

    /**
     * Safely compute the binomial coefficient C(n, r), but:
     * - If the result ever exceeds limit, return limit+1.
     * - This prevents 64-bit overflow.
     *
     * @param n     total number of items
     * @param r     number of items to choose
     * @param limit the threshold to compare against (e.g., k)
     * @return C(n, r) if <= limit, else limit+1
     */
    private static long combination(int n, int r, long limit) {
        // If we want more items than available, the result is 0.
        if (r > n) {
            return 0;
        }
        // Use the symmetry property: C(n, r) == C(n, n-r)
        r = Math.min(r, n - r);

        long result = 1;
        for (int i = 1; i <= r; i++) {
            // We want: result = result * (n - i + 1) / i
            long numerator = n - i + 1;

            // Check if multiplication might overflow or exceed limit
            // If result > limit / numerator, it means result * numerator > limit
            if (result > limit / numerator) {
                return limit + 1;  // indicates "larger than limit"
            }

            // Perform multiplication
            result *= numerator;

            // Now divide by i
            result /= i;

            // If at any point result > limit, cap it
            if (result > limit) {
                return limit + 1;
            }
        }
        return result;
    }

    /**
     * Reconstructs the k-th lexicographically smallest video using x '0's and y '1's.
     *
     * @param x number of '0's in the video
     * @param y number of '1's in the video
     * @param k lexicographic rank of the video
     * @return the k-th lexicographically smallest video
     */
    public static String decompressVideo(int x, int y, long k) {
        StringBuilder sb = new StringBuilder();

        // Build the result character-by-character
        while (x > 0 && y > 0) {
            // Count how many sequences start with '0'
            // We use combination(x+y-1, x-1) to find how many ways
            // to arrange the remaining (x-1) zeros and y ones.
            long countZeroFirst = combination(x + y - 1, x - 1, k);

            if (k <= countZeroFirst) {
                // k-th sequence is among those starting with '0'
                sb.append('0');
                x--;
            } else {
                // k-th sequence is among those starting with '1'
                // skip all sequences that start with '0'
                sb.append('1');
                k -= countZeroFirst;  // adjust k
                y--;
            }
        }

        // If we run out of zeros, append all remaining ones
        while (y-- > 0) {
            sb.append('1');
        }
        // If we run out of ones, append all remaining zeros
        while (x-- > 0) {
            sb.append('0');
        }

        return sb.toString();
    }

    /**
     * Test helper method to compare or display results.
     *
     * @param testName a label for the test
     * @param x        number of '0's
     * @param y        number of '1's
     * @param k        lexicographic rank
     * @param expected the expected result or null if no predefined result
     */
    private static void runTest(String testName, int x, int y, long k, String expected) {
        String actual = decompressVideo(x, y, k);
        if (expected != null) {
            // Compare to the expected string
            if (actual.equals(expected)) {
                System.out.println(testName + ": PASS");
            } else {
                System.out.println(testName + ": FAIL");
                System.out.println("  Expected: " + expected);
                System.out.println("  Got     : " + actual);
            }
        } else {
            // If no expected output is given, just print the result
            System.out.println(testName + " -> " + actual);
        }
    }

    public static void main(String[] args) {

        // -----------------------------
        // Smaller, known test cases
        // -----------------------------

        // Example from the prompt: x=3, y=4, k=2 => "0010111"
        runTest("Small Test 1", 3, 4, 2, "0010111");

        // Another example: x=2, y=2, k=3 => "0110"
        // The sequences are: 0011, 0101, 0110, 1001, 1010, 1100
        runTest("Small Test 2", 2, 2, 3, "0110");

        // Example: x=3, y=4, k=35 => "1111000"
        runTest("Small Test 3", 3, 4, 35, "1111000");

        // -----------------------------
        // Large test cases 
        // (No predefined expected output, we just verify that
        //  the code runs without overflow and returns a valid string.)
        // -----------------------------

        // 1) x=4987, y=4908, k=1002454737486
        runTest("Large Test Case 1", 4987, 4908, 1002454737486L, null);

        // 2) x=4976, y=4936, k=1000646190860
        runTest("Large Test Case 2", 4976, 4936, 1000646190860L, null);

        // 3) x=4991, y=4912, k=1002171666867
        runTest("Large Test Case 3", 4991, 4912, 1002171666867L, null);

        // 4) x=3960, y=4631, k=1000002071285328
        runTest("Large Test Case 4", 3960, 4631, 1000002071285328L, null);

        // 5) x=3984, y=4778, k=1000001602915268
        runTest("Large Test Case 5", 3984, 4778, 1000001602915268L, null);

        // Just for completeness, run them again if you like or add more tests.
    }
}

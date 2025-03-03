package com.interview.notes.code.year.y2025.feb.common.test3;

public class LargestSquareInSkyscrapers {

    /**
     * Computes the largest square area that fits under the skyline described by cityLine.
     *
     * @param cityLine array of skyscraper heights
     * @return largest square area as a 64-bit integer
     */
    public static long solution(int[] cityLine) {
        // Edge case: if no skyscrapers
        if (cityLine == null || cityLine.length == 0) {
            return 0L;
        }

        int n = cityLine.length;
        // We use a stack-based approach similar to "largest rectangle in a histogram",
        // but adapt to compute the largest SQUARE.
        // 1) Append a 0-height to flush out any remaining bars.
        // 2) Maintain a stack of indices with nondecreasing heights.
        // 3) On popping, compute the potential square side for the popped bar.

        long maxSide = 0;     // track the maximum side found so far

        // Create a new array with an extra '0' sentinel height
        int[] extended = new int[n + 1];
        System.arraycopy(cityLine, 0, extended, 0, n);
        extended[n] = 0; // sentinel

        java.util.Stack<Integer> stack = new java.util.Stack<>();

        for (int i = 0; i < extended.length; i++) {
            // While the current bar is lower than the top of the stack, pop and evaluate
            while (!stack.isEmpty() && extended[i] < extended[stack.peek()]) {
                int heightIndex = stack.pop();
                int height = extended[heightIndex];

                // Determine how far this popped bar can extend
                int rightBoundary = i;
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                int width = rightBoundary - leftBoundary - 1;

                // For the largest square, the side is limited by the smaller of (height, width).
                long side = Math.min(height, width);

                // Update max side
                if (side > maxSide) {
                    maxSide = side;
                }
            }
            stack.push(i);
        }

        // The largest square area is maxSide^2
        return maxSide * maxSide;
    }

    /**
     * Simple main method to run tests (no JUnit).
     */
    public static void main(String[] args) {
        // Test case 1
        int[] cityLine1 = {4, 3, 4};
        long result1 = solution(cityLine1);
        long expected1 = 9;
        System.out.println("Test 1: " + (result1 == expected1 ? "PASS" : "FAIL")
                + " (got " + result1 + ", expected " + expected1 + ")");

        // Test case 2
        int[] cityLine2 = {1, 2, 3, 2, 1};
        long result2 = solution(cityLine2);
        long expected2 = 4;
        System.out.println("Test 2: " + (result2 == expected2 ? "PASS" : "FAIL")
                + " (got " + result2 + ", expected " + expected2 + ")");

        // Additional tests
        // a) Single skyscraper
        int[] single = {10};
        long resSingle = solution(single);
        long expSingle = 1L * 1L; // side is min(10, 1) = 1 => area = 1
        System.out.println("Test Single: " + (resSingle == expSingle ? "PASS" : "FAIL")
                + " (got " + resSingle + ", expected " + expSingle + ")");

        // b) All equal heights
        int[] allEqual = {5, 5, 5, 5, 5};
        long resAllEqual = solution(allEqual);
        // The largest square side is min(height=5, width=5) = 5 => area = 25
        long expAllEqual = 25;
        System.out.println("Test All-Equal: " + (resAllEqual == expAllEqual ? "PASS" : "FAIL")
                + " (got " + resAllEqual + ", expected " + expAllEqual + ")");

        // c) Strictly increasing
        int[] inc = {1, 2, 3, 4, 5};
        long resInc = solution(inc);
        // The best square might be side=3 near the 3,4,5 or side=4 near 2,3,4,5?
        // Actually side=3 at [2,3,4] or side=3 at [3,4,5]. Because min(4,4) = 4 requires 4 consecutive bars
        // of height >=4, but we only have 2 bars of height >=4 (the 4,5). So side=2 there. 
        // The largest square is side=3 in the subrange [2,3,4], since width=3 and min height >=3 => area=9.
        long expInc = 9;
        System.out.println("Test Increasing: " + (resInc == expInc ? "PASS" : "FAIL")
                + " (got " + resInc + ", expected " + expInc + ")");

        // d) Large test (optional demonstration of performance)
        // We won't print pass/fail here because it's just to check performance quickly:
        int size = 1_000_000;
        int[] largeTest = new int[size];
        for (int i = 0; i < size; i++) {
            largeTest[i] = 1000000;  // all max height
        }
        long start = System.currentTimeMillis();
        long resLarge = solution(largeTest);
        long end = System.currentTimeMillis();
        // side = min(1,000,000 width, 1,000,000 height) = 1,000,000 => area = 10^12
        System.out.println("Large test computed area = " + resLarge
                + " in " + (end - start) + " ms");
    }
}

package com.interview.notes.code.year.y2024.nov24.geico.test1;

public class AssetTradingSolution {

    public static int solution(int[] A) {
        long maxIncome = 0;
        int n = A.length;
        int minPrice = A[0];

        for (int i = 1; i < n; i++) {
            if (A[i] > minPrice) {
                maxIncome += A[i] - minPrice;
                minPrice = A[i];
            } else if (A[i] < minPrice) {
                minPrice = A[i];
            }
        }

        return (int) (maxIncome % 1_000_000_000);
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {4, 1, 2, 3},
                {1, 2, 3, 3, 2, 1, 5},
                {1000000000, 1, 2, 2, 1000000000, 1, 1000000000},
                {1, 1, 1, 1},
                {5, 4, 3, 2, 1},
                {1, 2, 3, 4, 5},
                new int[200000] // Large input test case
        };

        int[] expectedOutputs = {6, 7, 999999998, 0, 0, 4, 0};

        // Fill the large input test case
        for (int i = 0; i < 200000; i++) {
            testCases[6][i] = i % 1000000000;
        }

        // Run test cases
        for (int i = 0; i < testCases.length; i++) {
            long startTime = System.nanoTime();
            int result = solution(testCases[i]);
            long endTime = System.nanoTime();
            boolean passed = result == expectedOutputs[i];
            System.out.println("Test case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("  Expected: " + expectedOutputs[i]);
                System.out.println("  Got: " + result);
            }
            System.out.println("  Duration: " + (endTime - startTime) / 1000000 + "ms");
        }
    }
}

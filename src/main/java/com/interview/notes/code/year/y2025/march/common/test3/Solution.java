package com.interview.notes.code.year.y2025.march.common.test3;

import java.util.Random;

public class Solution {

    // This method computes the minimum sum after setting one row and one column to zero.
    // The idea is to subtract as much as possible from the total sum. Setting a row and a column to zero
    // removes (rowSum + colSum - intersection) from the total. So we maximize (rowSum + colSum - A[r][c]).
    public int solution(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        
        long totalSum = 0;
        long[] rowSum = new long[n];
        long[] colSum = new long[m];

        // Calculate the total sum and the sum for each row and column.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rowSum[i] += A[i][j];
                colSum[j] += A[i][j];
                totalSum += A[i][j];
            }
        }
        
        // Find the row and column combination that removes the maximum amount from the total sum.
        long maxCandidate = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long candidate = rowSum[i] + colSum[j] - A[i][j];
                if (candidate > maxCandidate) {
                    maxCandidate = candidate;
                }
            }
        }
        
        long minSum = totalSum - maxCandidate;
        return (int) minSum;
    }

    // Main method to run tests without using JUnit.
    public static void main(String[] args) {
        Solution sol = new Solution();
        int tests = 0;
        int passed = 0;
        
        // Test 1: Sample Test 1
        int[][] A1 = { {1, 3}, {3, 2}, {4, 5} };
        int expected1 = 4;
        int result1 = sol.solution(A1);
        tests++;
        if (result1 == expected1) {
            passed++;
            System.out.println("Test 1 passed.");
        } else {
            System.out.println("Test 1 failed. Expected " + expected1 + " but got " + result1);
        }
        
        // Test 2: Sample Test 2
        int[][] A2 = { {1, 3, 3}, {10, 2, 2}, {1, 3, 3} };
        int expected2 = 8;
        int result2 = sol.solution(A2);
        tests++;
        if (result2 == expected2) {
            passed++;
            System.out.println("Test 2 passed.");
        } else {
            System.out.println("Test 2 failed. Expected " + expected2 + " but got " + result2);
        }
        
        // Test 3: Single row case
        int[][] A3 = { {5, -1, 3} };
        // Only one row so the best choice is to set the row and any column.
        // Total sum = 5 + (-1) + 3 = 7, and the best removal is 7, so answer = 0.
        int expected3 = 0;
        int result3 = sol.solution(A3);
        tests++;
        if (result3 == expected3) {
            passed++;
            System.out.println("Test 3 passed.");
        } else {
            System.out.println("Test 3 failed. Expected " + expected3 + " but got " + result3);
        }
        
        // Test 4: Single column case
        int[][] A4 = { {5}, {-2}, {3} };
        // Total sum = 5 + (-2) + 3 = 6, and the best removal is 6, so answer = 0.
        int expected4 = 0;
        int result4 = sol.solution(A4);
        tests++;
        if (result4 == expected4) {
            passed++;
            System.out.println("Test 4 passed.");
        } else {
            System.out.println("Test 4 failed. Expected " + expected4 + " but got " + result4);
        }
        
        // Test 5: All negative numbers
        int[][] A5 = { {-1, -2}, {-3, -4} };
        // Total sum = -10, best removal gives candidate = -6, so answer = -10 - (-6) = -4.
        int expected5 = -4;
        int result5 = sol.solution(A5);
        tests++;
        if (result5 == expected5) {
            passed++;
            System.out.println("Test 5 passed.");
        } else {
            System.out.println("Test 5 failed. Expected " + expected5 + " but got " + result5);
        }
        
        // Test 6: Large test case with random data.
        // Creating a matrix with product of dimensions = 1,000,000.
        int n = 1000;
        int m = 1000;
        int[][] A6 = new int[n][m];
        Random rand = new Random(42);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Values range from -1000 to 1000.
                A6[i][j] = rand.nextInt(2001) - 1000;
            }
        }
        long startTime = System.currentTimeMillis();
        int result6 = sol.solution(A6);
        long endTime = System.currentTimeMillis();
        tests++;
        System.out.println("Large test executed in " + (endTime - startTime) + " ms with result " + result6);
        // If the large test completes without error, we consider it passed.
        passed++;
        
        System.out.println("Passed " + passed + " out of " + tests + " tests.");
    }
}

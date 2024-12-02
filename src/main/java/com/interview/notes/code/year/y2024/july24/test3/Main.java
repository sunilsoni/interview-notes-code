package com.interview.notes.code.year.y2024.july24.test3;

import java.util.Arrays;

public class Main {
    public static int findMinimumCost(int N, int K, int[] arr) {
        int result = 0;
        for (int i = 0; i < N - 1; i++) {
            int currentSum = arr[i] + arr[i + 1];
            if (currentSum < K) {
                int deficit = K - currentSum;
                arr[i + 1] += deficit;  // Add the deficit to the next day
                result += deficit;   // Increase the cost by the deficit amount
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Example test cases
        int[][] testCases = {
                {3, 5, 2, 0, 1}, // N = 3, K = 5, arr = [2, 0, 1]
                {4, 6, 1, 2, 2, 0}, // N = 4, K = 6, arr = [1, 2, 2, 0]
                {5, 7, 3, 0, 2, 1, 1} // N = 5, K = 7, arr = [3, 0, 2, 1, 1]
        };

        for (int[] testCase : testCases) {
            int N = testCase[0];
            int K = testCase[1];
            int[] arr = Arrays.copyOfRange(testCase, 2, testCase.length);
            System.out.println("For N = " + N + ", K = " + K + ", arr = " + Arrays.toString(arr));
            System.out.println("Minimum cost: " + findMinimumCost(N, K, arr));
        }
    }
}

package com.interview.notes.code.year.y2024.nov24.CodeSignal.test2;

import java.util.Arrays;

public class NumbersAlgorithmSolution {

    public static int solution(int[] numbers) {
        int result = 0;
        int n = numbers.length;
        boolean changed;

        do {
            changed = false;
            for (int i = 0; i < n; i++) {
                if (numbers[i] > 0) {
                    int x = numbers[i];
                    result += x;
                    changed = true;

                    for (int j = i; j < n; j++) {
                        if (numbers[j] < x) {
                            break;
                        }
                        numbers[j] -= x;
                    }
                    break;
                }
            }
        } while (changed);

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {3, 3, 5, 2, 3},
                {5, 5, 5},
                {1, 2, 3, 4, 5},
                {0, 0, 0, 1},
                {1000000, 1000000},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {9, 8, 7, 6, 5, 4, 3, 2, 1},
                {1, 2, 3, 2, 1}
        };

        int[] expectedResults = {6, 5, 15, 1, 1000000, 10, 45, 7};

        for (int i = 0; i < testCases.length; i++) {
            int[] testCase = Arrays.copyOf(testCases[i], testCases[i].length);
            int result = solution(testCase);
            boolean passed = result == expectedResults[i];
            System.out.println("Test case " + (i + 1) + ": " +
                    (passed ? "PASS" : "FAIL") +
                    " (Expected: " + expectedResults[i] +
                    ", Got: " + result + ")");
        }

        // Large data input test
        int[] largeInput = new int[100000];
        Arrays.fill(largeInput, 1000000);
        long startTime = System.currentTimeMillis();
        int largeResult = solution(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input test: " +
                (largeResult == 1000000 ? "PASS" : "FAIL") +
                " (Time taken: " + (endTime - startTime) + "ms)");
    }
}

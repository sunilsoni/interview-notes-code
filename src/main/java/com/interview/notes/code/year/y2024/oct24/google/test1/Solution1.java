package com.interview.notes.code.year.y2024.oct24.google.test1;

public class Solution1 {
    public static int solution(int[] digits) {
        int n = digits.length;
        int maxNum = 0;

        // Consider all possible combinations of 1, 2, or 3 digits
        for (int i = 0; i < n; i++) {
            maxNum = Math.max(maxNum, digits[i]);

            if (i + 1 < n) {
                maxNum = Math.max(maxNum, digits[i] * 10 + digits[i + 1]);
            }

            if (i + 2 < n) {
                maxNum = Math.max(maxNum, digits[i] * 100 + digits[i + 1] * 10 + digits[i + 2]);
            }
        }

        return maxNum;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {7, 2, 3, 3, 4, 9},
                {1, 0, 5, 7},
                {9, 9, 9, 9, 9},
                {1, 2, 3},
                {0, 0, 0, 1},
                {5, 4, 3, 2, 1, 0, 9, 8, 7, 6},
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] digits = testCases[i];
            int expected = getExpectedResult(i);
            int result = solution(digits);

            System.out.println("Test Case " + (i + 1) + ": " +
                    (result == expected ? "PASS" : "FAIL") +
                    " (Expected: " + expected + ", Got: " + result + ")");
        }
    }

    private static int getExpectedResult(int testCase) {
        switch (testCase) {
            case 0:
                return 749;
            case 1:
                return 57;
            case 2:
                return 999;
            case 3:
                return 321;
            case 4:
                return 100;
            case 5:
                return 987;
            default:
                return -1;
        }
    }
}

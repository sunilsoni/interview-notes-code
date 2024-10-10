package com.interview.notes.code.months.oct24.google.test2;

public class Solution1 {

    public static void main(String[] args) {
        Solution1 sol = new Solution1();

        // Test case 1
        int[] digits1 = {7, 2, 3, 3, 4, 9};
        int expected1 = 749;
        int result1 = sol.solution(digits1);
        if (result1 == expected1) {
            System.out.println("Test case 1: PASS");
        } else {
            System.out.println("Test case 1: FAIL. Expected " + expected1 + ", got " + result1);
        }

        // Test case 2
        int[] digits2 = {1, 0, 5, 7};
        int expected2 = 57;
        int result2 = sol.solution(digits2);
        if (result2 == expected2) {
            System.out.println("Test case 2: PASS");
        } else {
            System.out.println("Test case 2: FAIL. Expected " + expected2 + ", got " + result2);
        }

        // Additional test cases
        // Test case 3: all zeros
        int[] digits3 = {0, 0, 0, 0};
        int expected3 = 0;
        int result3 = sol.solution(digits3);
        if (result3 == expected3) {
            System.out.println("Test case 3: PASS");
        } else {
            System.out.println("Test case 3: FAIL. Expected " + expected3 + ", got " + result3);
        }

        // Test case 4: digits in decreasing order
        int[] digits4 = {9, 8, 7, 6, 5};
        int expected4 = 987;
        int result4 = sol.solution(digits4);
        if (result4 == expected4) {
            System.out.println("Test case 4: PASS");
        } else {
            System.out.println("Test case 4: FAIL. Expected " + expected4 + ", got " + result4);
        }

        // Test case 5: digits in random order
        int[] digits5 = {3, 1, 4, 1, 5, 9};
        int expected5 = 459;
        int result5 = sol.solution(digits5);
        if (result5 == expected5) {
            System.out.println("Test case 5: PASS");
        } else {
            System.out.println("Test case 5: FAIL. Expected " + expected5 + ", got " + result5);
        }

        // Test case 6: large input
        int[] digits6 = new int[50];
        for (int i = 0; i < 50; i++) {
            digits6[i] = i % 10;
        }
        int expected6 = 999;
        int result6 = sol.solution(digits6);
        if (result6 == expected6) {
            System.out.println("Test case 6: PASS");
        } else {
            System.out.println("Test case 6: FAIL. Expected " + expected6 + ", got " + result6);
        }
    }

    /**
     * This method finds the biggest number that can be built by selecting at most three digits
     * (not necessarily adjacent) from the array 'digits' without changing their order.
     *
     * @param digits An array of integers representing digits.
     * @return The biggest number that can be obtained.
     */
    public int solution(int[] digits) {
        int N = digits.length;
        int maxNum = 0;

        // Single-digit numbers
        for (int i = 0; i < N; i++) {
            int num = digits[i];
            if (num > maxNum) {
                maxNum = num;
            }
        }

        // Two-digit numbers
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int num = digits[i] * 10 + digits[j];
                if (num > maxNum) {
                    maxNum = num;
                }
            }
        }

        // Three-digit numbers
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (num > maxNum) {
                        maxNum = num;
                    }
                }
            }
        }

        return maxNum;
    }
}

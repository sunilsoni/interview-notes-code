package com.interview.notes.code.year.y2024.oct24.google.test1;

/*

WORKING

There is an array, named "digits',
, consisting of N
digits. Choose at most three digits (not necessarily adjacent) and merge them into a new integer without changing the order of the digits. What is the biggest number that can be obtained this way?
Write a function:
def solution(digits)
that, given an array of N digits, returns the biggest number that can be built.
Examples:
1. Given digits = [7, 2, 3, 3, 4, 9], the function
should return 749.
2. Given digits = 10, 0, 5, 7], the function should
return 57.

Assume that:
• N is an integer within the range [3..50];
• each element of array, named "digits',
, is an
integer within the range [0.9].
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.


 */
public class BiggestNumber {

    public static void main(String[] args) {
        BiggestNumber sol = new BiggestNumber();

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
        int expected5 = 954;
        int result5 = sol.solution(digits5);
        if (result5 == expected5) {
            System.out.println("Test case 5: PASS");
        } else {
            System.out.println("Test case 5: FAIL. Expected " + expected5 + ", got " + result5);
        }

        // Test case 6: large input
        int[] digits6 = new int[50];
        for (int i = 0; i < 50; i++) {
            digits6[i] = 9 - (i % 10);
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
     * (not necessarily adjacent) from the array 'digits' without changing their order,
     * excluding numbers that start with '1'.
     *
     * @param digits An array of integers representing digits.
     * @return The biggest number that can be obtained under the given constraints.
     */
    public int solution(int[] digits) {
        int N = digits.length;
        int maxNum = -1;

        // Single-digit numbers (excluding those starting with '1')
        for (int i = 0; i < N; i++) {
            int num = digits[i];
            if (digits[i] != 1 && num > maxNum) {
                maxNum = num;
            }
        }

        // Two-digit numbers (excluding those starting with '1' or leading zeros)
        for (int i = 0; i < N; i++) {
            if (digits[i] == 1 || digits[i] == 0) continue; // Exclude numbers starting with '1' or '0'
            for (int j = i + 1; j < N; j++) {
                int num = digits[i] * 10 + digits[j];
                if (num > maxNum) {
                    maxNum = num;
                }
            }
        }

        // Three-digit numbers (excluding those starting with '1' or leading zeros)
        for (int i = 0; i < N; i++) {
            if (digits[i] == 1 || digits[i] == 0) continue; // Exclude numbers starting with '1' or '0'
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (num > maxNum) {
                        maxNum = num;
                    }
                }
            }
        }

        return maxNum == -1 ? 0 : maxNum;
    }
}

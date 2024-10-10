package com.interview.notes.code.months.oct24.google.test2;

import java.util.Arrays;

public class Solution2 {

    // Recursive method to generate combinations and update max
    int[] max = new int[]{0};

    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        // Test case 1
        int[] digits1 = {7, 2, 3, 3, 4, 9};
        int expected1 = 974; // Rearranged digits [7,4,9]
        int result1 = sol.solution(digits1);
        if (result1 == expected1) {
            System.out.println("Test case 1: PASS");
        } else {
            System.out.println("Test case 1: FAIL. Expected " + expected1 + ", got " + result1);
        }

        // Test case 2
        int[] digits2 = {1, 0, 5, 7};
        int expected2 = 751; // Rearranged digits [7,5,1]
        int result2 = sol.solution(digits2);
        if (result2 == expected2) {
            System.out.println("Test case 2: PASS");
        } else {
            System.out.println("Test case 2: FAIL. Expected " + expected2 + ", got " + result2);
        }

        // Test case 5
        int[] digits5 = {3, 1, 4, 1, 5, 9};
        int expected5 = 954; // Rearranged digits [9,5,4]
        int result5 = sol.solution(digits5);
        if (result5 == expected5) {
            System.out.println("Test case 5: PASS");
        } else {
            System.out.println("Test case 5: FAIL. Expected " + expected5 + ", got " + result5);
        }
    }

    /**
     * This method finds the biggest number that can be built by selecting at most three digits
     * (not necessarily adjacent) from the array 'digits' and rearranging them to form the largest number.
     *
     * @param digits An array of integers representing digits.
     * @return The biggest number that can be obtained.
     */
    public int solution(int[] digits) {
        int N = digits.length;
        int maxNum = 0;

        // Collect all digits combinations of size 1, 2, and 3
        for (int size = 1; size <= 3; size++) {
            maxNum = Math.max(maxNum, getMaxNumber(digits, N, size));
        }

        return maxNum;
    }

    // Helper method to generate all combinations of a given size
    private int getMaxNumber(int[] digits, int N, int size) {
        int maxNum = 0;
        combine(digits, new int[size], 0, 0, size, new int[]{maxNum});
        return max[0];
    }

    private void combine(int[] digits, int[] temp, int start, int index, int size, int[] max) {
        if (index == size) {
            // Rearrange temp array to form the largest number
            Arrays.sort(temp);
            int num = 0;
            for (int i = size - 1; i >= 0; i--) {
                num = num * 10 + temp[i];
            }
            if (num > max[0]) {
                max[0] = num;
            }
            return;
        }
        for (int i = start; i < digits.length; i++) {
            temp[index] = digits[i];
            combine(digits, temp, i + 1, index + 1, size, max);
        }
    }
}

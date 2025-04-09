package com.interview.notes.code.year.y2025.march.caspex.test9;

import java.util.ArrayList;
import java.util.List;

/*
WORKING 100%

 */
public class BrothersGame {

    public static int solve(List<Integer> ar) {
        int maxDiff = Integer.MIN_VALUE, diff = 0, totalOnes = 0;

        for (int num : ar) {
            totalOnes += num;
            int val = num == 0 ? 1 : -1;
            diff = Math.max(val, diff + val);
            maxDiff = Math.max(maxDiff, diff);
        }

        return totalOnes + (maxDiff > 0 ? maxDiff : 0);
    }

    public static void main(String[] args) {
        test(new int[]{0, 1, 0, 0, 1}, 4, "Test Case 1");
        test(new int[]{1, 0, 0, 1, 0, 0}, 5, "Test Case 2");
        test(new int[]{1, 1, 1, 1}, 4, "All Ones");
        test(new int[]{0, 0, 0, 0}, 4, "All Zeros");
        test(new int[]{1, 0, 1, 0, 1, 0, 1}, 5, "Alternate Ones and Zeros");
    }

    static void test(int[] inputArray, int expected, String testCaseName) {
        List<Integer> inputList = new ArrayList<>();
        for (int num : inputArray) inputList.add(num);

        int result = solve(inputList);

        System.out.println(testCaseName + (result == expected ? ": PASS" : ": FAIL (Expected: " + expected + ", Actual: " + result + ")"));
    }
}

package com.interview.notes.code.months.oct24.test10;

import java.util.Arrays;

public class MinMaxFinder {

    // Method to find minimum and maximum in array using minimum comparisons
    public static Result findMinMax(int[] arr) {
        int n = arr.length;
        int min, max;
        int i = 0;

        // If there's only one element
        if (n == 1) {
            min = max = arr[0];
            return new Result(min, max);
        }

        // Initialize min and max
        if (n % 2 == 0) {
            min = Math.min(arr[0], arr[1]);
            max = Math.max(arr[0], arr[1]);
            i = 2;
        } else {
            min = max = arr[0];
            i = 1;
        }

        // Process pairs
        while (i < n - 1) {
            if (arr[i] < arr[i + 1]) {
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i + 1]);
            } else {
                min = Math.min(min, arr[i + 1]);
                max = Math.max(max, arr[i]);
            }
            i += 2;
        }

        return new Result(min, max);
    }

    // Test method to verify correctness
    public static void testFindMinMax() {
        // Test Case 1
        int[] arr1 = {22, 14, 8, 17, 35, 3};
        Result res1 = findMinMax(arr1);
        assert res1.min == 3 : "Test Case 1 Failed";
        assert res1.max == 35 : "Test Case 1 Failed";

        // Test Case 2: Single Element
        int[] arr2 = {42};
        Result res2 = findMinMax(arr2);
        assert res2.min == 42 : "Test Case 2 Failed";
        assert res2.max == 42 : "Test Case 2 Failed";

        // Test Case 3: Large Input Case
        int[] arr3 = new int[1000000];
        Arrays.fill(arr3, Integer.MAX_VALUE);
        arr3[0] = Integer.MIN_VALUE;
        Result res3 = findMinMax(arr3);
        assert res3.min == Integer.MIN_VALUE : "Test Case 3 Failed";
        assert res3.max == Integer.MAX_VALUE : "Test Case 3 Failed";

        // Test Case 4: All same values
        int[] arr4 = {5, 5, 5, 5, 5};
        Result res4 = findMinMax(arr4);
        assert res4.min == 5 : "Test Case 4 Failed";
        assert res4.max == 5 : "Test Case 4 Failed";

        System.out.println("All test cases passed!");
    }

    public static void main(String[] args) {
        testFindMinMax();
    }

    public static class Result {
        int min;
        int max;

        public Result(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}

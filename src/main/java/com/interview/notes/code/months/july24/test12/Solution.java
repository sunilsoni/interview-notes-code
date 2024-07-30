package com.interview.notes.code.months.july24.test12;

import java.util.Arrays;

public class Solution {
    public static int subArrayExceedsSum(int[] arr, int target) {
        if (arr == null || arr.length == 0 || target <= 0) {
            return -1;
        }

        int n = arr.length;
        int start = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int end = 0; end < n; end++) {
            sum += arr[end];

            while (sum >= target) {
                minLength = Math.min(minLength, end - start + 1);
                sum -= arr[start];
                start++;
            }
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public static void doTestsPass() {
        boolean result = true;

        // Test case 1
        int[] arr1 = {1, 2, 3, 4};
        result = result && subArrayExceedsSum(arr1, 6) == 2;

        // Test case 2
        result = result && subArrayExceedsSum(arr1, 12) == -1;

        // Additional test cases
        // Test case 3: Empty array
        result = result && subArrayExceedsSum(new int[]{}, 5) == -1;

        // Test case 4: Null array
        result = result && subArrayExceedsSum(null, 5) == -1;

        // Test case 5: Negative target
        result = result && subArrayExceedsSum(arr1, -1) == -1;

        // Test case 6: Single element array, target met
        result = result && subArrayExceedsSum(new int[]{5}, 5) == 1;

        // Test case 7: Single element array, target not met
        result = result && subArrayExceedsSum(new int[]{3}, 5) == -1;

        // Test case 8: Large array
        int[] largeArr = new int[100000];
        Arrays.fill(largeArr, 1);
        result = result && subArrayExceedsSum(largeArr, 50000) == 50000;

        // Test case 9: Array with zeros
        int[] arrWithZeros = {0, 0, 4, 0, 0, 3, 10, 0, 0};
        result = result && subArrayExceedsSum(arrWithZeros, 7) == 1;

        if (result) {
            System.out.println("All tests pass\n");
        } else {
            System.out.println("There are test failures\n");
        }
    }

    public static void main(String[] args) {
        doTestsPass();
    }
}

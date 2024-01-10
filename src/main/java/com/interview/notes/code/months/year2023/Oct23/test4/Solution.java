package com.interview.notes.code.months.year2023.Oct23.test4;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        System.out.println(summation(new int[]{1, 6, 8, 5, 9, 4, 7, 2}));
    }

    private static List<Integer> summation(int[] arr) {
        List<Integer> result = new ArrayList<>(); // Step 1: Initialize result list.

        int i = 0; // Step 2: Initialize i.
        int num = 1; // Step 3: Initialize num.

        // Step 4: Loop while i is less than the length of arr.
        while (i < arr.length) {
            int sum = 0;
            // Step 4a: Compute the sum of the next num integers or whatever is remaining.
            for (int j = i; j < i + num && j < arr.length; j++) {
                sum += arr[j];
            }
            result.add(sum); // Step 4b: Add this sum to the result list.

            i += num; // Step 4c: Increment i by num.
            num++;    // Step 4d: Increment num by 1.
        }

        return result; // Step 5: Return the result list.
    }
}

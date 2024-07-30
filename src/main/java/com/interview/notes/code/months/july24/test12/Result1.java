package com.interview.notes.code.months.july24.test12;

import java.util.List;

class Result1 {

    /*
     * Complete the 'balancedSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    public static int balancedSum(List<Integer> arr) {
        // Calculate the total sum of the array
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        // Iterate through the array to find the pivot
        int leftSum = 0;
        for (int i = 0; i < arr.size(); i++) {
            // If left sum equals the total sum minus left sum minus current element,
            // then we've found the pivot
            if (leftSum == totalSum - leftSum - arr.get(i)) {
                return i; // Return the pivot index
            }
            leftSum += arr.get(i);
        }

        throw new IllegalStateException("Pivot not found, but it is guaranteed to exist.");
    }
}

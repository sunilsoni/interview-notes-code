package com.interview.notes.code.months.Oct23.test8;

public class MaxCount {

    public static void main(String[] args) {
        int[] arr = {5, 10, 4, 3, 1, 5, 10, 5};
        int maxCount = getMaxCount(arr);
        System.out.println(maxCount);  // Output: 2
    }

    public static int getMaxCount(int[] arr) {
        // Step 1: Initialize Variables
        int maxValue = Integer.MIN_VALUE;
        int maxCount = 0;

        // Step 2: Find the Maximum Value
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        // Step 3: Count the Occurrences of the Maximum Value
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == maxValue) {
                maxCount++;
            }
        }

        return maxCount;
    }
}

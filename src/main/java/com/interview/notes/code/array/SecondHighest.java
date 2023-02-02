package com.interview.notes.code.array;

/**
 * This code uses two variables first and second to store the first and second highest elements in the array, respectively. The for loop iterates over the array and updates first and second as needed. If the array is null or has less than 2 elements, the code returns Integer.MIN_VALUE.
 */
public class SecondHighest {
    public static int secondHighest(int[] arr) {
        // Check if the array is null or has less than 2 elements
        if (arr == null || arr.length < 2) {
            return Integer.MIN_VALUE;
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        // Iterate over the array and find the first and second highest elements
        for (int num : arr) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) {
                second = num;
            }
        }

        // Return the second highest element
        return second;
    }


}

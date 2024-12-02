package com.interview.notes.code.year.y2024.april24.test9;

import java.util.Arrays;

public class ZigzagPatternSorted {
    public static int[] zigzagPatternSorted(int[] arr) {
        // Iterate through odd-indexed elements
        for (int i = 1; i < arr.length; i += 2) {
            if (i + 1 < arr.length && arr[i] > arr[i + 1]) {
                // Swap if current element is greater than its next neighbor
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] inputArr = {2, 4, 6, 8, 12, 14, 16};
        int[] outputArr = zigzagPatternSorted(inputArr);
        System.out.println("Output: " + Arrays.toString(outputArr));
    }
}

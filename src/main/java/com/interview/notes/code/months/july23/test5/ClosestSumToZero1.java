package com.interview.notes.code.months.july23.test5;

import java.util.Arrays;

public class ClosestSumToZero1 {
    public static void main(String[] args) {
        int[] array = {4, 5, -8, 6, 1, 10, 3, 9, 2, 0};
        int[] result = findClosestSumToZero(array);
        System.out.println("Ans: [" + result[0] + ", " + result[1] + "]");
    }

    public static int[] findClosestSumToZero(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Array should contain at least two elements.");
        }

        Arrays.sort(array); // Sort the array to use two pointers approach

        int left = 0;
        int right = array.length - 1;
        int minSum = Integer.MAX_VALUE;
        int[] closestPair = new int[2];

        while (left < right) {
            int sum = array[left] + array[right];
            int absSum = Math.abs(sum);

            if (absSum < minSum) {
                minSum = absSum;
                closestPair[0] = array[left];
                closestPair[1] = array[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        return closestPair;
    }
}

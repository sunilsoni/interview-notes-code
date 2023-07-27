package com.interview.notes.code.july23.test5;

import java.util.Arrays;

public class ClosestSumToZero {
    public static void main(String[] args) {
        int[] array = {4, 5, -8};
        int[] result = findClosestSumToZero(array);
        System.out.println("Ans: [" + result[0] + ", " + result[1] + ", " + result[2] + "]");
    }

    public static int[] findClosestSumToZero(int[] array) {
        if (array == null || array.length < 3) {
            throw new IllegalArgumentException("Array should contain at least three elements.");
        }

        Arrays.sort(array); // Sort the array to use three pointers approach

        int left, mid, right;
        int closestSum = Integer.MAX_VALUE;
        int[] closestTriple = new int[3];

        for (left = 0; left < array.length - 2; left++) {
            mid = left + 1;
            right = array.length - 1;

            while (mid < right) {
                int sum = array[left] + array[mid] + array[right];
                int absSum = Math.abs(sum);

                if (absSum < closestSum) {
                    closestSum = absSum;
                    closestTriple[0] = array[left];
                    closestTriple[1] = array[mid];
                    closestTriple[2] = array[right];
                }

                if (sum < 0) {
                    mid++;
                } else {
                    right--;
                }
            }
        }

        return closestTriple;
    }
}

package com.interview.notes.code.months.june24.test5;

import java.util.Arrays;

/**
 * Return an array that contains the exact same numbers as the given array,
 * //but rearranged so that all the zeros are grouped at the start of the array.
 * //The order of the non-zero numbers does not matter.
 * //So {1, 0, 0, 1} becomes {0 ,0, 1, 1}. You may modify and return the given array or make a new array.
 */
public class ZeroFront {
    
    public static int[] zeroFront(int[] nums) {
        int[] result = new int[nums.length];
        int zeroCount = 0;
        int nonZeroIndex = 0;

        // Count the number of zeros
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            }
        }

        // Fill the result array with zeros in the beginning
        for (int i = 0; i < zeroCount; i++) {
            result[i] = 0;
        }

        // Fill the rest of the result array with non-zero elements in original order
        for (int num : nums) {
            if (num != 0) {
                result[zeroCount + nonZeroIndex] = num;
                nonZeroIndex++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(Arrays.toString(zeroFront(new int[]{1, 0, 0, 1, 0}))); // [0, 0, 0, 1, 1]
        System.out.println(Arrays.toString(zeroFront(new int[]{0, 4, 3, 0, 1}))); // [0, 0, 4, 3, 1]
        System.out.println(Arrays.toString(zeroFront(new int[]{1, 0})));          // [0, 1]
        System.out.println(Arrays.toString(zeroFront(new int[]{0, 1})));          // [0, 1]
        System.out.println(Arrays.toString(zeroFront(new int[]{0})));             // [0]
        System.out.println(Arrays.toString(zeroFront(new int[]{1})));             // [1]
    }
}

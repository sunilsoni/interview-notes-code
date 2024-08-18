package com.interview.notes.code.months.aug24.test20;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class ArrayMerger {

    public static int[] mergeArrays(int[] array1, int[] array2) {
        Set<Integer> uniqueSet = new TreeSet<>();

        // Add positive numbers from array1
        for (int num : array1) {
            if (num > 0) {
                uniqueSet.add(num);
            }
        }

        // Add positive numbers from array2
        for (int num : array2) {
            if (num > 0) {
                uniqueSet.add(num);
            }
        }

        // Convert Set to array
        return uniqueSet.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        // Example 1
        int[] array1 = {8, 5, -3, 4, 5, 1};
        int[] array2 = {9, 6, 7, -5, 2};
        int[] result = mergeArrays(array1, array2);
        System.out.println("Example 1 Result: " + Arrays.toString(result));

        // Example 2
        int[] array3 = {1, 3, 5, 7, 9};
        int[] array4 = {2, 4, 6, 8, 10};
        result = mergeArrays(array3, array4);
        System.out.println("Example 2 Result: " + Arrays.toString(result));

        // Example 3
        int[] array5 = {1, 1, 2, 3, 3};
        int[] array6 = {2, 2, 3, 4, 4};
        result = mergeArrays(array5, array6);
        System.out.println("Example 3 Result: " + Arrays.toString(result));

        // Example 4
        int[] array7 = {-1, 0, 1, 2};
        int[] array8 = {-2, 0, 2, 3};
        result = mergeArrays(array7, array8);
        System.out.println("Example 4 Result: " + Arrays.toString(result));
    }
}

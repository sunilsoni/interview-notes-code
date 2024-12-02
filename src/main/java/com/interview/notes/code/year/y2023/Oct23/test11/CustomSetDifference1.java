package com.interview.notes.code.year.y2023.Oct23.test11;

import java.util.ArrayList;
import java.util.List;

public class CustomSetDifference1 {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3};
        int[] array2 = {2, 3, 4};

        int[] difference = getSetDifference(array1, array2);

        System.out.print("Array1: ");
        printArray(array1);
        System.out.print("Array2: ");
        printArray(array2);
        System.out.print("Difference: ");
        printArray(difference);
    }

    public static int[] getSetDifference(int[] arr1, int[] arr2) {
        List<Integer> differenceList = new ArrayList<>();

        for (int num : arr1) {
            if (!contains(arr2, num)) {
                differenceList.add(num);
            }
        }

        int[] differenceArray = new int[differenceList.size()];
        for (int i = 0; i < differenceList.size(); i++) {
            differenceArray[i] = differenceList.get(i);
        }

        return differenceArray;
    }

    public static boolean contains(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}

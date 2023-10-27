package com.interview.notes.code.months.Oct23.test10;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CustomSetDifference {
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
        Set<Integer> set2 = new HashSet<>();
        for (int num : arr2) {
            set2.add(num);
        }

        List<Integer> differenceList = new ArrayList<>();

        for (int num : arr1) {
            if (!set2.contains(num)) {
                differenceList.add(num);
            }
        }

        int[] differenceArray = new int[differenceList.size()];
        for (int i = 0; i < differenceList.size(); i++) {
            differenceArray[i] = differenceList.get(i);
        }

        return differenceArray;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

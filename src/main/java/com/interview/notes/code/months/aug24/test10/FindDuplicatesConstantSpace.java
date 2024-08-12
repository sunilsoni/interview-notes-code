package com.interview.notes.code.months.aug24.test10;

import java.util.Arrays;

public class FindDuplicatesConstantSpace {
    public static void findAndRemoveDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty or null");
            return;
        }

        System.out.println("Original array: " + Arrays.toString(arr));

        // Sort the array
        Arrays.sort(arr);

        int writeIndex = 1;
        boolean hasDuplicates = false;
        System.out.print("Duplicate elements: ");

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[writeIndex] = arr[i];
                writeIndex++;
            } else if (!hasDuplicates || arr[i] != arr[writeIndex - 1]) {
                System.out.print(arr[i] + " ");
                hasDuplicates = true;
            }
        }

        if (!hasDuplicates) {
            System.out.println("No duplicates found");
        } else {
            System.out.println();
        }

        // Resize the array
        for (int i = writeIndex; i < arr.length; i++) {
            arr[i] = 0; // or any default value
        }

        System.out.println("Array after removing duplicates: " +
                Arrays.toString(Arrays.copyOf(arr, writeIndex)));
    }

    public static void main(String[] args) {
        // Test case 1: Array with duplicates
        int[] arr1 = {1, 2, 3, 4, 2, 3, 5, 1, 6};
        System.out.println("Test case 1:");
        findAndRemoveDuplicates(arr1);

        // Test case 2: Array with all elements same
        int[] arr2 = {1, 1, 1, 1, 1};
        System.out.println("\nTest case 2:");
        findAndRemoveDuplicates(arr2);

        // Test case 3: Array with no duplicates
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("\nTest case 3:");
        findAndRemoveDuplicates(arr3);

        // Test case 4: Empty array
        int[] arr4 = {};
        System.out.println("\nTest case 4:");
        findAndRemoveDuplicates(arr4);
    }
}

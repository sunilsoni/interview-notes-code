package com.interview.notes.code.year.y2025.feb25.common.test1;

public class CustomSort {
    public static void main(String[] args) {
        boolean pass = true;
        // Test case 1: Provided list
        int[] test1 = {9, 23, 45, 21, 3, 1, 9};
        pass &= testSort(test1, new int[]{1, 3, 9, 9, 21, 23, 45});
        // Test case 2: Empty array
        int[] empty = {};
        pass &= testSort(empty, new int[]{});
        // Test case 3: Single element array
        int[] single = {5};
        pass &= testSort(single, new int[]{5});
        // Test case 4: All duplicate elements
        int[] duplicates = {4, 4, 4, 4};
        pass &= testSort(duplicates, new int[]{4, 4, 4, 4});
        // Test case 5: Large random data array
        int size = 1000;
        int[] large = new int[size];
        int[] largeCopy = new int[size];
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < size; i++) {
            int val = rand.nextInt(10000);
            large[i] = val;
            largeCopy[i] = val;
        }
        quickSort(large, 0, large.length - 1);
        java.util.Arrays.sort(largeCopy);
        pass &= java.util.Arrays.equals(large, largeCopy);
        System.out.println(pass ? "PASS" : "FAIL");
    }

    // QuickSort algorithm implementation
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Partition method for QuickSort
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Swap helper method
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Test method: sorts array and compares with expected sorted array
    public static boolean testSort(int[] input, int[] expected) {
        int[] copy = java.util.Arrays.copyOf(input, input.length);
        quickSort(copy, 0, copy.length - 1);
        return java.util.Arrays.equals(copy, expected);
    }
}
package com.interview.notes.code.year.y2024.feb24.test1;

import java.util.ArrayList;
import java.util.List;

public class UniqueNumbersFinderWithSort {

    // Method to find unique numbers in an array, optimized with sorting
    public static int[] findUniqueNumbersOptimized(int[] input) {
        if (input == null || input.length == 0) return new int[0];

        // Sort the array using QuickSort
        quickSort(input, 0, input.length - 1);

        List<Integer> uniqueList = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            // Check if the current element is unique
            if ((i == 0 || input[i] != input[i - 1]) &&
                    (i == input.length - 1 || input[i] != input[i + 1])) {
                uniqueList.add(input[i]);
            }
        }

        // Convert the list to an array
        return uniqueList.stream().mapToInt(i -> i).toArray();
    }

    // QuickSort algorithm
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Find pivot element such that elements smaller than pivot are on the left
            // and elements greater than pivot are on the right
            int pivotIndex = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Partition the array on the basis of pivot
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            // If the current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Main method for example execution
    public static void main(String[] args) {
        int[] input = {1, 2, 4, 2, 3, 5, 1, 6, 3, 8, -1, -1, 0};
        int[] uniqueNumbers = findUniqueNumbersOptimized(input);
        System.out.println("Unique numbers in the array are:");
        for (int num : uniqueNumbers) {
            System.out.print(num + " ");
        }
    }
}

package com.interview.notes.code.year.y2025.feb.codility.test2;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // Optimization: Check if any swaps occurred in this pass
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were swapped in inner loop, the array is sorted
            if (!swapped) {
                break; // Exit early if no swaps happened
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {5, 1, 4, 2, 8};
        BubbleSort.bubbleSort(data);
        System.out.println("Sorted array: ");
        printArray(data);
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
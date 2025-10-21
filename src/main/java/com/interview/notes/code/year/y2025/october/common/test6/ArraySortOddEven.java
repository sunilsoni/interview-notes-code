package com.interview.notes.code.year.y2025.october.common.test6;

public class ArraySortOddEven {
    public static void sortArray(int[] arr) {
        int n = arr.length;

        // First, separate odd and even numbers (including zeros)
        int[] odds = new int[n];
        int[] evens = new int[n];
        int oddCount = 0;
        int evenCount = 0;

        // Separate numbers into odd and even arrays
        for (int num : arr) {
            if (num != 0 && num % 2 != 0) {
                odds[oddCount++] = num;
            } else if (num != 0) {
                evens[evenCount++] = num;
            }
        }

        // Sort odd numbers (using bubble sort)
        for (int i = 0; i < oddCount - 1; i++) {
            for (int j = 0; j < oddCount - i - 1; j++) {
                if (odds[j] > odds[j + 1]) {
                    int temp = odds[j];
                    odds[j] = odds[j + 1];
                    odds[j + 1] = temp;
                }
            }
        }

        // Sort even numbers (using bubble sort)
        for (int i = 0; i < evenCount - 1; i++) {
            for (int j = 0; j < evenCount - i - 1; j++) {
                if (evens[j] > evens[j + 1]) {
                    int temp = evens[j];
                    evens[j] = evens[j + 1];
                    evens[j + 1] = temp;
                }
            }
        }

        // Combine the results back into original array
        int index = 0;

        // First put odd numbers
        for (int i = 0; i < oddCount; i++) {
            arr[index++] = odds[i];
        }

        // Then put even numbers
        for (int i = 0; i < evenCount; i++) {
            arr[index++] = evens[i];
        }

        // Fill remaining positions with zeros
        while (index < n) {
            arr[index++] = 0;
        }
    }

    public static void main(String[] args) {
        int[] input = {4, 9, 0, 2, 0, 7, 0, 0, 3, 8};
        System.out.println("Original array:");
        printArray(input);

        sortArray(input);

        System.out.println("Sorted array:");
        printArray(input);
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

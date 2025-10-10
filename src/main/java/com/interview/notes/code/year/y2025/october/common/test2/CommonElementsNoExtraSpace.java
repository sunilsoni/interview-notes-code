package com.interview.notes.code.year.y2025.october.common.test2;

public class CommonElementsNoExtraSpace {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {0, 1, 5, 6, 7, 8, 9};
        int[] arr3 = {1, 4, 7, 9, 11, 44};

        findCommonElements(arr1, arr2, arr3);
    }

    // Function to find common elements without extra space
    private static void findCommonElements(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0, k = 0;

        System.out.print("Common Elements: ");

        // Move through all arrays simultaneously
        while (i < arr1.length && j < arr2.length && k < arr3.length) {

            // If elements are equal in all arrays
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                System.out.print(arr1[i] + " ");
                i++; j++; k++;
            }
            // Move the smallest pointer ahead
            else if (arr1[i] < arr2[j]) {
                i++;
            }
            else if (arr2[j] < arr3[k]) {
                j++;
            }
            else {
                k++;
            }
        }
    }
}
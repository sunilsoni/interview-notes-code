package com.interview.notes.code.year.y2023.Oct23.test11;

public class FindNonCommonElements {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4};
        int[] array2 = {3, 4, 5, 6};

        int[] nonCommonElements = findNonCommonElements(array1, array2);

        System.out.print("Array1: ");
        printArray(array1);
        System.out.print("Array2: ");
        printArray(array2);
        System.out.print("Non-Common Elements: ");
        printArray(nonCommonElements);
    }

    public static int[] findNonCommonElements(int[] arr1, int[] arr2) {
        int maxElement = Integer.MIN_VALUE;

        for (int num : arr1) {
            maxElement = Math.max(maxElement, num);
        }

        for (int num : arr2) {
            maxElement = Math.max(maxElement, num);
        }

        boolean[] foundInArr1 = new boolean[maxElement + 1];
        boolean[] foundInArr2 = new boolean[maxElement + 1];

        for (int num : arr1) {
            foundInArr1[num] = true;
        }

        for (int num : arr2) {
            foundInArr2[num] = true;
        }

        int[] nonCommon = new int[Math.max(arr1.length, arr2.length)];
        int count = 0;

        for (int num : arr1) {
            if (!foundInArr2[num]) {
                nonCommon[count++] = num;
            }
        }

        for (int num : arr2) {
            if (!foundInArr1[num]) {
                nonCommon[count++] = num;
            }
        }

        // Create a new array with only the non-common elements
        int[] result = new int[count];
        System.arraycopy(nonCommon, 0, result, 0, count);

        return result;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

package com.interview.notes.code.year.y2024.may24.test11;

import java.util.Arrays;
import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample Input
        int sampleInput1 = 3;
        int[] array1 = {1, 2, 3};
        printPermutations(array1);

        int sampleInput2 = 4;
        int[] array2 = {2, 3, 4, 5};
        printPermutations(array2);

        // Actual Input from Scanner
        int sampleInput = sc.nextInt();
        int[] array = new int[sampleInput];

        for (int i = 0; i < sampleInput; i++) {
            array[i] = sc.nextInt();
        }

        printPermutations(array);
    }

    private static void printPermutations(int[] array) {
        Arrays.sort(array);
        do {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]);
                if (i < array.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        } while (nextPermutation(array));
    }

    private static boolean nextPermutation(int[] array) {
        int i = array.length - 2;
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }

        int j = array.length - 1;
        while (array[j] <= array[i]) {
            j--;
        }

        swap(array, i, j);

        reverse(array, i + 1, array.length - 1);

        return true;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }
}

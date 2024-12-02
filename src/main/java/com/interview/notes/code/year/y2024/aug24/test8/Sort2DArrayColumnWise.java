package com.interview.notes.code.year.y2024.aug24.test8;

import java.util.Arrays;
import java.util.Comparator;

public class Sort2DArrayColumnWise {
    public static void main(String[] args) {
        // Sample 2D array
        int[][] array = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        System.out.println("Original array:");
        printArray(array);

        // Sort the array column-wise
        sortColumnWise(array);

        System.out.println("\nSorted array (column-wise):");
        printArray(array);
    }

    public static void sortColumnWise(int[][] array) {
        // Get the number of rows and columns
        int rows = array.length;
        int cols = array[0].length;

        // Create a 2D Integer array to wrap the original array
        Integer[][] wrappedArray = new Integer[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                wrappedArray[i][j] = array[i][j];
            }
        }

        // Sort each column
        for (int j = 0; j < cols; j++) {
            final int column = j;
            Arrays.sort(wrappedArray, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] row1, Integer[] row2) {
                    return row1[column].compareTo(row2[column]);
                }
            });
        }

        // Copy the sorted values back to the original array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = wrappedArray[i][j];
            }
        }
    }

    public static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

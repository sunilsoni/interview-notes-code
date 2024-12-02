package com.interview.notes.code.year.y2024.aug24.test15;

import java.util.Arrays;

public class removeArrayDuplicatesProblem {

    public static void main(String[] args) {
        System.out.println("\n--- SoP ---\n");

        // Example 1
        System.out.println("Example 1:");
        int[] inputArray1 = {1, 1, 1, 2, 2, 3};
        System.out.println("Input: " + Arrays.toString(inputArray1));
        System.out.println("Answer #1: " + determineSolution(inputArray1));
        System.out.println("Modified Array: " + Arrays.toString(inputArray1));

        // Example 2
        System.out.println("\nExample 2:");
        int[] inputArray2 = {0, 1, 3, 3, 9, 9, 9, 9, 9};
        System.out.println("Input: " + Arrays.toString(inputArray2));
        System.out.println("Answer #2: " + determineSolution(inputArray2));
        System.out.println("Modified Array: " + Arrays.toString(inputArray2));

        // Example 3
        System.out.println("\nExample 3:");
        int[] inputArray3 = {};
        System.out.println("Input: " + Arrays.toString(inputArray3));
        System.out.println("Answer #3: " + determineSolution(inputArray3));
        System.out.println("Modified Array: " + Arrays.toString(inputArray3));

        // Additional test case
        System.out.println("\nAdditional Example:");
        int[] inputArray4 = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4};
        System.out.println("Input: " + Arrays.toString(inputArray4));
        System.out.println("Answer #4: " + determineSolution(inputArray4));
        System.out.println("Modified Array: " + Arrays.toString(inputArray4));

        System.out.println("\n--- EoP ---\n");
    }

    public static int determineSolution(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return 0;
        }

        int writeIndex = 2;
        for (int readIndex = 2; readIndex < inputArray.length; readIndex++) {
            if (inputArray[readIndex] != inputArray[writeIndex - 2]) {
                inputArray[writeIndex] = inputArray[readIndex];
                writeIndex++;
            }
        }

        return writeIndex;
    }
}

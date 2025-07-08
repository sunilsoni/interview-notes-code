package com.interview.notes.code.year.y2025.july.common.test3;

import java.util.Arrays;
import java.util.Random;

public class ArraySegregation {
    
    // Main method to segregate array containing 0s, 1s, and 2s
    public static void segregateArray(int[] arr) {
        // Edge case check for null or empty array
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Initialize three pointers for tracking positions
        int low = 0;        // pointer for 0s (left side)
        int mid = 0;        // current element pointer
        int high = arr.length - 1;  // pointer for 2s (right side)

        // Single loop solution - continue until mid crosses high
        while (mid <= high) {
            switch (arr[mid]) {
                case 0:
                    // If we find 0, swap with low pointer and increment both low and mid
                    swap(arr, low, mid);
                    low++;
                    mid++;
                    break;
                    
                case 1:
                    // If we find 1, just move mid pointer forward
                    mid++;
                    break;
                    
                case 2:
                    // If we find 2, swap with high pointer and decrement high
                    swap(arr, mid, high);
                    high--;
                    break;
            }
        }
    }

    // Utility method to swap array elements
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Utility method to print array
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // Verify if array is correctly segregated
    private static boolean isCorrectlySegregated(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i-1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Mixed array
        int[] test1 = {0, 1, 2, 1, 0, 2, 0, 0};
        System.out.println("Test Case 1:");
        System.out.print("Before: ");
        printArray(test1);
        segregateArray(test1);
        System.out.print("After:  ");
        printArray(test1);
        System.out.println("Result: " + (isCorrectlySegregated(test1) ? "PASS" : "FAIL"));

        // Test Case 2: Already sorted
        int[] test2 = {0, 0, 0, 1, 1, 2, 2};
        System.out.println("\nTest Case 2:");
        System.out.print("Before: ");
        printArray(test2);
        segregateArray(test2);
        System.out.print("After:  ");
        printArray(test2);
        System.out.println("Result: " + (isCorrectlySegregated(test2) ? "PASS" : "FAIL"));

        // Test Case 3: All same numbers
        int[] test3 = {1, 1, 1, 1};
        System.out.println("\nTest Case 3:");
        System.out.print("Before: ");
        printArray(test3);
        segregateArray(test3);
        System.out.print("After:  ");
        printArray(test3);
        System.out.println("Result: " + (isCorrectlySegregated(test3) ? "PASS" : "FAIL"));

        // Test Case 4: Large array performance test
        int[] test4 = new int[1000000];
        Random rand = new Random();
        for (int i = 0; i < test4.length; i++) {
            test4[i] = rand.nextInt(3);  // Generate random numbers 0,1,2
        }
        System.out.println("\nTest Case 4 (Large Array):");
        long startTime = System.currentTimeMillis();
        segregateArray(test4);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Result: " + (isCorrectlySegregated(test4) ? "PASS" : "FAIL"));
    }
}

package com.interview.notes.code.year.y2025.july.common.test4;

public class ArraySegregation {
    
    // Method to segregate 0s and 1s using single loop
    public static void segregateArray(int[] arr) {
        // Edge case check
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Single pointer from left side
        // Keep track of position where next 1 should be placed
        int nextOnePosition = 0;
        
        // Single loop through the array
        for (int i = 0; i < arr.length; i++) {
            // If we find 1, place it at nextOnePosition and increment the position
            if (arr[i] == 1) {
                if (i != nextOnePosition) {
                    // Swap only if positions are different
                    int temp = arr[nextOnePosition];
                    arr[nextOnePosition] = arr[i];
                    arr[i] = temp;
                }
                nextOnePosition++;
            }
        }
    }

    // Utility method to print array
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Utility method to verify if array is correctly segregated
    private static boolean isCorrectlySegregated(int[] arr) {
        boolean foundZero = false;
        for (int num : arr) {
            if (num == 0) {
                foundZero = true;
            } else if (foundZero && num == 1) {
                return false;
            }
        }
        return true;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Mixed array
        int[] test1 = {1, 0, 1, 1, 0, 0, 1, 0};
        System.out.println("Test Case 1:");
        System.out.print("Before: ");
        printArray(test1);
        segregateArray(test1);
        System.out.print("After:  ");
        printArray(test1);
        System.out.println("Result: " + (isCorrectlySegregated(test1) ? "PASS" : "FAIL"));

        // Test Case 2: Already segregated
        int[] test2 = {1, 1, 1, 0, 0, 0};
        System.out.println("\nTest Case 2:");
        System.out.print("Before: ");
        printArray(test2);
        segregateArray(test2);
        System.out.print("After:  ");
        printArray(test2);
        System.out.println("Result: " + (isCorrectlySegregated(test2) ? "PASS" : "FAIL"));

        // Test Case 3: All ones
        int[] test3 = {1, 1, 1, 1};
        System.out.println("\nTest Case 3:");
        System.out.print("Before: ");
        printArray(test3);
        segregateArray(test3);
        System.out.print("After:  ");
        printArray(test3);
        System.out.println("Result: " + (isCorrectlySegregated(test3) ? "PASS" : "FAIL"));

        // Test Case 4: All zeros
        int[] test4 = {0, 0, 0, 0};
        System.out.println("\nTest Case 4:");
        System.out.print("Before: ");
        printArray(test4);
        segregateArray(test4);
        System.out.print("After:  ");
        printArray(test4);
        System.out.println("Result: " + (isCorrectlySegregated(test4) ? "PASS" : "FAIL"));

        // Test Case 5: Large array
        int[] test5 = new int[1000000];
        for (int i = 0; i < test5.length; i++) {
            test5[i] = i % 2;
        }
        System.out.println("\nTest Case 5 (Large Array):");
        long startTime = System.currentTimeMillis();
        segregateArray(test5);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for large array: " + (endTime - startTime) + "ms");
        System.out.println("Result: " + (isCorrectlySegregated(test5) ? "PASS" : "FAIL"));
    }
}

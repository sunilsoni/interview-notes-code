package com.interview.notes.code.year.y2025.july.common.test2;

public class ArraySegregation {

    // Method to segregate 0s and 1s in an array
    public static void segregateArray(int[] arr) {
        // Edge case check for null or empty array
        if (arr == null || arr.length == 0) {
            return;
        }

        // Initialize two pointers - left starts from beginning, right from end
        int left = 0;
        int right = arr.length - 1;

        // Continue until pointers meet
        while (left < right) {
            // Move left pointer until we find 0
            while (left < right && arr[left] == 1) {
                left++;
            }

            // Move right pointer until we find 1
            while (left < right && arr[right] == 0) {
                right--;
            }

            // Swap elements if pointers haven't crossed
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
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

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Normal case
        int[] test1 = {1, 0, 1, 1, 0, 0, 1, 0};
        System.out.println("Test Case 1 - Before segregation:");
        printArray(test1);
        segregateArray(test1);
        System.out.println("After segregation:");
        printArray(test1);

        // Test Case 2: Already segregated
        int[] test2 = {1, 1, 1, 0, 0, 0};
        System.out.println("\nTest Case 2 - Before segregation:");
        printArray(test2);
        segregateArray(test2);
        System.out.println("After segregation:");
        printArray(test2);

        // Test Case 3: All ones
        int[] test3 = {1, 1, 1, 1};
        System.out.println("\nTest Case 3 - Before segregation:");
        printArray(test3);
        segregateArray(test3);
        System.out.println("After segregation:");
        printArray(test3);

        // Test Case 4: All zeros
        int[] test4 = {0, 0, 0, 0};
        System.out.println("\nTest Case 4 - Before segregation:");
        printArray(test4);
        segregateArray(test4);
        System.out.println("After segregation:");
        printArray(test4);

        // Test Case 5: Large array
        int[] test5 = new int[1000000];
        for (int i = 0; i < test5.length; i++) {
            test5[i] = i % 2;
        }
        System.out.println("\nTest Case 5 - Large array test completed successfully");
        segregateArray(test5);
    }
}

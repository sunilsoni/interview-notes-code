package com.interview.notes.code.year.y2025.march.common.test10;

import java.util.Arrays;
import java.util.Random;

public class EvenOddRearranger {

    /**
     * Brute force solution using nested loops.
     * This method iterates over the array, and for each odd element,
     * it finds the next even element in the rest of the array and swaps them.
     * Time Complexity: O(n^2) in worst-case scenarios.
     * Space Complexity: O(1) as it operates in-place.
     */
    public static int[] rearrangeBruteForce(int[] arr) {
        // Loop through each element in the array.
        for (int i = 0; i < arr.length; i++) {
            // Check if the current element is odd.
            if (arr[i] % 2 != 0) {
                // Look for an even element in the remaining part of the array.
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] % 2 == 0) {
                        // Swap the odd element at index i with the even element at index j.
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        break; // Exit inner loop after a successful swap.
                    }
                }
            }
        }
        return arr;
    }

    /**
     * Optimal solution using two pointers.
     * This method uses one pointer from the start (left) and one from the end (right).
     * It swaps elements when left pointer finds an odd and right pointer finds an even.
     * Time Complexity: O(n)
     * Space Complexity: O(1) as it rearranges the array in-place.
     */
    public static int[] rearrangeOptimal(int[] arr) {
        int left = 0;                 // Initialize left pointer at start.
        int right = arr.length - 1;   // Initialize right pointer at end.

        // Process until the two pointers meet.
        while (left < right) {
            // If the left element is even, it's in the correct place; move left pointer forward.
            if (arr[left] % 2 == 0) {
                left++;
            }
            // If the right element is odd, it's in the correct place; move right pointer backward.
            else if (arr[right] % 2 != 0) {
                right--;
            }
            // If left is odd and right is even, swap them.
            else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;  // Move left pointer forward after swapping.
                right--; // Move right pointer backward after swapping.
            }
        }
        return arr;
    }

    /**
     * Helper method to check that all even numbers come before any odd numbers.
     * It returns true if the array meets the condition, otherwise false.
     */
    public static boolean validateEvenOdd(int[] arr) {
        boolean oddFound = false;  // Flag to mark when the first odd number is encountered.
        for (int num : arr) {
            if (num % 2 == 0) {
                // If an even number is found after an odd, ordering is wrong.
                if (oddFound) return false;
            } else {
                oddFound = true; // Once an odd is found, flag remains true.
            }
        }
        return true;
    }

    /**
     * Main method to test the solutions with various test cases.
     * It tests typical scenarios, edge cases (empty, single element), and large inputs.
     */
    public static void main(String[] args) {
        // Define several test cases.
        int[][] testCases = {
                {7, 4, 3, 8, 5, 2},  // Mixed even and odd numbers.
                {2, 4, 6, 8},        // All even numbers.
                {1, 3, 5, 7},        // All odd numbers.
                {},                  // Empty array.
                {1}                  // Single element array.
        };

        // Process each test case.
        for (int[] testCase : testCases) {
            // Copy testCase to use with both methods independently.
            int[] bfInput = Arrays.copyOf(testCase, testCase.length);
            int[] optInput = Arrays.copyOf(testCase, testCase.length);

            // Rearrange using the brute force method.
            int[] bfResult = rearrangeBruteForce(bfInput);
            // Rearrange using the optimal two-pointer method.
            int[] optResult = rearrangeOptimal(optInput);

            // Validate that both methods have placed even numbers first.
            boolean bfPass = validateEvenOdd(bfResult);
            boolean optPass = validateEvenOdd(optResult);

            System.out.println("Test case: " + Arrays.toString(testCase));
            System.out.println("Brute Force Output: " + Arrays.toString(bfResult) + " -> Pass: " + bfPass);
            System.out.println("Optimal Output    : " + Arrays.toString(optResult) + " -> Pass: " + optPass);
            System.out.println("--------------------------------------------------");
        }

        // Large Data Test: Process an array with 1 million elements.
        int n = 1000000;
        int[] largeTest = new Random().ints(n, 1, 1000).toArray();
        // Use the optimal method for large input due to its efficiency.
        long start = System.currentTimeMillis();
        rearrangeOptimal(largeTest);
        long end = System.currentTimeMillis();
        boolean largePass = validateEvenOdd(largeTest);
        System.out.println("Large Test (1M numbers): Pass: " + largePass + " | Time taken: " + (end - start) + "ms");
    }
}
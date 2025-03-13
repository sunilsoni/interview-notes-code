package com.interview.notes.code.year.y2025.march.common.test9;

public class EvenOddRearranger {

    /**
     * Rearranges the array so that even numbers come first and odd numbers at the end,
     * preserving the relative order (stable partition).
     *
     * @param arr The input array containing positive numbers.
     * @return The rearranged array with evens in front and odds at the back.
     */
    public static int[] rearrangeOptimal(int[] arr) {
        // Iterate over each element in the array.
        for (int i = 0; i < arr.length; i++) {
            // Check if the current element is even.
            if (arr[i] % 2 == 0) {
                int evenVal = arr[i]; // Store the even number temporarily.
                int j = i;
                // Shift all preceding odd elements one step to the right.
                // This creates the correct position to insert the even number,
                // preserving the order among odd numbers.
                while (j > 0 && arr[j - 1] % 2 != 0) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                // Place the even number at its new, correct position.
                arr[j] = evenVal;
            }
        }
        return arr;
    }

    /**
     * Helper method to print the array.
     *
     * @param arr The array to be printed.
     */
    public static void printArray(int[] arr) {
        System.out.println(java.util.Arrays.toString(arr));
    }

    /**
     * Main method to run tests.
     */
    public static void main(String[] args) {
        // Test case as given in the problem
        int[] input = {7, 4, 3, 8, 5, 2};
        int[] output = rearrangeOptimal(input);
        System.out.println("Input : [7, 4, 3, 8, 5, 2]");
        System.out.print("Output: ");
        printArray(output);  // Expected: [4, 8, 2, 7, 3, 5]

        // Additional test cases to cover edge cases:
        // 1. All even numbers.
        int[] testEven = {2, 4, 6, 8};
        System.out.print("All even: ");
        printArray(rearrangeOptimal(testEven));  // Expected: [2, 4, 6, 8]

        // 2. All odd numbers.
        int[] testOdd = {1, 3, 5, 7};
        System.out.print("All odd: ");
        printArray(rearrangeOptimal(testOdd));   // Expected: [1, 3, 5, 7]

        // 3. Empty array.
        int[] testEmpty = {};
        System.out.print("Empty array: ");
        printArray(rearrangeOptimal(testEmpty));   // Expected: []

        // 4. Single element.
        int[] testSingle = {2};
        System.out.print("Single element: ");
        printArray(rearrangeOptimal(testSingle));  // Expected: [2]
    }
}
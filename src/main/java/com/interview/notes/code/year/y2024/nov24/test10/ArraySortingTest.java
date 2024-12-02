package com.interview.notes.code.year.y2024.nov24.test10;

public class ArraySortingTest {

    // Method to sort character array
    public static char[] sortCharArray(char[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        // Using bubble sort for simplicity and demonstration
        // For production code, Arrays.sort() would be more efficient
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap characters
                    char temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    // Helper method to check if arrays are equal
    private static boolean areArraysEqual(char[] arr1, char[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    // Test method
    private static void runTest(String testName, char[] input, char[] expected) {
        System.out.println("\nRunning test: " + testName);
        System.out.print("Input: ");
        printArray(input);

        char[] result = sortCharArray(input.clone());

        System.out.print("Expected: ");
        printArray(expected);
        System.out.print("Actual: ");
        printArray(result);

        boolean passed = areArraysEqual(result, expected);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
    }

    // Helper method to print array
    private static void printArray(char[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        // Test Case 1: Given example
        runTest("Basic Test",
                new char[]{'a', 'l', 'e', 'p', 'p'},
                new char[]{'a', 'e', 'l', 'p', 'p'});

        // Test Case 2: Empty array
        runTest("Empty Array",
                new char[]{},
                new char[]{});

        // Test Case 3: Single element
        runTest("Single Element",
                new char[]{'x'},
                new char[]{'x'});

        // Test Case 4: Already sorted
        runTest("Already Sorted",
                new char[]{'a', 'b', 'c'},
                new char[]{'a', 'b', 'c'});

        // Test Case 5: Reverse sorted
        runTest("Reverse Sorted",
                new char[]{'z', 'y', 'x'},
                new char[]{'x', 'y', 'z'});

        // Test Case 6: Large input
        char[] largeInput = new char[1000];
        char[] largeExpected = new char[1000];
        for (int i = 0; i < 1000; i++) {
            largeInput[i] = (char) ('a' + (Math.random() * 26));
            largeExpected[i] = largeInput[i];
        }
        java.util.Arrays.sort(largeExpected);
        runTest("Large Input (1000 elements)",
                largeInput,
                largeExpected);
    }
}
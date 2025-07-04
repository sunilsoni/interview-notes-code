package com.interview.notes.code.year.y2025.June.common.test13;

public class ArrayTransformer {
    public static int transform(char[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int writeIndex = 0;

        for (int readIndex = 0; readIndex < arr.length && arr[readIndex] != '\0'; readIndex++) {
            if (arr[readIndex] == 'x') {
                arr[writeIndex++] = 'a';
                arr[writeIndex++] = 'a';
            } else if (arr[readIndex] != 'y') {
                arr[writeIndex++] = arr[readIndex];
            }
        }

        // Fill remaining positions with empty characters if any
        while (writeIndex < arr.length) {
            arr[writeIndex++] = '\0';
        }

        return writeIndex;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        char[] arr1 = {'x', 'x', 'y', 'z', 'x', '\0', '\0', '\0'};
        testTransform(arr1, "Test Case 1 - Basic case");

        // Test Case 2: All x's
        char[] arr2 = {'x', 'x', 'x', '\0', '\0', '\0'};
        testTransform(arr2, "Test Case 2 - All x's");

        // Test Case 3: All y's
        char[] arr3 = {'y', 'y', 'y', '\0', '\0'};
        testTransform(arr3, "Test Case 3 - All y's");

        // Test Case 4: Mixed characters
        char[] arr4 = {'a', 'x', 'b', 'y', 'c', 'x', '\0', '\0', '\0'};
        testTransform(arr4, "Test Case 4 - Mixed characters");

        // Test Case 5: Empty array
        char[] arr5 = {'\0', '\0', '\0'};
        testTransform(arr5, "Test Case 5 - Empty array");

        // Test Case 6: Single character
        char[] arr6 = {'x', '\0', '\0'};
        testTransform(arr6, "Test Case 6 - Single x");

        // Test Case 7: No transformation needed
        char[] arr7 = {'a', 'b', 'c', '\0', '\0'};
        testTransform(arr7, "Test Case 7 - No transformation needed");
    }

    private static void testTransform(char[] arr, String testName) {
        System.out.println("\n" + testName);
        System.out.print("Input:  ");
        printArray(arr);

        int newLength = transform(arr);

        System.out.print("Output: ");
        printArray(arr, newLength);
        System.out.println("New Length: " + newLength);
    }

    private static void printArray(char[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '\0') {
                System.out.print("''");
            } else {
                System.out.print("'" + arr[i] + "'");
            }
            if (i < arr.length - 1) System.out.print(",");
        }
        System.out.println("}");
    }

    private static void printArray(char[] arr, int length) {
        System.out.print("{");
        for (int i = 0; i < length; i++) {
            if (arr[i] == '\0') {
                System.out.print("''");
            } else {
                System.out.print("'" + arr[i] + "'");
            }
            if (i < length - 1) System.out.print(",");
        }
        System.out.println("}");
    }
}

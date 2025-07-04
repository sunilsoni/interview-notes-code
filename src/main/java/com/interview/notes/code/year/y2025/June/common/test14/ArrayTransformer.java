package com.interview.notes.code.year.y2025.June.common.test14;

public class ArrayTransformer {
    public static int transformArrayInPlace(char[] input, int length) {
        if (input == null || length == 0) {
            return 0;
        }

        // Count 'x' and 'y' to calculate new length
        int xCount = 0;
        int yCount = 0;
        for (int i = 0; i < length; i++) {
            if (input[i] == 'x') xCount++;
            if (input[i] == 'y') yCount++;
        }

        // Calculate new length
        int newLength = length + xCount - yCount;

        // Transform array from right to left
        int readIndex = length - 1;
        int writeIndex = newLength - 1;

        while (readIndex >= 0) {
            char currentChar = input[readIndex];
            if (currentChar == 'x') {
                input[writeIndex--] = 'a';
                input[writeIndex--] = 'a';
            } else if (currentChar != 'y') {
                input[writeIndex--] = currentChar;
            }
            readIndex--;
        }

        return newLength;
    }

    // Utility method to print array up to specified length
    public static void printArray(char[] arr, int length) {
        System.out.print("[");
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i]);
            if (i < length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        // Test Case 1: Basic transformation
        System.out.println("Test Case 1:");
        char[] test1 = new char[10];  // Array with extra space
        test1[0] = 'x';
        test1[1] = 'y';
        test1[2] = 'x';
        test1[3] = 'z';
        int length1 = 4;

        System.out.print("Original array: ");
        printArray(test1, length1);

        int newLength1 = transformArrayInPlace(test1, length1);
        System.out.print("Transformed array: ");
        printArray(test1, newLength1);
        System.out.println("New length: " + newLength1);
        System.out.println();

        // Test Case 2: Multiple consecutive x's and y's
        System.out.println("Test Case 2:");
        char[] test2 = new char[15];
        test2[0] = 'x';
        test2[1] = 'x';
        test2[2] = 'y';
        test2[3] = 'y';
        test2[4] = 'x';
        int length2 = 5;

        System.out.print("Original array: ");
        printArray(test2, length2);

        int newLength2 = transformArrayInPlace(test2, length2);
        System.out.print("Transformed array: ");
        printArray(test2, newLength2);
        System.out.println("New length: " + newLength2);
        System.out.println();

        // Test Case 3: No transformation needed
        System.out.println("Test Case 3:");
        char[] test3 = new char[5];
        test3[0] = 'a';
        test3[1] = 'b';
        test3[2] = 'c';
        int length3 = 3;

        System.out.print("Original array: ");
        printArray(test3, length3);

        int newLength3 = transformArrayInPlace(test3, length3);
        System.out.print("Transformed array: ");
        printArray(test3, newLength3);
        System.out.println("New length: " + newLength3);
        System.out.println();

        // Test Case 4: Only x's and y's
        System.out.println("Test Case 4:");
        char[] test4 = new char[10];
        test4[0] = 'x';
        test4[1] = 'y';
        test4[2] = 'y';
        test4[3] = 'x';
        int length4 = 4;

        System.out.print("Original array: ");
        printArray(test4, length4);

        int newLength4 = transformArrayInPlace(test4, length4);
        System.out.print("Transformed array: ");
        printArray(test4, newLength4);
        System.out.println("New length: " + newLength4);
    }
}

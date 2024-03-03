package com.interview.notes.code.months.march24.test2;

public class Add1ToArray {
    public static void main(String[] args) {
        int[] input1 = {1, 2, 3, 4};
        int[] output1 = addOne(input1);
        printArray(output1); // Expected: [1, 2, 3, 5]

        int[] input2 = {1, 2, 9, 9};
        int[] output2 = addOne(input2);
        printArray(output2); // Expected: [1, 3, 0, 0]

        int[] input3 = {9, 9, 9, 9};
        int[] output3 = addOne(input3);
        printArray(output3); // Expected: [1, 0, 0, 0, 0]
    }

    public static int[] addOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] newNumber = new int[n + 1];
        newNumber[0] = 1;
        return newNumber;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

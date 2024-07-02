package com.interview.notes.code.months.june24.test9;

public class Main {
    public static void main(String[] args) {
        int[][] examples = {
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {1, 2, 5, 3, 4},
                {2, 3, 1, 5, 6}, // Additional case for testing
                {5, 5, 5, 5, 5}, // All 5s
                {1, 5, 2, 3, 4}  // 5 appears after the first element
        };

        for (int[] numbers : examples) {
            processArray(numbers);
            System.out.println();  // Print a newline between examples
        }
    }

    private static void processArray(int[] numbers) {
        boolean invalidFlag = false;


        for (int i = 0; i < numbers.length; i++) {
            if (i == 0 && numbers[i] == 5) {
                System.out.print("5 ");
                invalidFlag = true;
            } else if (!invalidFlag) {
                if (numbers[i] == 5) {
                    invalidFlag = true;
                    System.out.print("Invalid ");
                } else {
                    System.out.print(numbers[i] + " ");
                }
            } else {
                System.out.print("Invalid ");
            }
        }
    }
}

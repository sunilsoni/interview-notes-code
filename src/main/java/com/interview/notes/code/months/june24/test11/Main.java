package com.interview.notes.code.months.june24.test11;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 5, 3, 4, 5, 6, 7, 8, 9};
        boolean[] isValid = new boolean[numbers.length];

        // Initially, mark all indices as valid
        for (int i = 0; i < isValid.length; i++) {
            isValid[i] = true;
        }

        // Mark indices as invalid if the number is 5 or the subsequent index of 5
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 5) {
                isValid[i] = false;
                if (i + 1 < numbers.length) {
                    isValid[i + 1] = false;
                }
            }
        }

        // Print the array with invalid indices marked
        for (int i = 0; i < numbers.length; i++) {
            if (isValid[i]) {
                System.out.print(numbers[i] + " ");
            } else {
                System.out.print("Invalid ");
            }
        }
    }
}

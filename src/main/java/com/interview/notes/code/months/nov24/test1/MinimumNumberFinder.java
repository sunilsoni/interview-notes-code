package com.interview.notes.code.months.nov24.test1;

public class MinimumNumberFinder {
    public static int findMinimum(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }

        int minimum = numbers[0]; // Assume the first element is the minimum

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < minimum) {
                minimum = numbers[i];
            }
        }

        return minimum;
    }

    public static void main(String[] args) {
        int[] numbers = {5, 2, 8, 1, 9, 3};
        int minNumber = findMinimum(numbers);
        System.out.println("The minimum number is: " + minNumber);
    }
}

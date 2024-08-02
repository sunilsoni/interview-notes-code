package com.interview.notes.code.months.july24.test15;

public class ArrayMean {
    public static double calculateMean(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }

        long sum = 0;
        for (int num : numbers) {
            sum += num;
        }

        return (double) sum / numbers.length;
    }

    public static void main(String[] args) {
        int[] testArray = {1, 2, 3, 4, 5};
        System.out.println("Mean: " + calculateMean(testArray));
    }
}

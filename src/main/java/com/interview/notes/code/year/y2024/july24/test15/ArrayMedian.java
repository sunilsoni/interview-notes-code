package com.interview.notes.code.year.y2024.july24.test15;

import java.util.Arrays;

public class ArrayMedian {
    public static double findMedian(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }

        Arrays.sort(numbers);
        int middleIndex = numbers.length / 2;

        if (numbers.length % 2 == 0) {
            return (numbers[middleIndex - 1] + numbers[middleIndex]) / 2.0;
        } else {
            return numbers[middleIndex];
        }
    }

    public static void main(String[] args) {
        int[] testArray = {5, 2, 8, 1, 9, 3};
        System.out.println("Median: " + findMedian(testArray));
    }
}

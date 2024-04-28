package com.interview.notes.code.months.april24.test14;

import java.util.Arrays;

public class SecondHighestElement {

    public static void main(String[] args) {
        int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}; // Example array

        // Use Java 8 stream to find the second highest element
        int secondHighest = Arrays.stream(array)
                .boxed() // Convert to Integer stream
                .sorted((a, b) -> b - a) // Sort in descending order
                .distinct() // Remove duplicates
                .skip(1) // Skip the first element (highest)
                .findFirst() // Find the second highest element
                .orElse(-1); // If array length is less than 2, return -1

        if (secondHighest != -1) {
            System.out.println("Second highest element: " + secondHighest);
        } else {
            System.out.println("Array length is less than 2.");
        }
    }
}

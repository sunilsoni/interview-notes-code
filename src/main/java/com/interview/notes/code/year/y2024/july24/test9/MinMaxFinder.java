package com.interview.notes.code.year.y2024.july24.test9;

public class MinMaxFinder {
    public static void main(String[] args) {
        int[] arr = {3, 5, 4, 1, 9}; // Your array

        // Initialize min and max with the first element of the array
        int min = arr[0];
        int max = arr[0];

        // Loop through the array
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                // Update min if current element is smaller
                min = arr[i];
            }
            if (arr[i] > max) {
                // Update max if current element is larger
                max = arr[i];
            }
        }

        // Output the results
        System.out.println("Minimum number: " + min);
        System.out.println("Maximum number: " + max);
    }
}

package com.interview.notes.code.months.sept24.test6;

public class LargestElementInArray {
    public static void main(String[] args) {
        // Sample array
        int[] numbers = {10, 5, 7, 25, 15, 30, 8};

        // Find and print the largest element
        int largest = findLargestElement(numbers);
        System.out.println("The largest element in the array is: " + largest);
    }

    public static int findLargestElement(int[] arr) {
        // Check if the array is empty
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }

        // Initialize largest with the first element
        int largest = arr[0];

        // Iterate through the array to find the largest element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }

        return largest;
    }
}

package com.interview.notes.code.tricky;

public class SingleElementFinder {
    public static void main(String[] args) {
        int[] arr = {2, 2, 3, 3, 5, 5, 9, 9, 6, 7, 7, 8, 8, 10, 10}; // Input array
        int singleElement = -1; // Initialize the variable to store the single element

        for (int i = 0; i < arr.length; i += 2) {
            // Compare consecutive elements in pairs
            if (arr[i] != arr[i + 1]) {
                // If they're not equal, we've found the single element
                singleElement = arr[i];
                break;
            }
        }

        if (singleElement == -1) {
            // If we haven't found a single element yet, it must be the last one
            singleElement = arr[arr.length - 1];
        }

        System.out.println("The single element is: " + singleElement);
    }
}

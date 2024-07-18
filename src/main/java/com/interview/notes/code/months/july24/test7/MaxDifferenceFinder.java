package com.interview.notes.code.months.july24.test7;

public class MaxDifferenceFinder {
    public static int[] findMaxDifference(int[] array) {
        // Check if the array has at least two elements
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        // Initialize variables to store the indices of the pair with the maximum difference
        int maxDiff = 0;
        int[] result = new int[2]; // This will store the pair of numbers

        // Iterate through the array from the first element to the second-to-last element
        for (int i = 0; i < array.length - 1; i++) {
            // Calculate the difference between current and next element
            int currentDiff = Math.abs(array[i] - array[i + 1]);

            // Update the maximum difference and the result pair if the current difference is greater
            if (currentDiff > maxDiff) {
                maxDiff = currentDiff;
                result[0] = array[i];
                result[1] = array[i + 1];
            }
        }

        // Return the pair with the maximum difference
        return result;
    }

    public static void main(String[] args) {
        int[] array = {10, 20, 1, 3, 100, 90};
        int[] maxDiffPair = findMaxDifference(array);
        System.out.println("The two adjacent numbers with the maximum difference are: " +
                maxDiffPair[0] + " and " + maxDiffPair[1]);
    }
}

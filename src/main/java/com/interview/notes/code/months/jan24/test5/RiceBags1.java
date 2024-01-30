package com.interview.notes.code.months.jan24.test5;

import java.util.Arrays;

public class RiceBags1 {
    public static void main(String[] args) {

        int[] riceBags1 = {625, 4, 2, 5, 25};
        System.out.println(findPerfectSetSize(riceBags1)); // Example 1

        int[] riceBags2 = {7, 4, 8, 9};
        System.out.println(findPerfectSetSize(riceBags2)); // Example 2


        int[] riceBags3 = {4, 7, 4, 8, 9};//expected -1
        System.out.println(findPerfectSetSize(riceBags3)); // Example 2
    }


    // A method that takes an array of rice bags and returns the size of the largest possible perfect set
    public static int findPerfectSetSize(int[] riceBags) {
        // If the array is null or has less than two elements, return -1
        if (riceBags == null || riceBags.length < 2) {
            return -1;
        }

        // Sort the array in ascending order
        Arrays.sort(riceBags);

        // Initialize the maximum size and the current size to 0
        int maxSize = 0;
        int currSize = 0;

        // Loop through the array from the second element
        for (int i = 1; i < riceBags.length; i++) {
            // If the current element satisfies the condition with the previous element, increment the current size
            if (riceBags[i] - riceBags[i - 1] == i - (i - 1)) {
                currSize++;
            } else {
                // Otherwise, reset the current size to 0
                currSize = 0;
            }

            // Update the maximum size if the current size is larger
            maxSize = Math.max(maxSize, currSize);
        }

        // If the maximum size is 0, it means no perfect set was found, so return -1
        // Otherwise, add 2 to the maximum size to account for the first and the last element of the perfect set
        return maxSize == 0 ? -1 : maxSize + 2;
    }
}

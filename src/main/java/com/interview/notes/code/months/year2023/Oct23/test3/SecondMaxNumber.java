package com.interview.notes.code.months.year2023.Oct23.test3;

import java.util.Arrays;
import java.util.OptionalInt;

public class SecondMaxNumber {
    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 9, 2, 8, 7};

        // Step 1: Convert the array to a Stream
        // Step 2: Use distinct() to eliminate duplicates
        // Step 3: Sort the elements
        // Step 4: Skip all but the second max element and find it
        OptionalInt secondMax = Arrays.stream(arr)
                .distinct()  // Remove duplicates
                .sorted()     // Sort the array
                .skip(arr.length - 2)  // Skip all but the second max
                .findFirst();  // Retrieve the second max element

        // Check if a second max exists and display it
        if (secondMax.isPresent()) {
            System.out.println("The second maximum number is: " + secondMax.getAsInt());
        } else {
            System.out.println("A second maximum number doesn't exist.");
        }
    }

    public static int findSecondMaxNumberWithoutSorting(int[] array) {
        int max1 = array[0];
        int max2 = array[1];

        int i = 2;
        while (i < array.length) {
            if (array[i] > max1) {
                max2 = max1;
                max1 = array[i];
            } else if (array[i] > max2 && array[i] <= max1) {
                max2 = array[i];
            }

            i += 2;
        }

        // Handle the case where the array has an odd number of elements.
        if (i < array.length) {
            if (array[i] > max1) {
                max2 = max1;
                max1 = array[i];
            } else if (array[i] > max2 && array[i] <= max1) {
                max2 = array[i];
            }
        }

        return max2;
    }

}

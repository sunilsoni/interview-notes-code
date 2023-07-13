package com.interview.notes.code.july23.test3;

import java.util.HashSet;
import java.util.Set;

public class CommonElements {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {4, 5, 6, 7, 8};

        // Create a HashSet to store the common elements
        Set<Integer> commonElements = new HashSet<>();

        // Iterate over the elements of the first array
        for (int num : array1) {
            // Check if the element exists in the second array
            for (int i : array2) {
                if (num == i) {
                    commonElements.add(num);
                    break;
                }
            }
        }

        // Print the common elements
        System.out.println("Common Elements:");
        for (int num : commonElements) {
            System.out.println(num);
        }
    }
}

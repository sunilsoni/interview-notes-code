package com.interview.notes.code.months.sept24.test15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StoringBoxes {


    public static void main(String[] args) {
        // Example usage:
        List<Integer> example1 = Arrays.asList(1, 2, 3, 5, 6, 8);
        System.out.println(solve(example1));  // Output should be 3

        List<Integer> example2 = Arrays.asList(2, 1, 11, 3);
        System.out.println(solve(example2));  // Output should be 2
    }

    /*
     * Function to solve the problem.
     * Takes a list of box sizes and returns the number of sets that can be formed.
     */
    public static int solve(List<Integer> a) {
        // If no boxes, no sets can be made
        if (a == null || a.size() == 0) return 0;

        // Sort the list of box sizes
        Collections.sort(a);

        // Initialize the number of sets
        int sets = 1;  // At least one set will be there if there's at least one box

        // Iterate through the sorted list and count the number of sets
        for (int i = 1; i < a.size(); i++) {
            // If the current box size is not consecutive to the previous one
            if (a.get(i) - a.get(i - 1) > 1) {
                sets++;  // Start a new set
            }
        }

        return sets;
    }
}

package com.interview.notes.code.months.sept24.test14;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Storekeeper {

    public static int solve(List<Integer> a) {
        // Sort the list to group numbers with a difference of 1
        Collections.sort(a);

        int sets = 1;  // Start with at least one set
        for (int i = 1; i < a.size(); i++) {
            // If the difference between consecutive numbers is more than 1, it's a new set
            if (a.get(i) - a.get(i - 1) > 1) {
                sets++;
            }
        }

        return sets;
    }

    public static void main(String[] args) {
        // Example usage:
        List<Integer> example1 = Arrays.asList(1, 2, 3, 5, 6, 8);
        System.out.println(solve(example1));  // Output should be 3

        List<Integer> example2 = Arrays.asList(2, 1, 11, 3);
        System.out.println(solve(example2));  // Output should be 2
    }
}

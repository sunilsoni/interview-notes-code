package com.interview.notes.code.months.march24.test13;

import java.util.*;

public class RussianDolls {

    public static int solve(List<Integer> doll) {
        Collections.sort(doll); // Sort the dolls in ascending order of their sizes
        int n = doll.size();
        int count = 0; // To keep track of the minimum number of dolls left
        int prev = 0; // To keep track of the previous doll size

        for (int i = n - 1; i >= 0; i--) {
            if (doll.get(i) > prev) {
                count++;
                prev = doll.get(i); // Update the previous doll size
            }
        }

        return count;
    }
    
    public static void main(String[] args) {
        // Example 1
        List<Integer> dolls1 = Arrays.asList(2, 2, 3, 3);
        System.out.println(solve(dolls1)); // Expected Output: 2

        // Example 2
        List<Integer> dolls2 = Arrays.asList(1, 2, 2, 3, 4, 5);
        System.out.println(solve(dolls2)); // Expected Output: 2
    }
}

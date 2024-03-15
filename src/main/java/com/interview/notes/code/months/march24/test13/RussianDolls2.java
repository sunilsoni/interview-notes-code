package com.interview.notes.code.months.march24.test13;

import java.util.*;

public class RussianDolls2 {

    public static int solve(List<Integer> dolls) {
        Collections.sort(dolls, Collections.reverseOrder()); // Sort in descending order
        int remainingDolls = 0;
        int currentLargestSize = Integer.MAX_VALUE;
        
        for (int size : dolls) {
            if (size < currentLargestSize) {
                currentLargestSize = size;
                remainingDolls++;
            }
        }
        
        return remainingDolls;
    }
    
    public static void main(String[] args) {
        // Example 1
        List<Integer> dolls1 = Arrays.asList(2, 2, 3, 3);
        System.out.println(solve(dolls1)); // Output: 2

        // Example 2
        List<Integer> dolls2 = Arrays.asList(1, 2, 2, 3, 4, 5);
        System.out.println(solve(dolls2)); // Output: 2
    }
}

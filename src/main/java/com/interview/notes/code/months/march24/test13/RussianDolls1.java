package com.interview.notes.code.months.march24.test13;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RussianDolls1 {

    public static int solve(List<Integer> dolls) {
        // Sort the dolls in ascending order (smallest to largest)
        Collections.sort(dolls);

        // This variable keeps track of the largest nesting doll size encountered so far
        int largestSize = Integer.MIN_VALUE;

        // Iterate through the sorted dolls
        for (int dollSize : dolls) {
            // If the current doll size is larger than the largest nesting doll,
            // it can hold other dolls inside. Update largestSize.
            if (dollSize > largestSize) {
                largestSize = dollSize;
            }
        }

        // The number of remaining dolls is the number of dolls that couldn't fit
        // inside any other doll (dolls with size greater than largestSize).
        // We count the occurrences of the largest size doll.
        int remainingDolls = 0;
        for (int dollSize : dolls) {
            if (dollSize == largestSize) {
                remainingDolls++;
            }
        }

        return remainingDolls;
    }

    public static void main(String[] args) {
        List<Integer> dolls1 = Arrays.asList(2, 2, 3, 3);
        System.out.println(solve(dolls1)); // Output: 2

        List<Integer> dolls2 = Arrays.asList(1, 2, 2, 3, 4, 5);
        System.out.println(solve(dolls2)); // Output: 2
    }
}

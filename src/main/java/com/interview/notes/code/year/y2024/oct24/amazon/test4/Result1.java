package com.interview.notes.code.year.y2024.oct24.amazon.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Result1 {
    public static int getMinimumOffBulbs(List<Integer> brightness) {
        // Sort the brightness list to maximize the chances of keeping bulbs ON
        Collections.sort(brightness);

        long cumulativeSum = 0;  // Variable to keep track of the cumulative sum
        int offBulbs = 0;        // Counter for OFF bulbs

        for (int b : brightness) {
            if (cumulativeSum < b) {
                // If cumulative sum is less than the current brightness, the bulb remains ON
                cumulativeSum += b;
            } else {
                // Otherwise, the bulb turns OFF
                offBulbs++;
            }
        }

        return offBulbs;  // Return the total number of bulbs that are OFF
    }

    // Main method to test the function with sample test cases
    public static void main(String[] args) {
        // Sample input from the problem statement
        List<Integer> brightness1 = Arrays.asList(3, 4, 2);
        System.out.println(getMinimumOffBulbs(brightness1)); // Expected output: 1

        List<Integer> brightness2 = Arrays.asList(1, 5, 10, 20);
        System.out.println(getMinimumOffBulbs(brightness2)); // Expected output: 0

        // Additional test case to test large input (stress test)
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            largeInput.add(i);
        }
        System.out.println(getMinimumOffBulbs(largeInput));  // Expected output should be 0 as no bulb will turn off
    }
}

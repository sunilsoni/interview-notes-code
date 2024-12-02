package com.interview.notes.code.year.y2024.oct24.amazon.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Result {

    /*
     * Complete the 'getMinimumOffBulbs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY brightness as parameter.
     */
    public static int getMinimumOffBulbs(List<Integer> brightness) {
        // Step 1: Sort the brightness array in ascending order
        Collections.sort(brightness);

        // Step 2: Initialize the sum of brightness of previous bulbs
        int sum = 0;
        int offBulbs = 0;

        // Step 3: Traverse the sorted brightness array
        for (int i = 0; i < brightness.size(); i++) {
            // If the sum of previous bulbs is greater than or equal to the current bulb's brightness, it will turn off
            if (sum >= brightness.get(i)) {
                offBulbs++;
            } else {
                // Otherwise, add the current bulb's brightness to the sum
                sum += brightness.get(i);
            }
        }

        // Step 4: Return the number of OFF bulbs
        return offBulbs;
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // Test case 1: Example from the problem statement
        List<Integer> brightness1 = Arrays.asList(1, 5, 10, 20);
        System.out.println("Test Case 1: " + (getMinimumOffBulbs(brightness1) == 0 ? "PASS" : "FAIL"));

        // Test case 2: Example from the problem statement
        List<Integer> brightness2 = Arrays.asList(2, 1, 3, 4, 3);
        System.out.println("Test Case 2: " + (getMinimumOffBulbs(brightness2) == 2 ? "PASS" : "FAIL"));

        // Additional test case 3: All bulbs have the same brightness
        List<Integer> brightness3 = Arrays.asList(5, 5, 5, 5, 5);
        System.out.println("Test Case 3: " + (getMinimumOffBulbs(brightness3) == 4 ? "PASS" : "FAIL"));

        // Additional test case 4: Only one bulb
        List<Integer> brightness4 = Arrays.asList(10);
        System.out.println("Test Case 4: " + (getMinimumOffBulbs(brightness4) == 0 ? "PASS" : "FAIL"));

        // Additional test case 5: Large input with increasing brightness
        List<Integer> brightness5 = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            brightness5.add(i);
        }
        long startTime = System.currentTimeMillis();
        System.out.println("Test Case 5: " + (getMinimumOffBulbs(brightness5) == 0 ? "PASS" : "FAIL"));
        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + "ms");
    }
}

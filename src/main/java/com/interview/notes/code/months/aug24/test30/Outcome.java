package com.interview.notes.code.months.aug24.test30;

import java.util.Arrays;
import java.util.List;

class Outcome {
    public static int solve(List<Integer> ar) {
        int n = ar.size();
        int maxOnes = 0;
        int currentOnes = 0;
        int maxFlip = 0;
        int currentFlip = 0;

        for (int i = 0; i < n; i++) {
            if (ar.get(i) == 1) {
                currentOnes++;
            }

            if (ar.get(i) == 0) {
                currentFlip++;
            } else {
                currentFlip--;
            }

            if (currentFlip < 0) {
                currentFlip = 0;
            }

            maxFlip = Math.max(maxFlip, currentFlip);
        }

        return currentOnes + maxFlip;
    }

    public static void main(String[] args) {
        // Test case 1
        List<Integer> test1 = Arrays.asList(0, 1, 0, 0, 1);
        int result1 = solve(test1);
        System.out.println("Test case 1:");
        System.out.println("Input: " + test1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: 4");
        System.out.println("Pass: " + (result1 == 4));
        System.out.println();

        // Test case 2
        List<Integer> test2 = Arrays.asList(1, 0, 0, 1, 0, 0);
        int result2 = solve(test2);
        System.out.println("Test case 2:");
        System.out.println("Input: " + test2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: 5");
        System.out.println("Pass: " + (result2 == 5));
    }
}

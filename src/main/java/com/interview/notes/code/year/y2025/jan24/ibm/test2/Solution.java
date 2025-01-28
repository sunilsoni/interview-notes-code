package com.interview.notes.code.year.y2025.jan24.ibm.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static int solve(List<Integer> arr) {
        int rounds = 0;
        int n = arr.size();

        // Continue until first n-1 elements are multiples of 3
        while (!checkFirstNMinusOneMultiplesOf3(arr)) {
            rounds++;
            // Create new list to store updated values
            List<Integer> newArr = new ArrayList<>(arr);

            // Process pairs
            for (int i = 0; i < n - 1; i++) {
                long product = (long) arr.get(i) * arr.get(i + 1);
                // If product divisible by 3, update current element
                if (product % 3 == 0) {
                    // Handle large numbers by taking modulo
                    newArr.set(i, (int) (product % 1000000007));
                }
            }

            arr = newArr;

            // Safety check to prevent infinite loops
            if (rounds > 1000) return -1;
        }

        return rounds;
    }

    private static boolean checkFirstNMinusOneMultiplesOf3(List<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) % 3 != 0) return false;
        }
        return true;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1
        List<Integer> test1 = Arrays.asList(34, 56, 20, 90, 100);
        System.out.println("Test 1 Result: " + solve(test1) + " (Expected: 3)");

        // Test Case 2
        List<Integer> test2 = Arrays.asList(1, 333, 222, 22);
        System.out.println("Test 2 Result: " + solve(test2) + " (Expected: 1)");

        // Edge Case: Already multiples of 3
        List<Integer> test3 = Arrays.asList(3, 6, 9, 12, 5);
        System.out.println("Test 3 Result: " + solve(test3) + " (Expected: 0)");

        // Edge Case: Minimum size array
        List<Integer> test4 = Arrays.asList(1, 2);
        System.out.println("Test 4 Result: " + solve(test4) + " (Expected: 1)");
    }
}

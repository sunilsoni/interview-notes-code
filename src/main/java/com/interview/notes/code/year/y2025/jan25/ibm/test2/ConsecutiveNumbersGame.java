package com.interview.notes.code.year.y2025.jan25.ibm.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsecutiveNumbersGame {
    public static void main(String[] args) {
        // Provided Test Cases
        List<Integer> test1 = Arrays.asList(34, 56, 20, 90, 100); // Output: 3
        List<Integer> test2 = Arrays.asList(1, 333, 222, 22);     // Output: 1

        // New Examples
        List<Integer> test3 = Arrays.asList(3, 9, 27, 81, 243);   // All divisible by 3, Output: 0
        List<Integer> test4 = Arrays.asList(5);                   // Single element, Output: 0
        List<Integer> test5 = Arrays.asList(2, 4, 6, 8, 10);      // None divisible by 3, Output: Multiple Rounds
        List<Integer> test6 = Arrays.asList(9, 12, 15, 18);       // Mixed divisible, Output: Few Rounds
        List<Integer> test7 = Arrays.asList(3, 6, 2, 1, 9);       // Mixed divisible and non-divisible, Output: Multiple Rounds

        // Edge Case: Large Input
        List<Integer> test8 = new ArrayList<>();                  // Very large input
        for (int i = 0; i < 1_000_000; i++) test8.add(2);         // All 2s, Output: Large Rounds

        // Run Tests
        System.out.println("Test 1 Result: " + solve(new ArrayList<>(test1))); // Expected: 3
        System.out.println("Test 2 Result: " + solve(new ArrayList<>(test2))); // Expected: 1
        System.out.println("Test 3 Result: " + solve(new ArrayList<>(test3))); // Expected: 0
        System.out.println("Test 4 Result: " + solve(new ArrayList<>(test4))); // Expected: 0
        System.out.println("Test 5 Result: " + solve(new ArrayList<>(test5))); // Expected: Multiple rounds
        System.out.println("Test 6 Result: " + solve(new ArrayList<>(test6))); // Expected: Few rounds
        System.out.println("Test 7 Result: " + solve(new ArrayList<>(test7))); // Expected: Multiple rounds
        System.out.println("Test 8 Result: " + solve(new ArrayList<>(test8))); // Expected: Handles without errors
    }

    public static int solve(List<Integer> arr) {
        int rounds = 0;
        int n = arr.size();

        if (n == 1) return 0; // Single element edge case

        while (true) {
            boolean allMultiplesOf3 = true;

            for (int i = 0; i < n - 1; i++) {
                int product = arr.get(i) * arr.get(i + 1);
                if (product % 3 == 0) {
                    arr.set(i, product);
                } else {
                    allMultiplesOf3 = false;
                }
            }

            // Check if first n-1 elements are multiples of 3
            for (int i = 0; i < n - 1; i++) {
                if (arr.get(i) % 3 != 0) {
                    allMultiplesOf3 = false;
                    break;
                }
            }

            if (allMultiplesOf3) break;
            rounds++;
        }

        return rounds;
    }
}

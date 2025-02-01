package com.interview.notes.code.year.y2025.jan25.ibm.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class ConsecutiveNumbersGame {
    // Main method for testing and running the solution
    public static void main(String[] args) {
        // Test Case 1
        List<Integer> arr1 = Arrays.asList(34, 56, 20, 90, 100);
        System.out.println("Test Case 1: " + solve(arr1)); // Expected: 3

        // Test Case 2
        List<Integer> arr2 = Arrays.asList(1, 333, 222, 22);
        System.out.println("Test Case 2: " + solve(arr2)); // Expected: 1

        // Edge Case: All numbers already divisible by 3
        List<Integer> arr3 = Arrays.asList(3, 6, 9, 12);
        System.out.println("Test Case 3: " + solve(arr3)); // Expected: 0

        // Large Input Case
        List<Integer> largeArr = generateLargeTestCase(1000);
        System.out.println("Large Input Case: " + solve(largeArr));
    }

    // Solution method to solve the Consecutive Numbers Game
    public static int solve(List<Integer> arr) {
        // Create a mutable copy of the input list
        List<Integer> nums = new ArrayList<>(arr);
        int rounds = 0;

        // Continue until first n-1 elements are divisible by 3
        while (!areFirstElementsDivisibleByThree(nums)) {
            nums = processRound(nums);
            rounds++;
        }

        return rounds;
    }

    // Process a single round of the game
    private static List<Integer> processRound(List<Integer> arr) {
        List<Integer> result = new ArrayList<>(arr);

        // Process pairs of numbers
        for (int i = 0; i < arr.size() - 1; i++) {
            int product = arr.get(i) * arr.get(i + 1);

            // Replace with product if divisible by 3
            if (product % 3 == 0) {
                result.set(i, product);
            }
        }

        return result;
    }

    // Check if first n-1 elements are divisible by 3
    private static boolean areFirstElementsDivisibleByThree(List<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) % 3 != 0) {
                return false;
            }
        }
        return true;
    }

    // Generate a large test case for performance testing
    private static List<Integer> generateLargeTestCase(int size) {
        Random random = new Random();
        List<Integer> largeArr = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            largeArr.add(random.nextInt(1000) + 1);
        }

        return largeArr;
    }
}
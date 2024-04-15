package com.interview.notes.code.months.april24.test6;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Example inputs (replace with your actual test cases)
        List<Integer> test1 = new ArrayList<>(List.of(3, 4, 1, 6, 2));
        List<Integer> test2 = new ArrayList<>(List.of(3, 2, 1));
        List<Integer> test3 = new ArrayList<>(List.of(3, 5, 2, 3));
        List<Integer> test4 = new ArrayList<>(List.of(1, 1, 1));  // Edge case: all elements equal

        // Test cases with large inputs (adjust values as needed)
        List<Integer> largeTest1 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeTest1.add((int) (Math.random() * 1000000000));  // Random values within range
        }

        List<Integer> largeTest2 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeTest2.add(1000000000);  // Edge case: all elements same (large value)
        }

        // Call the function with different test cases
        System.out.println("Test 1 (Sample Input): " + makePowerNonDecreasing(test1));
        System.out.println("Test 2 (Sample Input): " + makePowerNonDecreasing(test2));
        System.out.println("Test 3 (Sample Input): " + makePowerNonDecreasing(test3));
        System.out.println("Test 4 (Edge case: all equal): " + makePowerNonDecreasing(test4));
        System.out.println("Large Test 1: " + makePowerNonDecreasing(largeTest1));
        System.out.println("Large Test 2 (Edge case: all large and equal): " + makePowerNonDecreasing(largeTest2));
    }

    public static long makePowerNonDecreasing(List<Integer> power) {
        long sum = 0;
        int n = power.size();

        // Input validation (optional)
        if (n < 1 || n > 100000 || power.stream().anyMatch(val -> val < 1 || val > 1000000000)) {
            throw new IllegalArgumentException("Invalid input size or power values");
        }

        for (int i = 1; i < n; i++) {
            // Check if current element is less than the previous one
            if (power.get(i) < power.get(i - 1)) {
                // Calculate the difference needed to make it non-decreasing
                int diff = power.get(i - 1) - power.get(i);
                sum += diff;
            }
        }

        return sum;
    }
}

package com.interview.notes.code.year.y2025.march.city.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HugeSale {

    /**
     * Calculates the maximum amount of money John can earn.
     *
     * @param M   maximum number of items John can carry
     * @param arr list of prices for available items
     * @return maximum amount of money John can earn
     */
    public static int getAmountOfMoney(int M, List<Integer> arr) {
        // Sort the prices in ascending order (most negative first)
        Collections.sort(arr);

        int totalEarned = 0;
        int itemsTaken = 0;

        // Take up to M items, but only if they have negative prices (earn money)
        for (int price : arr) {
            if (itemsTaken >= M || price >= 0) {
                break; // Stop if we've taken M items or if remaining items cost money
            }

            // Add the absolute value of negative price (what John earns)
            totalEarned += Math.abs(price);
            itemsTaken++;
        }

        return totalEarned;
    }

    /**
     * Test method to verify the solution with the provided examples.
     */
    public static void testSolution() {
        // Test Case 1
        int M1 = 1;
        List<Integer> arr1 = new ArrayList<>();
        arr1.add(-1);
        arr1.add(-10);
        int expected1 = 10;
        int result1 = getAmountOfMoney(M1, arr1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL") +
                " (Expected: " + expected1 + ", Got: " + result1 + ")");

        // Test Case 2
        int M2 = 2;
        List<Integer> arr2 = new ArrayList<>();
        arr2.add(10);
        arr2.add(20);
        arr2.add(30);
        int expected2 = 0;
        int result2 = getAmountOfMoney(M2, arr2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL") +
                " (Expected: " + expected2 + ", Got: " + result2 + ")");

        // Additional Test Case: Mixed prices
        int M3 = 3;
        List<Integer> arr3 = new ArrayList<>();
        arr3.add(5);
        arr3.add(-15);
        arr3.add(-7);
        arr3.add(20);
        arr3.add(-3);
        int expected3 = 25; // Should take -15, -7, and -3 = 25
        int result3 = getAmountOfMoney(M3, arr3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "PASS" : "FAIL") +
                " (Expected: " + expected3 + ", Got: " + result3 + ")");

        // Edge Case: Large number of items
        int M4 = 50;
        List<Integer> arr4 = new ArrayList<>();
        int expected4 = 0;
        for (int i = 0; i < 100; i++) {
            // Add alternating negative and positive values
            int value = (i % 2 == 0) ? -(i + 1) : (i + 1);
            arr4.add(value);
            if (i % 2 == 0 && i < 100) {
                expected4 += (i + 1);
            }
        }
        expected4 = Math.min(expected4, 1275); // 50 negative items from -1 to -99 in steps of 2
        int result4 = getAmountOfMoney(M4, arr4);
        System.out.println("Test Case 4 (Large Data): " + (result4 == expected4 ? "PASS" : "FAIL") +
                " (Expected: " + expected4 + ", Got: " + result4 + ")");
    }

    public static void main(String[] args) {
        testSolution();
    }
}

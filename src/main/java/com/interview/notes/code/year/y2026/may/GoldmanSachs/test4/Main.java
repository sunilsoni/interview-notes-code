package com.interview.notes.code.year.y2026.may.GoldmanSachs.test4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main { // Main class because Java program starts from a class

    static int maxDistance(int[] a) { // Method to find maximum distance between same numbers

        Map<Integer, Integer> first = new HashMap<>(); // Stores each number's first index

        int max = 0; // Stores final maximum distance, default 0 if no duplicate found

        for (int i = 0; i < a.length; i++) { // Loop through every array element using index

            int num = a[i]; // Get current number from array

            if (!first.containsKey(num)) { // If this number is seen first time

                first.put(num, i); // Save its first index for future distance calculation

            } else { // If this number already appeared before

                int distance = i - first.get(num); // Current index minus first index gives distance

                max = Math.max(max, distance); // Keep the bigger distance as answer
            }
        }

        return max; // Return maximum distance found
    }

    static void test(int[] input, int expected) { // Simple test method without JUnit

        int actual = maxDistance(input); // Call our logic and get actual answer

        String result = actual == expected ? "PASS" : "FAIL"; // Compare actual and expected result

        System.out.println(result + " | input=" + Arrays.toString(input)
                + " | expected=" + expected + " | actual=" + actual); // Print test result
    }

    public static void main(String[] args) { // Program starts from main method

        test(new int[]{1, 9, 2, 4, 3, 2, 1}, 6); // Given test case, 1 has max distance 6

        test(new int[]{1, 2, 3, 4}, 0); // No duplicate, so answer is 0

        test(new int[]{5}, 0); // Single element, no pair possible

        test(new int[]{7, 7, 7, 7}, 3); // Same number from index 0 to 3, distance 3

        test(new int[]{4, 1, 4, 2, 1, 4}, 5); // Number 4 appears from index 0 to 5

        test(new int[]{}, 0); // Empty array, answer should be 0

        int[] large = new int[100000]; // Create large array to test performance

        Arrays.fill(large, 1); // Fill all places with same number 1

        test(large, 99999); // Maximum distance is last index minus first index
    }
}
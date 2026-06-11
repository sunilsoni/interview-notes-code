package com.interview.notes.code.year.y2026.june.common.test6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class ThirdHighest { // Main class

    static Optional<Integer> thirdHighest(int[] arr) { // Method returns 3rd highest safely
        return Arrays.stream(arr) // Convert int array to stream
                .boxed() // Convert int to Integer
                .distinct() // Remove duplicate numbers
                .sorted(Comparator.reverseOrder()) // Sort high to low
                .skip(2) // Skip 1st and 2nd highest
                .findFirst(); // Get 3rd highest
    }

    static void test(int[] arr, Integer expected) { // Simple test method
        var actual = thirdHighest(arr).orElse(null); // Get result or null
        System.out.println(Objects.equals(actual, expected) // Compare result
                ? "PASS" 
                : "FAIL expected=" + expected + " actual=" + actual);
    }

    public static void main(String[] args) { // Program starts here
        test(new int[]{1, 10, 5, 13, 21, 15, 17, 19}, 17); // Normal case
        test(new int[]{5, 5, 4, 3, 2}, 3); // Duplicate case
        test(new int[]{1, 2}, null); // Less than 3 distinct numbers
        test(new int[]{-1, -5, -2, -3}, -3); // Negative numbers
        test(new int[]{100, 90, 80, 70}, 80); // Already descending
    }
}
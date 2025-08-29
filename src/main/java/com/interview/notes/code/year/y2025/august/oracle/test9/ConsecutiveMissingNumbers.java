package com.interview.notes.code.year.y2025.august.oracle.test9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsecutiveMissingNumbers {
    public static void main(String[] args) {
        // Input array
        int[] numbers = {3, 2, 7, 1, 4, 8};

        // Step 1: Find min and max using Streams
        int min = Arrays.stream(numbers).min().getAsInt();  // smallest number
        int max = Arrays.stream(numbers).max().getAsInt();  // largest number

        // Step 2: Put all numbers in a HashSet for quick lookup
        Set<Integer> set = Arrays.stream(numbers).boxed().collect(Collectors.toSet());

        // Step 3: Traverse from min to max and find missing numbers
        List<Integer> missing = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (!set.contains(i)) {   // number is missing
                missing.add(i);
            }
        }

        // Step 4: Find consecutive missing numbers
        for (int i = 0; i < missing.size() - 1; i++) {
            if (missing.get(i) + 1 == missing.get(i + 1)) {
                System.out.println("Consecutive missing numbers: " + missing.get(i) + ", " + missing.get(i + 1));
                break; // stop after finding first pair
            }
        }
    }
}
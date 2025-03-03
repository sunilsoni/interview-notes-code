package com.interview.notes.code.year.y2025.feb.common.test1;

import java.util.HashSet;
import java.util.Set;

public class UniqueMultiplesFinder {
    public static void main(String[] args) {
        Set<Integer> uniqueMultiples = new HashSet<>();

        // Find all multiples
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0 || i % 3 == 0 || i % 4 == 0 || i % 5 == 0) {
                uniqueMultiples.add(i);
            }
        }

        // Calculate sum of unique multiples
        int sum = uniqueMultiples.stream().mapToInt(Integer::intValue).sum();

        System.out.println("Unique multiples: " + uniqueMultiples);
        System.out.println("Sum of unique multiples: " + sum);
    }
}

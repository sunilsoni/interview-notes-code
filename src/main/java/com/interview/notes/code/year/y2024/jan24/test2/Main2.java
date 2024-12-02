package com.interview.notes.code.year.y2024.jan24.test2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(2, 4, 5, 19, 18, 40, 13, 6);

        List<Integer> highestValues = integers.stream()
                .distinct() // Remove duplicates if any
                .sorted(Comparator.reverseOrder()) // Sort in descending order
                .skip(2) // Skip the first two (3rd highest remains)
                .limit(3) // Limit to the next three (3rd, 4th, and 5th)
                .collect(Collectors.toList());

        if (highestValues.size() >= 3) {
            System.out.println("3rd highest: " + highestValues.get(0));
            System.out.println("4th highest: " + highestValues.get(1));
            System.out.println("5th highest: " + highestValues.get(2));
        } else {
            System.out.println("List does not contain at least 3 distinct elements.");
        }
    }
}

package com.interview.notes.code.year.y2025.June.amazon.test15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DuplicateFinder {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 5, 5, 6, 1);

        // Find duplicates
        List<Integer> duplicates = numbers.stream()
                .filter(i -> Collections.frequency(numbers, i) > 1)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Duplicates: " + duplicates); // Output: [1, 2, 5]
    }
}

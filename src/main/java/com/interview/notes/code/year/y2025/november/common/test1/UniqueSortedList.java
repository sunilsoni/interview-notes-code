package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UniqueSortedList {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 3, 8, 3, 1, 5, 9, 2);

        List<Integer> uniqueSorted = numbers.stream()
                .distinct()         // Remove duplicates
                .sorted()           // Sort in natural order
                .collect(Collectors.toList()); // Collect to List

        System.out.println(uniqueSorted); // Output: [1, 2, 3, 5, 8, 9]
    }
}

package com.interview.notes.code.year.y2025.June.common.test7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);

// Sort in ascending order
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());

// Sort in descending order
        List<Integer> reverseSorted = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

    }
}

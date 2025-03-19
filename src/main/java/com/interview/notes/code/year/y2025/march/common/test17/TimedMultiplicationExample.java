package com.interview.notes.code.year.y2025.march.common.test17;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TimedMultiplicationExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        long startTime = System.currentTimeMillis();

        List<Integer> result = numbers.stream()
                .peek(num -> System.out.println("→ Input: " + num))
                .filter(num -> num % 2 == 0)
                .peek(num -> System.out.println("  Filtered: " + num))
                .map(num -> num * 10)
                .peek(num -> System.out.println("  ✓ Output: " + num))
                .collect(Collectors.toList());

        long endTime = System.currentTimeMillis();

        System.out.println("\nResults:");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Original numbers: " + numbers);
        System.out.println("Multiplied even numbers: " + result);
    }
}

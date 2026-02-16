package com.interview.notes.code.year.y2026.feb.USTechSolutions.test2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 != 0)          // 1. Filter odd numbers
                .map(n -> (n * n) / 2)            // 2. Square and divide by 2
                .sorted(Comparator.reverseOrder())// 3. Reverse order
                .collect(Collectors.toList());

        System.out.println(result);
    }
}

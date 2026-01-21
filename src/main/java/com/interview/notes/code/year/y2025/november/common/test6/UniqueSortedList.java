package com.interview.notes.code.year.y2025.november.common.test6;

import java.util.Arrays;
import java.util.List;

public class UniqueSortedList {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 3, 9, 3, 1, 9, 7, 5);

        List<Integer> result =
                numbers.stream()
                        .distinct()
                        .sorted()
                        .toList();

        System.out.println(result);
    }
}

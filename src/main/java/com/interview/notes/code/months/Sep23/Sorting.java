package com.interview.notes.code.months.Sep23;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorting {
    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(3, 2, 5, 8, 34, 55, 9);

        List<Integer> result = input.stream()
                                    .sorted(Comparator.comparingInt(i -> i % 2))
                                    .collect(Collectors.toList());

        System.out.println(result); // [2, 8, 34, 3, 5, 55, 9]
    }
}

package com.interview.notes.code.months.year2023.Aug23.test1;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] A) {
        // Use Java 8 streams to create a frequency map, where the key is the number and the value is its count
        return Arrays.stream(A)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                // Filter the numbers that only occur once
                .filter(entry -> entry.getValue() == 1)
                // Sum the unique numbers
                .mapToInt(entry -> entry.getKey())
                .sum();
    }

    public int solution1(int[] A) {
        // Use Java 8 streams to create a frequency map, where the key is the number and the value is its count
        return Arrays.stream(A)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                // Filter the numbers that only occur once
                .filter(entry -> entry.getValue() == 1)
                // Sum the unique numbers
                .mapToInt(entry -> entry.getKey())
                .sum();
    }
}

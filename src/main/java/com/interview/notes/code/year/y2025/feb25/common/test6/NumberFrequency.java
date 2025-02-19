package com.interview.notes.code.year.y2025.feb25.common.test6;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NumberFrequency {
    public static void main(String[] args) {
        // Sample list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 1, 3, 3, 4, 5, 4, 5, 6);

        // Group the numbers and count the frequency of each number
        Map<Integer, Long> frequencyMap = numbers.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Print the frequency of each number
        frequencyMap.forEach((number, count) -> 
            System.out.println("Number " + number + " appears " + count + " times")
        );
    }
}
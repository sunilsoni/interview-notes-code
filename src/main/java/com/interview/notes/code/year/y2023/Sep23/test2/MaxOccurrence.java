package com.interview.notes.code.year.y2023.Sep23.test2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaxOccurrence {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 7, 7, 9, 7);

        Optional<Map.Entry<Integer, Long>> maxEntry = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        if (maxEntry.isPresent()) {
            System.out.println("Number with max occurrences: " + maxEntry.get().getKey());
            System.out.println("Occurrences: " + maxEntry.get().getValue());
        } else {
            System.out.println("List is empty");
        }
    }
}

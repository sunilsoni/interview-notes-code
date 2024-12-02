package com.interview.notes.code.year.y2023.Sep23.test2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaxOccurrences {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 5, 5, 7, 7, 9, 7);

        Map<Integer, Long> counts = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Determine the highest count
        Optional<Long> maxCountOpt = counts.values().stream().max(Long::compareTo);

        if (maxCountOpt.isPresent()) {
            long maxCount = maxCountOpt.get();

            // Filter numbers that match the highest count
            counts.entrySet().stream()
                    .filter(entry -> entry.getValue() == maxCount)
                    .forEach(entry -> {
                        System.out.println("Number with max occurrences: " + entry.getKey());
                        System.out.println("Occurrences: " + entry.getValue());
                    });
        } else {
            System.out.println("List is empty");
        }
    }
}

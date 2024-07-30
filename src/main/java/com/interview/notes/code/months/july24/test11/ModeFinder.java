package com.interview.notes.code.months.july24.test11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ModeFinder {

    public static List<Integer> findModes(List<Integer> numbers) {
        // Create a frequency map using the Stream API
        Map<Integer, Long> frequencyMap = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Find the maximum frequency
        long maxFrequency = frequencyMap.values().stream()
                .mapToLong(count -> count)
                .max().orElse(1);

        // If all numbers occur with the same frequency and that frequency is 1,
        // then all numbers are modes.
        if (maxFrequency == 1) {
            return new ArrayList<>(numbers);
        }

        // Filter the numbers that occur with maxFrequency and collect them into a list
        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(2, 2, 3, 4, 5);
        System.out.println("Modes of the list are: " + findModes(numbers1));

        List<Integer> numbers2 = Arrays.asList(2, 2, 3, 3, 4);
        System.out.println("Modes of the list are: " + findModes(numbers2));

        List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        System.out.println("Modes of the list are: " + findModes(numbers3));
    }
}

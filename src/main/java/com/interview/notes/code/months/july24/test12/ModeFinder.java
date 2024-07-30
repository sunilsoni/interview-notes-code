package com.interview.notes.code.months.july24.test12;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ModeFinder {

    public static List<Integer> findModes(List<Integer> numbers) {
        return numbers.isEmpty() ? Collections.emptyList() : new ArrayList<>(
                numbers.stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .collect(Collectors.groupingBy(Map.Entry::getValue))
                        .entrySet().stream()
                        .max(Map.Entry.comparingByKey())
                        .map(Map.Entry::getValue)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList())
        );
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

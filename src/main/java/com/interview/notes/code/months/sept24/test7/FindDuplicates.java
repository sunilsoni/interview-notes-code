package com.interview.notes.code.months.sept24.test7;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicates {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 3, 2, 6, 7, 8, 8);

        Set<Integer> duplicates = numbers.stream()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());

        System.out.println("Duplicates: " + duplicates);
    }
}

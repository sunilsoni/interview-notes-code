package com.interview.notes.code.year.y2023.july23.test12;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {
    public static void main(String[] args) {
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        List<Integer> flattenedList = listOfLists.stream()
                .flatMap(List::stream) // Stream<List<Integer>> to Stream<Integer>
                .collect(Collectors.toList());

        flattenedList.forEach(System.out::println);
    }
}

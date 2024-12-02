package com.interview.notes.code.year.y2023.dec23.test5;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UniqueElementsWithCountOne {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 2, 5, 6, 1, 7};

        // Count the occurrences of each element using a Map
        Map<Integer, Long> elementCountMap = Arrays.stream(arr)
                .boxed() // Convert int to Integer
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Filter for elements with a count of one
        int[] uniqueElementsWithCountOne = elementCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .mapToInt(Map.Entry::getKey)
                .toArray();

        System.out.println("Unique elements with count one: " + Arrays.toString(uniqueElementsWithCountOne));
    }
}

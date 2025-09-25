package com.interview.notes.code.year.y2025.september.common.test8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CommonElementsUsingStreams {
    public static void main(String[] args) {
        // First integer list
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        // Second integer list
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8, 9);

        // Find common elements using Java 8 Streams
        List<Integer> common = list1.stream()
                .filter(list2::contains) // keep only elements present in list2
                .collect(Collectors.toList()); // collect to new list

        List<Integer> commonOptimized = list1.stream()
                .filter(new HashSet<>(list2)::contains)
                .collect(Collectors.toList());

        System.out.println("Common Elements (Optimized): " + commonOptimized);
        System.out.println("Common Elements: " + common);
    }
}
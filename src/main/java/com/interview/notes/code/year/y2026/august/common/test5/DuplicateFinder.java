package com.interview.notes.code.year.y2026.august.common.test5;

import java.util.List;
import java.util.stream.Collectors;

public class DuplicateFinder {

    /**
     * Finds and returns a list of duplicate integers from the input list.
     * 
     * @param numbers The input list of integers
     * @List containing only the numbers that appear more than once
     */
    public static List<Integer> findDuplicates(List<Integer> numbers) {
        if (numbers == null) {
            return List.of();
        }

        // Count frequencies of each element, filter for those > 1, and collect to a list
        return numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(java.util.Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
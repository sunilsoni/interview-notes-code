package com.interview.notes.code.year.y2024.sept24.test1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DuplicateFinder {

    public static List<Integer> findDuplicates(int[] array, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex >= array.length || startIndex > endIndex) {
            throw new IllegalArgumentException("Invalid indices");
        }

        // Extract the subarray
        List<Integer> subList = IntStream.range(startIndex, endIndex + 1)
                .mapToObj(i -> array[i])
                .collect(Collectors.toList());

        // Find duplicates in the subarray
        Map<Integer, Long> elementCounts = subList.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        return elementCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)  // Keep only duplicates
                .map(Map.Entry::getKey)                  // Extract the elements
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 4, 5, 6, 5, 7, 8, 5};

        // Example usage
        List<Integer> duplicates = findDuplicates(array, 0, 8);
        System.out.println("Duplicates in the range: " + duplicates);
    }
}

package com.interview.notes.code.months.jan24.test4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindDuplicatesUsingStreamAlternative {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 2, 5, 6, 3, 7, 8, 7};

        // Use IntStream to create a stream of elements from the array
        IntStream stream = Arrays.stream(arr);

        // Collect the elements into a map where the key is the element, and the value is its count
        Map<Integer, Long> elementCounts = stream
                .boxed() // Convert IntStream to Stream<Integer>
                .collect(Collectors.toMap(
                        i -> i, // Key: element value
                        v -> 1L, // Value: 1 for each occurrence
                        Long::sum // Merge function for counting duplicates
                ));

        // Filter the elements with counts greater than 1 (duplicates)
        List<Integer> duplicates = elementCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Print the duplicate elements
        System.out.println("Duplicate elements in the array are: " + duplicates);
    }
}

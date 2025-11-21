package com.interview.notes.code.year.y2025.november.common.test2;

import java.util.*;

public class DuplicateFinderStream {

    /**
     * Demo entry point. Creates a sample list and prints detected duplicate values.
     *
     * @param args ignored
     */
    public static void main(String[] args) {

        // Sample input containing duplicate values and nulls.
        List<String> strs = Arrays.asList("java", "JS", "kafka", "aws", "java", "kafka", null, null);

        // Stream pipeline explanation:
        // 1) filter(Objects::nonNull): remove nulls because Collections.frequency(null) is undefined
        // 2) filter(s -> Collections.frequency(strs, s) > 1): keep only elements that appear more than once
        //    Note: frequency() scans the list each time, producing O(n^2) behavior overall.
        // 3) distinct(): reduce to a single occurrence per duplicate value
        // 4) sorted(Comparator.reverseOrder()): sort results in descending alphabetical order
        // 5) toList(): collect results into an immutable List (Java 16+)
        List<String> duplicates =
                strs.stream()
                        .filter(Objects::nonNull) // avoid null pointer in frequency()
                        .filter(s -> Collections.frequency(strs, s) > 1)
                        .distinct()
                        .sorted(Comparator.reverseOrder()) // descending alphabetical
                        .toList();

        System.out.println("Duplicates: " + duplicates);
    }
}

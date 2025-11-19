package com.interview.notes.code.year.y2025.november.common.test2;

import java.util.*;

public class DuplicateFinderStream {
    public static void main(String[] args) {

        List<String> strs = Arrays.asList("java", "JS", "kafka", "aws", "java", "kafka", null, null);

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

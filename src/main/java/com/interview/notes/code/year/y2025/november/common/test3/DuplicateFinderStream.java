package com.interview.notes.code.year.y2025.november.common.test3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicateFinderStream {
    public static void main(String[] args) {

        List<String> strs = Arrays.asList("java", "JS", "kafka", "aws", "java", "kafka",null,null);

        Set<String> duplicates =
                strs.stream()
                    .filter(s -> Collections.frequency(strs, s) > 1)
                    .collect(Collectors.toSet());

        System.out.println("Duplicates: " + duplicates);
    }
}

package com.interview.notes.code.months.year2023.june23.test11;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Duplicates {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("One", "Two", "Three", "One", "Two", "Four");

        Set<String> duplicates = list.stream()
                .filter(i -> Collections.frequency(list, i) > 1)
                .collect(Collectors.toSet());

        System.out.println("Duplicate elements: " + duplicates);
    }
}

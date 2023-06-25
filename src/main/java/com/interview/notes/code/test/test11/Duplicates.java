package com.interview.notes.code.test.test11;

import java.util.*;
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

package com.interview.notes.code.months.aug24.test31;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NameProcessor {
    public static void main(String[] args) {
        List<String> names = List.of("John", "Alice", "Bob", "Charlie", "David", "Eve", "Frank");

        Map<Integer, String> result = names.stream()
                .map(String::toUpperCase)
                .filter(name -> name.length() > 5)
                .collect(Collectors.toMap(
                        String::length,
                        name -> name,
                        (existing, replacement) -> existing // In case of duplicate lengths, keep the existing name
                ));

        System.out.println(result);
    }
}

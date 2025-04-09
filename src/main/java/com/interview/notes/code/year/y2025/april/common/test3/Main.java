package com.interview.notes.code.year.y2025.april.common.test3;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 3, 2, 6, 7, 7, 8);

        List<Integer> duplicates = numbers.stream()
                .filter(n -> numbers.stream().filter(x -> x.equals(n)).count() > 1)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(duplicates); // Output: [2, 3, 7]
    }
}

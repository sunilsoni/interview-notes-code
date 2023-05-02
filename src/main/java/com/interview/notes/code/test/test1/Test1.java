package com.interview.notes.code.test.test1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        List<String> strings = Stream.of("C2", "C5", "C10", "C3", "C15")
                .sorted(Comparator.comparing(s -> Integer.parseInt(s.replaceAll("\\D+", ""))))
                .collect(Collectors.toList());

        System.out.println(strings); // Output: [C2, C3, C5, C10, C15]


    }
}

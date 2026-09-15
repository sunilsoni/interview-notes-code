package com.interview.notes.code.year.y2026.august.assessments.test1;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String str = "banana";

        Map<Character, Long> count = str.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            ));

        System.out.println(count);
    }
}
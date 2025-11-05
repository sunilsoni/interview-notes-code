package com.interview.notes.code.year.y2025.november.common.test2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByFirstChar {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("apple", "banana", "orange", "apricot", "blueberry", "avocado");

        Map<Character, List<String>> grouped = fruits.stream()
            .collect(Collectors.groupingBy(s -> Character.toLowerCase(s.charAt(0))));

        grouped.forEach((k, v) -> System.out.println(k + " → " + v));
    }
}

package com.interview.notes.code.year.y2023.july23.test3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapStream {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "carrot", "date", "elderberry");

        Map<Integer, String> lengthToStringMap = strings.stream()
                .collect(Collectors.toMap(String::length, s -> s));

        // Print the map
        lengthToStringMap.forEach((length, string) -> System.out.println(length + " -> " + string));
    }
}

package com.interview.notes.code.year.y2025.september.common.test;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> str = Arrays.asList("abc", "bca", "aaa", "cdca");

        str.stream()
                .filter(s -> s.toLowerCase().startsWith("a"))
                .distinct()
                .forEach(System.out::println);
    }
}

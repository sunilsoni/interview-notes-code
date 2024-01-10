package com.interview.notes.code.months.year2023.Sep23.test3;

import java.util.Arrays;
import java.util.List;

public class StringFilterExample {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        long count = strings.stream()
                .filter(str -> str.length() > 3)
                .count();

        System.out.println("Number of strings with length greater than 3: " + count);
    }
}

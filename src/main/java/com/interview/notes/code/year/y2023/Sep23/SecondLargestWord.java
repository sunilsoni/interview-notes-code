package com.interview.notes.code.year.y2023.Sep23;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class SecondLargestWord {
    public static void main(String[] args) {
        String input = "I am good programmer";
        System.out.println(findSecondLargestWord(input).orElse("No second largest word found"));
    }

    public static Optional<String> findSecondLargestWord(String str) {
        return Stream.of(str.split(" "))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .skip(1)
                .findFirst();
    }
}

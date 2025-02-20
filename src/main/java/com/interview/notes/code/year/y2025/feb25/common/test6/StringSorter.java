package com.interview.notes.code.year.y2025.feb25.common.test6;

import java.util.*;
import java.util.stream.Collectors;

public class StringSorter {

    public static void main(String[] args) {
        // Create an instance of StringChecker using a lambda expression.
        // This checker returns true if the string is not null.
        StringChecker nonNullChecker = s -> s != null;

        // Sample list containing some null values.
        List<String> sampleList = Arrays.asList("apple", null, "banana", "kiwi", "watermelon");

        // Use the custom checker in your sorting method.
        List<String> sortedList = new StringSorter().sortByLengthDescending(sampleList, nonNullChecker);

        System.out.println(sortedList);  // Output: [watermelon, banana, apple, kiwi]
    }

    // The method now takes a StringChecker to determine if a string should be included.
    public List<String> sortByLengthDescending(List<String> list, StringChecker checker) {
        return Optional.ofNullable(list)
                .map(l -> l.stream()
                        .filter(s -> checker.check(s))  // Using the custom functional interface
                        .sorted(Comparator.comparing(String::length).reversed())
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }
}
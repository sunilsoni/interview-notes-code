package com.interview.notes.code.year.y2023.june23.test11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortedString {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Albert", "Andrew", "Amanda", "Alicia",
                "Bob", "Bella", "Brian", "Ben", "Betty");

        List<String> filteredNames = names.stream()
                // filter names starting with "A"
                .filter(name -> name.startsWith("A"))
                // sort in descending order
                .sorted(Comparator.reverseOrder())
                // get unique names
                .distinct()
                .collect(Collectors.toList());

        System.out.println(filteredNames);

        List<String> names1 = Arrays.asList("Alice", "Albert", "Andrew", "Amanda", "Alicia",
                "Bob", "Bella", "Brian", "Ben", "Betty");

        Map<String, Long> frequencyMap = names1.stream()
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));

        System.out.println(frequencyMap);

    }
}

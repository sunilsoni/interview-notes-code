package com.interview.notes.code.year.y2026.jan.common.test6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamCombinedExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
            "Sunil", "Anita", "Ravi", "Suresh", "Amit", "Sneha", "Raj",
            "Sunil", "Sneha"   // duplicates
        );

        names.stream()
            .map(String::toUpperCase)               // convert to uppercase
            .distinct()                             // remove duplicates
            .filter(name -> name.length() > 3)      // optional filter (length > 3)
            .sorted(Comparator.comparing(String::length)
                              .thenComparing(Comparator.naturalOrder())) // sort by length then alphabetically
            .forEach(name -> 
                System.out.println(name + " (length: " + name.length() + ")")
            );
    }
}

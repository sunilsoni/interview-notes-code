package com.interview.notes.code.months.may24.test5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test5 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        List<String> filteredNames = names.stream()
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());

    }
}

package com.interview.notes.code.months.year2023.june23.test1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
This code is using the `sorted` method of a stream to sort a collection of strings based on their numeric values. The `Comparator.comparing` method is used to create a `Comparator` instance that compares the strings based on their numeric values.

The argument passed to `Comparator.comparing` is a lambda expression that takes a string `s` and converts it to an integer by first removing all non-numeric characters from the string using a regular expression and then parsing the resulting string using `Integer.parseInt` method.

The regular expression used is `\\D+`. The `\\D` matches any non-digit character, and the `+` means one or more occurrences of the preceding pattern. Therefore, `s.replaceAll("\\D+", "")` replaces all non-digit characters in `s` with an empty string, effectively removing them.

By converting the strings to integers and comparing them using the `Comparator`, the `sorted` method will sort the strings in ascending order based on their numeric values. If two strings have the same numeric value, their relative order will be preserved.

For example, given the input `C2`, `C5`, `C10`, `C3`, and `C15`, the resulting sorted list will be `C2`, `C3`, `C5`, `C10`, and `C15`, which is the expected order when sorting strings that represent numeric values.
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> strings = Stream.of("C2", "C5", "C10", "C3", "C15")
                .sorted(Comparator.comparing(s -> Integer.parseInt(s.replaceAll("\\D+", ""))))
                .collect(Collectors.toList());

        System.out.println(strings); // Output: [C2, C3, C5, C10, C15]


    }
}

package com.interview.notes.code.months.may24.test13;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Reverse the list using Java 8 streams methods only
        List<Integer> reversedNumbers = numbers.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    java.util.Collections.reverse(list);
                    return list;
                }));

        // Print the reversed list
        System.out.println("Reversed Numbers: " + reversedNumbers);
    }
}

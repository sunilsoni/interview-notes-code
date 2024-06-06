package com.interview.notes.code.months.may24.test13;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main1 {
    public static void main(String[] args) {
        // Sample list with duplicates
        List<String> listWithDuplicates = Arrays.asList("apple", "banana", "orange", "apple", "banana");

        // Remove duplicates using Java streams
        List<String> listWithoutDuplicates = listWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());

        // Print the list without duplicates
        System.out.println("List without duplicates: " + listWithoutDuplicates);
    }
}

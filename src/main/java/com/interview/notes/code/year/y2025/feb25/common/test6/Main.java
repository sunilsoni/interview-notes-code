package com.interview.notes.code.year.y2025.feb25.common.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Test cases
        List<String> list1 = Arrays.asList("apple", "banana", "cherry", "date");
        List<String> list2 = Arrays.asList("apple", null, "", "banana", "   ", "cherry");
        List<String> list3 = null;
        List<String> list4 = new ArrayList<>();

        Main main = new Main();

        // Test with normal list
        System.out.println("Normal list: " + main.sortByLengthDescending(list1));
        // Output: [banana, cherry, apple, date]

        // Test with list containing null and empty strings
        System.out.println("List with null and empty: " + main.sortByLengthDescending(list2));
        // Output: [banana, cherry, apple]

        // Test with null list
        System.out.println("Null list: " + main.sortByLengthDescending(list3));
        // Output: []

        // Test with empty list
        System.out.println("Empty list: " + main.sortByLengthDescending(list4));
        // Output: []
    }

    public List<String> sortByLengthDescending(List<String> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        return list.stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))
                .collect(Collectors.toList());
    }

}

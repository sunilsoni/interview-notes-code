package com.interview.notes.code.months.aug24.test31;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EvenNumberFilter {
    public static void main(String[] args) {
        // Create a list of integers with both even and odd numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Use Java 8 stream to filter out even numbers
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        // Print the original list
        System.out.println("Original list: " + numbers);

        // Print the filtered list of even numbers
        System.out.println("Even numbers: " + evenNumbers);
    }
}

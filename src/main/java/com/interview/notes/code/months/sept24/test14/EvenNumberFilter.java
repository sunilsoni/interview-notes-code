package com.interview.notes.code.months.sept24.test14;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EvenNumberFilter {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Create a predicate to filter even numbers
        Predicate<Integer> isEven = n -> n % 2 == 0;

        // Filter the list using the predicate
        List<Integer> evenNumbers = numbers.stream()
                .filter(isEven)
                .collect(Collectors.toList());

        System.out.println(evenNumbers); // Output: [2, 4, 6, 8, 10]
    }
}
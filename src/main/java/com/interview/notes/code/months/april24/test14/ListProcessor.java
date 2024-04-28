package com.interview.notes.code.months.april24.test14;

import java.util.*;
import java.util.stream.Collectors;

public class ListProcessor {
    public static void main(String[] args) {
        // Example list of numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 1) Filter out even numbers using a lambda expression
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());

        System.out.println("Even Numbers: " + evenNumbers);

        // 2) Find the second max and second min numbers
        Optional<Integer> secondMax = numbers.stream()
                                             .sorted(Comparator.reverseOrder())
                                             .distinct()
                                             .skip(1)
                                             .findFirst();

        Optional<Integer> secondMin = numbers.stream()
                                             .sorted()
                                             .distinct()
                                             .skip(1)
                                             .findFirst();

        secondMax.ifPresent(max -> System.out.println("Second Max: " + max));
        secondMin.ifPresent(min -> System.out.println("Second Min: " + min));
    }
}


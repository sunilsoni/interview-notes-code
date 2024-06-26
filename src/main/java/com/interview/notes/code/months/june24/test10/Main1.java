package com.interview.notes.code.months.june24.test10;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1 {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        // This line will throw a runtime exception because Set.of() does not allow duplicate elements
        Set<Integer> integerSet;
        try {
            integerSet = Set.of(5, 6, 7, 7);  // This line will throw java.lang.IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Runtime exception at Set.of(): " + e.getMessage());
            return;
        }

        Set<Integer> resultSet = Stream.concat(integerList.stream(), integerSet.stream())
                .collect(Collectors.toSet());
        resultSet.forEach(System.out::print);
    }
}

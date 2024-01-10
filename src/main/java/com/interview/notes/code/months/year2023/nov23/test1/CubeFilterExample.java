package com.interview.notes.code.months.year2023.nov23.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Java 8 program to perfrom cube on list elements and filter numbers greater than 50.
public class CubeFilterExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> result = numbers.stream()
                .map(n -> n * n * n)          // Cube each element
                .filter(n -> n > 50)         // Filter numbers greater than 50
                .collect(Collectors.toList());

        System.out.println(result);  // This will print: [64, 125, 216, 343, 512, 729, 1000]
    }
}

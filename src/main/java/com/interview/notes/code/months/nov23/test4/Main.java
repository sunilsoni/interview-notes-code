package com.interview.notes.code.months.nov23.test4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(10);
        numbers.add(2);
        numbers.add(7);
        numbers.add(8);

        List<Integer> evenSortedDesc = numbers.stream()
                .filter(num -> num % 2 == 0)  // Filter even numbers
                .sorted(Comparator.reverseOrder())  // Sort in descending order
                .collect(Collectors.toList());

        System.out.println(evenSortedDesc);
    }
}

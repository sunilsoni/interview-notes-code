package com.interview.notes.code.months.year2023.june23.test7;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface NumberFilter {
    boolean test(int number);
}

public class LambdaExample {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(7);
        numbers.add(5);
        numbers.add(12);
        numbers.add(3);

        NumberFilter evenFilter = number -> number % 2 == 0;

        // Using lambda expression with custom functional interface
        numbers.stream()
                .filter(evenFilter::test)
                .forEach(System.out::println);
    }
}

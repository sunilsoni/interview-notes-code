package com.interview.notes.code.months.july24.test11;

import java.util.Arrays;
import java.util.List;

public class SumExample {
    public static void main(String[] args) {
        // List of numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Calculate the sum using Stream API
        int sum = numbers.stream()
                .mapToInt(Integer::intValue) // Convert to IntStream
                .sum(); // Sum the values

        // Output the result
        System.out.println("Sum: " + sum);
    }
}

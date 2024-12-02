package com.interview.notes.code.year.y2023.nov23.test2;

import java.util.Arrays;
import java.util.List;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 7, 6, 4, 3, 8, 10);

        int sumOfSortedNumbers = numbers.stream()
                .sorted()                          // Sort the numbers in ascending order
                .mapToInt(Integer::intValue)       // Convert to IntStream
                .sum();                            // Sum the values

        System.out.println("Sum of sorted numbers: " + sumOfSortedNumbers);
    }
}

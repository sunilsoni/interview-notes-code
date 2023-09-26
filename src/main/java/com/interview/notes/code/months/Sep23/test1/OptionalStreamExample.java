package com.interview.notes.code.months.Sep23.test1;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalStreamExample {
    public static void main(String[] args) {
        List<Optional<String>> optionalStrings = Arrays.asList(
                Optional.of("Hello"),
                Optional.empty(),
                Optional.of("Stream"),
                Optional.of("API"),
                Optional.empty()
        );

        int sumOfLengths = optionalStrings.stream()
                .filter(Optional::isPresent) // Filter out empty Optionals
                .map(Optional::get) // Get the values from Optionals
                .map(String::length) // Map to the lengths
                .mapToInt(Integer::intValue) // Convert to IntStream
                .sum(); // Calculate the sum

        System.out.println("Sum of lengths: " + sumOfLengths);
    }
}

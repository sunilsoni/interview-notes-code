package com.interview.notes.code.months.april24.test4;

import java.util.Optional;

public class LambdaExample {
    public static void main(String[] args) {
        // Simulating a method that may or may not return a value
        Optional<String> optionalValue = getValue();

        // Using method chaining to process the value if present,
        // or print a message if absent
        optionalValue.map(value -> "Uppercase value: " + value.toUpperCase())
                    .ifPresentOrElse(System.out::println, () -> System.out.println("Value is absent"));
    }

    // Method that may or may not return a value
    private static Optional<String> getValue() {
        // Simulating a situation where the value may be null
        String data = null;

        // Wrap the value in an Optional to indicate it may be absent
        return Optional.ofNullable(data);
    }
}

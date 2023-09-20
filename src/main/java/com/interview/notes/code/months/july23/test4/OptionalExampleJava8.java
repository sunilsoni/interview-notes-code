package com.interview.notes.code.months.july23.test4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalExampleJava8 {
    public static void main(String[] args) {
        String value = "Hello, Java 8!";
        Optional<String> optionalValue = Optional.of(value);
        Optional<String> optionalValue1 = Optional.ofNullable(value);

        System.out.println(optionalValue.get()); // Output: "Hello, Java 8!"

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.stream().forEach(name -> System.out.println("Hello, " + name));

    }
}

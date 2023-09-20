package com.interview.notes.code.months.june23.test2;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> optionalName = Optional.of("John");

        if (optionalName.isPresent()) {
            String name = optionalName.get();
            System.out.println("Name: " + name);
        } else {
            System.out.println("Name is not present.");
        }

        optionalName.ifPresent(name -> System.out.println("Name: " + name));

        String fallback = optionalName.orElse("Default Name");
        System.out.println("Fallback Name: " + fallback);
    }
}

package com.interview.notes.code.year.y2024.june24.test12;

public class Main {
    public static void main(String[] args) {
        // Lambda expression implementing the concatenate method
        StringConcatenator concatenator = (s1, s2) -> s1 + s2;

        // Test the lambda expression
        String result = concatenator.concatenate("Hello, ", "World!");
        System.out.println(result);  // Output: Hello, World!
    }
}

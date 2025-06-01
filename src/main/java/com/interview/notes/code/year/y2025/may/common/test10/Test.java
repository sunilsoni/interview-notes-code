package com.interview.notes.code.year.y2025.may.common.test10;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Stream<String> s = Stream.of("a", "b", "c");
        s.forEach(System.out::println);  // First iteration
        s = Stream.of("a", "b", "c");   // Create a new stream
        s.forEach(System.out::println);  // Second iteration

        List<Integer> numbers = Arrays.asList(1,2,3);
        try {
            numbers.add(4);
        } catch (Exception e) {
            System.out.println("Exception while adding");
        }
        try {
            numbers.set(2,2);
        } catch (Exception e) {
            System.out.println("Exception while m");
        }

    }
}

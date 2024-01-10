package com.interview.notes.code.months.year2023.july23.test12;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SupplierExample {
    public static void main(String[] args) {
        // Create a Supplier that generates random integers
        Supplier<Integer> randomIntSupplier = () -> new Random().nextInt(100);

        // Generate a stream of 10 random integers
        Stream<Integer> randomIntStream = Stream.generate(randomIntSupplier).limit(100);

        // Print the generated random integers
        randomIntStream.forEach(System.out::println);
    }
}

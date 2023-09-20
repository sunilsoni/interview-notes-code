package com.interview.notes.code.months.july23.test12;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListSupplierExample {
    public static void main(String[] args) {
        // Create a Supplier that generates a list of random integers
        Supplier<List<Integer>> randomIntListSupplier = () -> {
            Random random = new Random();
            int listSize = random.nextInt(5) + 1; // Generate a list size between 1 and 5
            return random.ints(listSize, 0, 100).boxed().collect(Collectors.toList());
        };

        // Generate a stream of 3 lists of random integers
        Stream<List<Integer>> randomIntListsStream = Stream.generate(randomIntListSupplier).limit(3);

        // Process and print the generated lists
        randomIntListsStream.forEach(list -> System.out.println("Generated List: " + list));
    }
}

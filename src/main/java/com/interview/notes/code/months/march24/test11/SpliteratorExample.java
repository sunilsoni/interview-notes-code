package com.interview.notes.code.months.march24.test11;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class SpliteratorExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Obtain Spliterator
        Spliterator<Integer> spliterator = numbers.spliterator();

        // Sequential traversal
        System.out.println("Sequential traversal:");
        spliterator.forEachRemaining(System.out::println);

        // Split the Spliterator for parallel processing
        Spliterator<Integer> splitSpliterator = numbers.spliterator().trySplit();

        // Parallel processing
        System.out.println("\nParallel traversal:");
        splitSpliterator.forEachRemaining(System.out::println);
    }
}

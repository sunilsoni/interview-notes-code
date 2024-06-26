package com.interview.notes.code.months.june24.test8;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class SpliteratorDemo {
    public static void main(String[] args) {
        // Create a list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Obtaining a Spliterator
        Spliterator<Integer> spliterator = numbers.spliterator();

        // Traversing elements sequentially using tryAdvance
        System.out.println("Sequential traversal:");
        spliterator.tryAdvance(System.out::println); // Process one element

        // Splitting the spliterator to allow parallel processing
        Spliterator<Integer> spliterator1 = spliterator.trySplit();

        // Using a thread to simulate parallel processing
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 processing:");
            spliterator1.forEachRemaining(System.out::println);
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2 processing:");
            spliterator.forEachRemaining(System.out::println);
        });

        thread1.start();
        thread2.start();

        // Wait for threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.interview.notes.code.year.y2025.march.common.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class FilterWithSupplier {

    // Generic filter method that takes a Supplier for the output collection.
    public static <T, U extends Collection<T>> U filter(
            Collection<T> items,
            Filter<T> filter,
            Supplier<U> outputSupplier) {
        U result = outputSupplier.get();  // Create an instance of the output collection.
        for (T item : items) {
            if (filter.apply(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filtering even numbers using an anonymous inner class for Filter
        // and a Supplier that provides an ArrayList for the output.
        List<Integer> evens = filter(numbers, new Filter<Integer>() {
            public boolean apply(Integer n) {
                return n % 2 == 0;
            }
        }, new Supplier<List<Integer>>() {
            public List<Integer> get() {
                return new ArrayList<Integer>();
            }
        });

        System.out.println("Even numbers: " + evens);
    }

    // Pre-Java8 style functional interface for filtering items.
    public interface Filter<T> {
        boolean apply(T t);
    }
}
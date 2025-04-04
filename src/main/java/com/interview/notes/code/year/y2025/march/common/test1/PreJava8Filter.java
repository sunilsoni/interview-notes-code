package com.interview.notes.code.year.y2025.march.common.test1;

import java.util.*;

public class PreJava8Filter {

    // Define a functional interface for filtering items
    public interface Filter<T> {
        boolean apply(T t);
    }
    
    // Generic filter method that mimics the behavior of Stream.filter
    public static <T> List<T> filter(List<T> list, Filter<T> filter) {
        List<T> result = new ArrayList<T>();
        for (T item : list) {
            if (filter.apply(item)) {
                result.add(item);
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filtering even numbers using an anonymous inner class
        List<Integer> evens = filter(numbers, new Filter<Integer>() {
            public boolean apply(Integer n) {
                return n % 2 == 0;
            }
        });
        System.out.println("Even numbers: " + evens);
        
        // Filtering odd numbers using another anonymous inner class
        List<Integer> odds = filter(numbers, new Filter<Integer>() {
            public boolean apply(Integer n) {
                return n % 2 != 0;
            }
        });
        System.out.println("Odd numbers: " + odds);
    }
}
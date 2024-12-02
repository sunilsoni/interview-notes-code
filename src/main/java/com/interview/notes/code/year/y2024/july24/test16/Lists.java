package com.interview.notes.code.year.y2024.july24.test16;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lists {
    public static void main(String[] args) {
        // Initialize a list of books
        List<String> books = List.of("Java 8 in Action", "Spring in Action", "Introduction to Spring Boot", "Java 8", "Java from Scratch", "Clean Code");

        // Initialize a list of numbers
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Define a predicate to check if a number is even
        Predicate<Integer> isEven = new Predicate<>() {
            @Override
            public boolean test(Integer x) {
                return x % 2 == 0;
            }
        };

        // Define a consumer to print integers
        Consumer<Integer> print = new Consumer<>() {
            @Override
            public void accept(Integer x) {
                System.out.println(x);
            }
        };

        // Define a binary operator to sum integers
        BinaryOperator<Integer> sumBinaryOperator = Integer::sum;

        // Get the even numbers from the numbers list
        System.out.println("-- Even numbers: ");
        List<Integer> evenNumbers = numbers.stream()
                .filter(isEven)
                .collect(Collectors.toList());
        evenNumbers.forEach(System.out::println);

        // Get the numbers from the list with no duplicates
        System.out.println("-- Distinct numbers: ");
        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        distinctNumbers.forEach(print);

        // Get the square of every number in the list
        System.out.println("-- Squared numbers: ");
        List<Integer> squareNumbers = numbers.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
        squareNumbers.forEach(print);

        // Calculate the sum of all numbers
        System.out.println("-- Sum of numbers: ");
        Integer sum = numbers.stream()
                .reduce(0, sumBinaryOperator);
        System.out.println(sum);

        // Get the books that contain the word "Java" in the name
        System.out.println("-- Java books: ");
        List<String> javaBooks = books.stream()
                .filter(book -> book.contains("Java"))
                .collect(Collectors.toList());
        javaBooks.forEach(System.out::println);
    }
}

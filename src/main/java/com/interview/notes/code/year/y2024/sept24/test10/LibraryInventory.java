package com.interview.notes.code.year.y2024.sept24.test10;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String category;

    public Book(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}

public class LibraryInventory {

    public static void main(String[] args) {
        List<Book> books = fetchBooksFromLibrary();

        // Grouping books by category and counting them
        Map<String, Long> inventory = books.stream()
                .collect(Collectors.groupingBy(
                        Book::getCategory,
                        Collectors.counting()
                ));

        // Printing the result
        inventory.forEach((category, count) ->
                System.out.println(category + " = " + count));
    }

    private static List<Book> fetchBooksFromLibrary() {
        return new ArrayList<>(List.of(
                new Book("Effective Java", "Java"),
                new Book("Java Concurrency in Practice", "Java"),
                new Book("The C Programming Language", "C"),
                new Book("C++ Primer", "C++"),
                new Book("Clean Code", "Java"),
                new Book("C Programming Absolute Beginner's Guide", "C"),
                new Book("C++ for Dummies", "C++"),
                // Simulating additional books
                new Book("Oracle Database 12c", "Oracle"),
                new Book("Oracle PL/SQL Programming", "Oracle"),
                new Book("Oracle Essentials", "Oracle"),
                new Book("Oracle Performance Tuning", "Oracle"),
                new Book("C Programming Language", "C"),
                new Book("C Programming Language", "C"),
                new Book("C++ Programming Language", "C++"),
                new Book("C++ Programming Language", "C++"),
                new Book("C++ Programming Language", "C++")
        ));
    }
}

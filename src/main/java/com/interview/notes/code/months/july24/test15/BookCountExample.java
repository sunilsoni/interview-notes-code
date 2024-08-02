package com.interview.notes.code.months.july24.test15;

import java.util.Arrays;
import java.util.List;

public class BookCountExample {
    public static void main(String[] args) {
        // Create a list of books
        List<Book> books = Arrays.asList(
                new Book("Alice in Wonderland", "Lewis Carroll"),
                new Book("A Tale of Two Cities", "Charles Dickens"),
                new Book("The Great Gatsby", "F. Scott Fitzgerald"),
                new Book("The Alchemist", "Paulo Coelho")
        );

        // Count the number of books whose name starts with 'A'
        long count = books.stream()
                .filter(book -> book.getName().startsWith("A"))
                .count();

        // Print the count
        System.out.println("Number of books starting with 'A': " + count);
    }
}

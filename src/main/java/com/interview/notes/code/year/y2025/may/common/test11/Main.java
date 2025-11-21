package com.interview.notes.code.year.y2025.may.common.test11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Book class to store book information
class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final int totalCopies;
    private int availableCopies;

    public Book(String isbn, String title, String author, int totalCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    // Getters and setters with validation
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void borrowCopy() {
        if (availableCopies > 0) availableCopies--;
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) availableCopies++;
    }
}

// Main Library Management System class
class LibrarySystem {
    private final Map<String, Book> booksByIsbn;
    private final Map<String, List<String>> borrowedBooks; // userId -> List of ISBNs

    public LibrarySystem() {
        this.booksByIsbn = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
    }

    // Add a new book or additional copies
    public boolean addBook(String isbn, String title, String author, int copies) {
        if (copies <= 0) return false;

        booksByIsbn.computeIfPresent(isbn, (k, v) ->
                new Book(isbn, title, author, v.getAvailableCopies() + copies));
        booksByIsbn.computeIfAbsent(isbn, k ->
                new Book(isbn, title, author, copies));
        return true;
    }

    // Borrow a book
    public boolean borrowBook(String userId, String isbn) {
        Book book = booksByIsbn.get(isbn);
        if (book == null || book.getAvailableCopies() <= 0) return false;

        borrowedBooks.computeIfAbsent(userId, k -> new ArrayList<>()).add(isbn);
        book.borrowCopy();
        return true;
    }

    // Return a book
    public boolean returnBook(String userId, String isbn) {
        if (!borrowedBooks.containsKey(userId)) return false;

        List<String> userBooks = borrowedBooks.get(userId);
        if (!userBooks.remove(isbn)) return false;

        Book book = booksByIsbn.get(isbn);
        book.returnCopy();
        return true;
    }
}

//    // Search books by title using Stream API
//    public List<Book> searchByTitle(String title) {
//        return booksByIsbn.values().stream()
//            .filter(book -> book.getTitle().toLowerCase()
//                .contains(title.toLowerCase()))
//            .toList();
//    }

// Get available copies
//    public int getAvailableCopies(String isbn) {
//        return Optional.ofNullable(booksByIsbn.get(isbn))
//            .map(Book::getAvailableCopies)
//            .orElse(0);
//    }
//}
//
/// / Main class for testing
//public class Main {
//    public static void main(String[] args) {
//        LibrarySystem library = new LibrarySystem();
//
//        // Test cases
//        System.out.println("Running test cases...\n");
//
//        // Test 1: Add books
//        testCase("Add Books", () -> {
//            boolean result = library.addBook("ISBN1", "Java Programming", "John Doe", 5);
//            result &= library.addBook("ISBN2", "Python Basics", "Jane Smith", 3);
//            return result;
//        });
//
//        // Test 2: Borrow book
//        testCase("Borrow Book", () -> {
//            boolean result = library.borrowBook("user1", "ISBN1");
//            return result && library.getAvailableCopies("ISBN1") == 4;
//        });
//
//        // Test 3: Return book
//        testCase("Return Book", () -> {
//            boolean result = library.returnBook("user1", "ISBN1");
//            return result && library.getAvailableCopies("ISBN1") == 5;
//        });
//
//        // Test 4: Search books
//        testCase("Search Books", () -> {
//            List<Book> results = library.searchByTitle("Java");
//            return results.size() == 1;
//        });
//
//        // Test 5: Edge cases
//        testCase("Edge Cases", () -> {
//            boolean result = !library.borrowBook("user1", "INVALID_ISBN");
//            result &= !library.addBook("ISBN3", "Test Book", "Author", 0);
//            return result;
//        });
//
//        // Performance test with large data
//        System.out.println("\nRunning performance test...");
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            library.addBook("ISBN" + i, "Book" + i, "Author" + i, 1);
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.printf("Added 10000 books in %dms%n", (endTime - startTime));
//    }
//
//    // Helper method for running test cases
//    private static void testCase(String testName, Runnable test) {
//        try {
//            boolean passed = test.run();
//            System.out.printf("Test: %s -> %s%n", testName,
//                passed ? "PASS" : "FAIL");
//        } catch (Exception e) {
//            System.out.printf("Test: %s -> FAIL (Exception: %s)%n",
//                testName, e.getMessage());
//        }
//    }
//}

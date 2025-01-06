package com.interview.notes.code.year.y2024.dec24.test6;

import java.util.Arrays;
import java.util.List;

// Option B: Interface Reader with default and additional methods
interface ReaderB {
    default void read(Book b) {
        System.out.println("Reading book " + b.getTitle());
    }

    void unread(Book b);
}

// Option C: Interface Reader with a default implementation
interface ReaderC {
    default void read(Book b) {
        System.out.println("Default read: Reading book " + b.getTitle());
    }
}

// Option E: Interface with both default and non-default methods
interface ReaderE {
    void read(Book b);

    default void unread(Book b) {
        System.out.println("Unread book " + b.getTitle());
    }
}

class Book {
    private String title;
    private String genre;

    public Book(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}

// Option A: Abstract class Reader with a concrete method
abstract class AbstractReaderA {
    void read(Book b) {
        System.out.println("Reading book " + b.getTitle());
    }
}

// Option D: Abstract class Reader with an abstract method
abstract class AbstractReaderD {
    abstract void read(Book b);
}

public class ReaderTest {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Gone with the Wind", "Fiction"),
                new Book("Bourne Ultimatum", "Thriller"),
                new Book("The Client", "Thriller")
        );

        // Testing Option B
        ReaderB readerB = new ReaderB() {
            @Override
            public void unread(Book b) {
                System.out.println("Unread book " + b.getTitle());
            }
        };

        books.forEach(readerB::read);
        books.forEach(readerB::unread);

        // Testing Option C
        ReaderC readerC = new ReaderC() {
        };
        books.forEach(readerC::read);

        // Testing Option E
        ReaderE readerE = new ReaderE() {
            @Override
            public void read(Book b) {
                System.out.println("Reading book (from ReaderE) " + b.getTitle());
            }
        };

        books.forEach(readerE::read);
        books.forEach(readerE::unread);
    }
}
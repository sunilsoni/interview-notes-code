package com.interview.notes.code.year.y2025.september.assesment.test6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

interface LibraryManagement {
    void addBook(Book book);

    String borrowBook(String user, Book book) throws OutOfStockException, MaxBorrowedBooksException;

    List<Book> getBooks();

    String viewLibraryInventory();
}

class Book {
    private final String title;
    private final String author;
    private boolean available;

    Book(String title, String author, boolean available) {
        this.title = title;
        this.author = author;
        this.available = available;
    }

    String title() {
        return title;
    }

    String author() {
        return author;
    }

    boolean available() {
        return available;
    }

    void setAvailable(boolean a) {
        this.available = a;
    }

    String asLine() {
        return " - " + title + " by " + author + " (Available: " + available + ")";
    }
}

class OutOfStockException extends Exception {
    OutOfStockException(String title) {
        super("Cannot borrow book '" + title + "' as it is out of stock.");
    }
}

class MaxBorrowedBooksException extends Exception {
    MaxBorrowedBooksException(int max) {
        super("Cannot borrow book as you have reached the maximum of " + max + " borrowed books.");
    }
}

class LibraryManagementSystem implements LibraryManagement {
    private final List<Book> books = new ArrayList<>();
    private final Map<String, List<Book>> borrowedBooks = new LinkedHashMap<>();
    private final int maxBorrowedBooks;

    LibraryManagementSystem(int maxBorrowedBooks) {
        this.maxBorrowedBooks = maxBorrowedBooks;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public String borrowBook(String user, Book book) throws OutOfStockException, MaxBorrowedBooksException {
        List<Book> mine = borrowedBooks.computeIfAbsent(user, k -> new ArrayList<>());
        if (mine.size() >= maxBorrowedBooks) throw new MaxBorrowedBooksException(maxBorrowedBooks);
        if (!book.available()) throw new OutOfStockException(book.title());
        book.setAvailable(false);
        mine.add(book);
        return "User '" + user + "' has borrowed the book '" + book.title() + "'.";
    }

    public boolean returnBook(String user, Book book) {
        List<Book> mine = borrowedBooks.getOrDefault(user, Collections.emptyList());
        boolean removed = mine.remove(book);
        if (removed) book.setAvailable(true);
        return removed;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String viewLibraryInventory() {
        String inv = "Library Inventory:\n" +
                books.stream().map(Book::asLine).collect(Collectors.joining("\n")) + "\n" +
                "Borrowed Books:\n" +
                borrowedBooks.entrySet().stream()
                        .filter(e -> !e.getValue().isEmpty())
                        .map(e -> "User '" + e.getKey() + "' has borrowed:\n" +
                                e.getValue().stream().map(Book::asLine).collect(Collectors.joining("\n")))
                        .collect(Collectors.joining("\n"));
        return inv.trim() + "\n";
    }
}

/**
 * CLI harness for a simple Library Management System used in assessment test6.
 * - Reads stdin; if none, runs built-in tests.
 * - Supports operations: borrowBook,user,index | returnBook,user,index | viewLibrary.
 * - Prints inventory and operation results in deterministic format for testing.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (!br.ready()) {
            runAllTests();
            return;
        }
        List<String> lines = br.lines().map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.print(process(lines));
    }

    static String process(List<String> lines) {
        StringBuilder out = new StringBuilder();
        int i = 0;
        int maxBorrowed = Integer.parseInt(lines.get(i++));
        int nBooks = Integer.parseInt(lines.get(i++));
        LibraryManagementSystem lib = new LibraryManagementSystem(maxBorrowed);
        for (int k = 0; k < nBooks; k++) {
            String[] p = lines.get(i++).split(",", -1);
            lib.addBook(new Book(p[0].trim(), p[1].trim(), Boolean.parseBoolean(p[2].trim().toLowerCase())));
        }
        int m = Integer.parseInt(lines.get(i++));
        for (int k = 0; k < m; k++) {
            String line = lines.get(i++);
            String[] p = line.split(",", -1);
            String op = p[0].trim();
            if ("borrowBook".equalsIgnoreCase(op)) {
                String user = p[1].trim();
                int idx = Integer.parseInt(p[2].trim()) - 1;
                if (idx < 0 || idx >= lib.getBooks().size()) {
                    out.append("Invalid book index.\n");
                    continue;
                }
                Book b = lib.getBooks().get(idx);
                try {
                    out.append(lib.borrowBook(user, b)).append("\n");
                } catch (Exception e) {
                    out.append(e.getMessage()).append("\n");
                }
            } else if ("returnBook".equalsIgnoreCase(op)) {
                String user = p[1].trim();
                int idx = Integer.parseInt(p[2].trim()) - 1;
                if (idx < 0 || idx >= lib.getBooks().size()) {
                    out.append("Invalid book index.\n");
                    continue;
                }
                Book b = lib.getBooks().get(idx);
                boolean ok = lib.returnBook(user, b);
                out.append(ok ? "User '" + user + "' has returned the book '" + b.title() + "'.\n"
                        : "Cannot return book as it was not borrowed by user.\n");
            } else if ("viewLibrary".equalsIgnoreCase(op)) {
                out.append("\n").append(lib.viewLibraryInventory());
            } else {
                out.append("Unknown operation.\n");
            }
        }
        return out.toString();
    }

    static void runAllTests() {
        List<TestCase> tests = new ArrayList<>();

        String in1 = String.join("\n",
                "3",
                "3",
                "Great,Scott,True",
                "Bird,Harper,True",
                "George,Orwell,False",
                "2",
                "borrowBook,Jane,1",
                "viewLibrary"
        );
        String must1a = "User 'Jane' has borrowed the book 'Great'.";
        String must1b = "Library Inventory:";
        String must1c = " - Great by Scott (Available: false)";
        String must1d = "User 'Jane' has borrowed:";
        tests.add(new TestCase("Sample-1", in1, List.of(must1a, must1b, must1c, must1d)));

        String in2 = String.join("\n",
                "2",
                "3",
                "Great,Scott,True",
                "Hello,Harper,True",
                "George,Orwell,False",
                "2",
                "borrowBook,Jenny,1",
                "viewLibrary"
        );
        tests.add(new TestCase("Sample-2", in2, List.of(
                "User 'Jenny' has borrowed the book 'Great'.",
                " - Great by Scott (Available: false)",
                " - Hello by Harper (Available: true)"
        )));

        String in3 = String.join("\n",
                "1",
                "2",
                "Out,Someone,False",
                "In,Auth,True",
                "3",
                "borrowBook,Alice,1",
                "borrowBook,Alice,2",
                "viewLibrary"
        );
        tests.add(new TestCase("OutOfStock", in3, List.of(
                "Cannot borrow book 'Out' as it is out of stock.",
                "User 'Alice' has borrowed the book 'In'."
        )));

        String in4 = String.join("\n",
                "1",
                "3",
                "A,AA,True",
                "B,BB,True",
                "C,CC,True",
                "3",
                "borrowBook,Bob,1",
                "borrowBook,Bob,2",
                "borrowBook,Bob,3"
        );
        tests.add(new TestCase("MaxLimit", in4, List.of(
                "User 'Bob' has borrowed the book 'A'.",
                "Cannot borrow book as you have reached the maximum of 1 borrowed books."
        )));

        String in5 = String.join("\n",
                "3",
                "2",
                "R,RA,True",
                "S,SB,True",
                "4",
                "borrowBook,Dan,2",
                "returnBook,Dan,2",
                "borrowBook,Dan,2",
                "viewLibrary"
        );
        tests.add(new TestCase("Return-Then-Borrow", in5, List.of(
                "User 'Dan' has borrowed the book 'S'.",
                "User 'Dan' has returned the book 'S'.",
                "User 'Dan' has borrowed the book 'S'.",
                "User 'Dan' has borrowed:"
        )));

        String largeInput = buildLargeInput(50000, 3, 2);
        tests.add(new TestCase("Large-50k", largeInput, List.of(
                "User 'U' has borrowed the book 'T0'.",
                "User 'U' has borrowed the book 'T1'."
        )));

        int pass = 0;
        for (TestCase t : tests) {
            String out = process(Arrays.stream(t.input.split("\n")).collect(Collectors.toList()));
            boolean ok = t.mustContain.stream().allMatch(out::contains);
            System.out.println("Test: " + t.name + " | " + (ok ? "PASS" : "FAIL"));
            if (!ok) {
                System.out.println("---- Output ----");
                System.out.println(out);
                System.out.println("---- Required fragments ----");
                t.mustContain.forEach(System.out::println);
                System.out.println("----------------");
            } else pass++;
        }
        System.out.println("Passed " + pass + " / " + tests.size());
    }

    static String buildLargeInput(int n, int max, int toBorrow) {
        List<String> lines = new ArrayList<>();
        lines.add(String.valueOf(max));
        lines.add(String.valueOf(n));
        for (int i = 0; i < n; i++) lines.add("T" + i + ",A" + i + ",True");
        lines.add(String.valueOf(toBorrow));
        lines.add("borrowBook,U,1");
        lines.add("borrowBook,U,2");
        return String.join("\n", lines);
    }

    record TestCase(String name, String input, List<String> mustContain) {
    }
}
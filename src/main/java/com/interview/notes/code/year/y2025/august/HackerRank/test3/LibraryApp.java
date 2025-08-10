package com.interview.notes.code.year.y2025.august.HackerRank.test3;

import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * Library Management System – Java 8 / Stream API
 * - Book implements IBook
 * - LibrarySystem implements ILibrarySystem, internally stores Map<IBook, Integer>
 * - Main: reads from stdin (optional) or runs built-in tests and prints PASS/FAIL
 */
public class LibraryApp {

    /* -------------------- Interfaces -------------------- */
    interface IBook {
        void setId(int id);
        int getId();

        void setTitle(String title);
        String getTitle();

        void setAuthor(String author);
        String getAuthor();

        void setCategory(String category);
        String getCategory();

        void setPrice(int price);
        int getPrice();
    }

    interface ILibrarySystem {
        void addBook(IBook book, int quantity);
        void removeBook(IBook book, int quantity);
        int calculateTotal();
        Map<String, Integer> categoryTotalPrice();
        List<BooksInfo> booksInfo();
        List<CategoryAuthorWithCount> categoryAndAuthorWithCount();
    }

    /* -------------------- DTOs for printing -------------------- */
    static class BooksInfo {
        String title;
        int quantity;
        int price;

        BooksInfo(String title, int quantity, int price) {
            this.title = title;
            this.quantity = quantity;
            this.price = price;
        }
    }

    static class CategoryAuthorWithCount {
        String category;
        String author;
        int count;

        CategoryAuthorWithCount(String category, String author, int count) {
            this.category = category;
            this.author = author;
            this.count = count;
        }
    }

    /* -------------------- Book & Library -------------------- */
    static class Book implements IBook {
        private int id;
        private String title;
        private String author;
        private String category;
        private int price;

        Book(int id, String title, String author, String category, int price) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.category = category;
            this.price = price;
        }

        @Override public void setId(int id) { this.id = id; }
        @Override public int getId() { return id; }

        @Override public void setTitle(String title) { this.title = title; }
        @Override public String getTitle() { return title; }

        @Override public void setAuthor(String author) { this.author = author; }
        @Override public String getAuthor() { return author; }

        @Override public void setCategory(String category) { this.category = category; }
        @Override public String getCategory() { return category; }

        @Override public void setPrice(int price) { this.price = price; }
        @Override public int getPrice() { return price; }

        /* Important for using Book as a Map key */
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Book)) return false;
            Book b = (Book) o;
            return id == b.id; // id uniquely identifies a book
        }
        @Override public int hashCode() { return Objects.hash(id); }
    }

    static class LibrarySystem implements ILibrarySystem {
        private final Map<IBook, Integer> books = new HashMap<>();

        @Override
        public void addBook(IBook book, int quantity) {
            if (book == null || quantity <= 0) return;
            books.merge(book, quantity, Integer::sum);
        }

        @Override
        public void removeBook(IBook book, int quantity) {
            if (book == null || quantity <= 0) return;
            books.computeIfPresent(book, (k, q) -> {
                int newQ = q - quantity;
                return (newQ > 0) ? newQ : null; // remove if zero or negative
            });
        }

        @Override
        public int calculateTotal() {
            return books.entrySet().stream()
                    .mapToInt(e -> e.getKey().getPrice() * e.getValue())
                    .sum();
        }

        @Override
        public Map<String, Integer> categoryTotalPrice() {
            // category -> sum(price * qty)
            Map<String, Integer> map = books.entrySet().stream()
                    .collect(Collectors.groupingBy(
                            e -> e.getKey().getCategory(),
                            Collectors.summingInt(e -> e.getKey().getPrice() * e.getValue())
                    ));
            // return sorted by category (like samples)
            return new TreeMap<>(map);
        }

        @Override
        public List<BooksInfo> booksInfo() {
            // sorted by title then quantity
            return books.entrySet().stream()
                    .map(e -> new BooksInfo(e.getKey().getTitle(), e.getValue(), e.getKey().getPrice()))
                    .sorted(Comparator
                            .comparing((BooksInfo bi) -> bi.title)
                            .thenComparingInt(bi -> bi.quantity))
                    .collect(Collectors.toList());
        }

        @Override
        public List<CategoryAuthorWithCount> categoryAndAuthorWithCount() {
            // group by (category, author) -> total quantity
            Map<String, Integer> grouped = books.entrySet().stream()
                    .collect(Collectors.groupingBy(
                            e -> e.getKey().getCategory() + "\u0001" + e.getKey().getAuthor(),
                            Collectors.summingInt(Map.Entry::getValue)
                    ));

            return grouped.entrySet().stream()
                    .map(en -> {
                        String[] parts = en.getKey().split("\u0001", -1);
                        return new CategoryAuthorWithCount(parts[0], parts[1], en.getValue());
                    })
                    .sorted(Comparator
                            .comparing((CategoryAuthorWithCount c) -> c.category)
                            .thenComparing(c -> c.author))
                    .collect(Collectors.toList());
        }
    }

    /* -------------------- Printing helpers (match sample format) -------------------- */
    static void printReport(LibrarySystem lib) {
        System.out.println("Book Info:");
        lib.booksInfo().forEach(bi ->
                System.out.println("Book Name:" + bi.title + ", Quantity:" + bi.quantity + ", Price:" + bi.price));

        System.out.println("Category Total Price:");
        lib.categoryTotalPrice().forEach((cat, price) ->
                System.out.println("Category:" + cat + ", Total Price:" + price));

        System.out.println("Category And Author With Count:");
        lib.categoryAndAuthorWithCount().forEach(ca ->
                System.out.println("Category:" + ca.category + ", Author:" + ca.author + ", Count:" + ca.count));

        System.out.println("Total Price: " + lib.calculateTotal());
    }

    /* -------------------- Parser for stdin (id title author category price qty) -------------------- */
    static LibrarySystem parseFromStdin(BufferedReader br) throws IOException {
        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) return null;
        int n = Integer.parseInt(first.trim());
        LibrarySystem lib = new LibrarySystem();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            if (line == null) break;
            // tokens: id title author category price quantity
            String[] t = line.trim().split("\\s+");
            if (t.length < 6) continue;
            int id = Integer.parseInt(t[0]);
            String title = t[1];
            String author = t[2];
            String category = t[3];
            int price = Integer.parseInt(t[4]);
            int qty = Integer.parseInt(t[5]);
            lib.addBook(new Book(id, title, author, category, price), qty);
        }
        return lib;
    }

    /* -------------------- Simple test harness (no JUnit) -------------------- */
    static class Assert {
        static void equals(String name, Object a, Object b, List<String> results) {
            boolean ok = Objects.equals(a, b);
            results.add((ok ? "PASS: " : "FAIL: ") + name + (ok ? "" : " (expected=" + b + ", got=" + a + ")"));
        }
        static void trueCond(String name, boolean cond, List<String> results) {
            results.add((cond ? "PASS: " : "FAIL: ") + name);
        }
    }

    /* Build library from arrays for tests */
    static LibrarySystem build(int[][] ints, String[][] strs) {
        // ints[i] = {id, price, qty}; strs[i] = {title, author, category}
        LibrarySystem lib = new LibrarySystem();
        for (int i = 0; i < ints.length; i++) {
            int id = ints[i][0], price = ints[i][1], qty = ints[i][2];
            String title = strs[i][0], author = strs[i][1], category = strs[i][2];
            lib.addBook(new Book(id, title, author, category, price), qty);
        }
        return lib;
    }

    static void runBuiltInTests() {
        List<String> out = new ArrayList<>();

        // Sample Case 1
        LibrarySystem s1 = build(
                new int[][]{{1,672,4},{2,59,29},{3,563,29}},
                new String[][]{{"Title-1","Author-1","Category-3"},{"Title-2","Author-2","Category-4"},{"Title-3","Author-1","Category-3"}}
        );
        Assert.equals("S1 total", s1.calculateTotal(), 20726, out);
        Map<String,Integer> s1cat = s1.categoryTotalPrice();
        Assert.equals("S1 cat Category-3", s1cat.get("Category-3"), 19015, out);
        Assert.equals("S1 cat Category-4", s1cat.get("Category-4"), 1711, out);
        // author counts
        Map<String,Integer> m1 = s1.categoryAndAuthorWithCount().stream()
                .collect(Collectors.toMap(c -> c.category + "|" + c.author, c -> c.count));
        Assert.equals("S1 Category-3 Author-1", m1.get("Category-3|Author-1"), 33, out);
        Assert.equals("S1 Category-4 Author-2", m1.get("Category-4|Author-2"), 29, out);

        // Sample Case 2
        LibrarySystem s2 = build(
                new int[][]{{1,206,14},{2,527,23},{3,734,6},{4,58,29}},
                new String[][]{{"Title-1","Author-1","Category-4"},{"Title-2","Author-2","Category-2"},{"Title-3","Author-2","Category-2"},{"Title-4","Author-1","Category-1"}}
        );
        Assert.equals("S2 total", s2.calculateTotal(), 21091, out);
        Map<String,Integer> s2cat = s2.categoryTotalPrice();
        Assert.equals("S2 cat Category-1", s2cat.get("Category-1"), 1682, out);
        Assert.equals("S2 cat Category-2", s2cat.get("Category-2"), 16525, out);
        Assert.equals("S2 cat Category-4", s2cat.get("Category-4"), 2884, out);
        Map<String,Integer> m2 = s2.categoryAndAuthorWithCount().stream()
                .collect(Collectors.toMap(c -> c.category + "|" + c.author, c -> c.count));
        Assert.equals("S2 Category-1 Author-1", m2.get("Category-1|Author-1"), 29, out);
        Assert.equals("S2 Category-2 Author-2", m2.get("Category-2|Author-2"), 29, out);
        Assert.equals("S2 Category-4 Author-1", m2.get("Category-4|Author-1"), 14, out);

        // Edge: add & remove leading to deletion
        LibrarySystem e = build(
                new int[][]{{10,100,3}},
                new String[][]{{"Only","A","C"}}
        );
        e.removeBook(new Book(10,"Only","A","C",100), 2);
        Assert.equals("Edge qty after remove 2", e.booksInfo().get(0).quantity, 1, out);
        e.removeBook(new Book(10,"Only","A","C",100), 1);
        Assert.trueCond("Edge removed when qty <= 0", e.booksInfo().isEmpty(), out);

        // Large data (100k entries mixed); verify totals match math
        Random rnd = new Random(1);
        LibrarySystem big = new LibrarySystem();
        long expected = 0;
        int N = 100_000;
        for (int i = 0; i < N; i++) {
            int id = i;
            int price = 10 + rnd.nextInt(990);
            int qty = 1 + rnd.nextInt(5);
            expected += (long) price * qty;
            String title = "T" + (i%500);
            String author = "A" + (i%50);
            String category = "C" + (i%20);
            big.addBook(new Book(id,title,author,category,price), qty);
        }
        Assert.trueCond("Large data total fits in int range check", expected <= Integer.MAX_VALUE, out);
        Assert.equals("Large data total", big.calculateTotal(), (int) expected, out);

        // Print results
        System.out.println("=== TEST RESULTS ===");
        out.forEach(System.out::println);
        long pass = out.stream().filter(s -> s.startsWith("PASS")).count();
        long fail = out.size() - pass;
        System.out.println("Summary: " + pass + " passed, " + fail + " failed");
        System.out.println();
    }

    /* -------------------- Main -------------------- */
    public static void main(String[] args) {
        List<String> out = new ArrayList<>();

        // --- SAMPLE CASE 1 ---
        LibrarySystem s1 = build(
                new int[][]{{1,672,4},{2,59,29},{3,563,29}},
                new String[][]{
                        {"Title-1","Author-1","Category-3"},
                        {"Title-2","Author-2","Category-4"},
                        {"Title-3","Author-1","Category-3"}
                }
        );
        Assert.equals("Case1 Total", s1.calculateTotal(), 20726, out);
        Assert.equals("Case1 Cat-3", s1.categoryTotalPrice().get("Category-3"), 19015, out);
        Assert.equals("Case1 Cat-4", s1.categoryTotalPrice().get("Category-4"), 1711, out);
        Map<String,Integer> m1 = s1.categoryAndAuthorWithCount().stream()
                .collect(Collectors.toMap(c -> c.category + "|" + c.author, c -> c.count));
        Assert.equals("Case1 Cat3-Auth1", m1.get("Category-3|Author-1"), 33, out);
        Assert.equals("Case1 Cat4-Auth2", m1.get("Category-4|Author-2"), 29, out);

        // --- SAMPLE CASE 2 ---
        LibrarySystem s2 = build(
                new int[][]{{1,206,14},{2,527,23},{3,734,6},{4,58,29}},
                new String[][]{
                        {"Title-1","Author-1","Category-4"},
                        {"Title-2","Author-2","Category-2"},
                        {"Title-3","Author-2","Category-2"},
                        {"Title-4","Author-1","Category-1"}
                }
        );
        Assert.equals("Case2 Total", s2.calculateTotal(), 21091, out);
        Assert.equals("Case2 Cat-1", s2.categoryTotalPrice().get("Category-1"), 1682, out);
        Assert.equals("Case2 Cat-2", s2.categoryTotalPrice().get("Category-2"), 16525, out);
        Assert.equals("Case2 Cat-4", s2.categoryTotalPrice().get("Category-4"), 2884, out);
        Map<String,Integer> m2 = s2.categoryAndAuthorWithCount().stream()
                .collect(Collectors.toMap(c -> c.category + "|" + c.author, c -> c.count));
        Assert.equals("Case2 Cat1-Auth1", m2.get("Category-1|Author-1"), 29, out);
        Assert.equals("Case2 Cat2-Auth2", m2.get("Category-2|Author-2"), 29, out);
        Assert.equals("Case2 Cat4-Auth1", m2.get("Category-4|Author-1"), 14, out);

        // --- PRINT PASS/FAIL SUMMARY ---
        System.out.println("=== TEST RESULTS ===");
        out.forEach(System.out::println);
        long pass = out.stream().filter(s -> s.startsWith("PASS")).count();
        long fail = out.size() - pass;
        System.out.println("Summary: " + pass + " passed, " + fail + " failed");
        System.out.println();

        // --- PRINT FULL OUTPUTS FOR VISUAL CHECK ---
        System.out.println("=== SAMPLE CASE 1 OUTPUT ===");
        printReport(s1);
        System.out.println();
        System.out.println("=== SAMPLE CASE 2 OUTPUT ===");
        printReport(s2);
    }
}
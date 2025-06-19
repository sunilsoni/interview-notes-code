package com.interview.notes.code.year.y2025.June.cts.test2;

import java.io.*;
import java.util.*;
import java.util.stream.*;

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

class BooksInfo {
    String title;
    int quantity;
    int price;
    BooksInfo(String title, int quantity, int price) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BooksInfo)) return false;
        BooksInfo b = (BooksInfo)o;
        return quantity==b.quantity && price==b.price && Objects.equals(title,b.title);
    }
    @Override public int hashCode() {
        return Objects.hash(title, quantity, price);
    }
    @Override public String toString() {
        return String.format("[Title=%s,Q=%d,P=%d]", title, quantity, price);
    }
}

class CategoryAuthorWithCount {
    String category;
    String author;
    int count;
    CategoryAuthorWithCount(String category, String author, int count) {
        this.category = category;
        this.author = author;
        this.count = count;
    }
    @Override public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof CategoryAuthorWithCount)) return false;
        CategoryAuthorWithCount c = (CategoryAuthorWithCount)o;
        return count==c.count
            && Objects.equals(category,c.category)
            && Objects.equals(author,c.author);
    }
    @Override public int hashCode() {
        return Objects.hash(category, author, count);
    }
    @Override public String toString() {
        return String.format("[Cat=%s,Auth=%s,C=%d]", category, author, count);
    }
}

class Book implements IBook {
    private int id;
    private String title, author, category;
    private int price;
    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public void setTitle(String t) { title = t; }
    public String getTitle() { return title; }
    public void setAuthor(String a) { author = a; }
    public String getAuthor() { return author; }
    public void setCategory(String c) { category = c; }
    public String getCategory() { return category; }
    public void setPrice(int p) { price = p; }
    public int getPrice() { return price; }
    @Override public boolean equals(Object o) {
        return o instanceof IBook && id==((IBook)o).getId();
    }
    @Override public int hashCode() {
        return Integer.hashCode(id);
    }
}

class LibrarySystem implements ILibrarySystem {
    private Map<IBook,Integer> books = new HashMap<>();

    public void addBook(IBook book, int quantity) {
        books.put(book, books.getOrDefault(book,0)+quantity);
    }
    public void removeBook(IBook book, int quantity) {
        if (!books.containsKey(book)) return;
        int q = books.get(book) - quantity;
        if (q<=0) books.remove(book);
        else books.put(book,q);
    }
    public int calculateTotal() {
        return books.entrySet().stream()
            .mapToInt(e -> e.getKey().getPrice()*e.getValue())
            .sum();
    }
    public Map<String,Integer> categoryTotalPrice() {
        return books.entrySet().stream()
            .collect(Collectors.groupingBy(
                e -> e.getKey().getCategory(),
                TreeMap::new,
                Collectors.summingInt(e -> e.getKey().getPrice()*e.getValue())
            ));
    }
    public List<BooksInfo> booksInfo() {
        return books.entrySet().stream()
            .map(e -> new BooksInfo(
                e.getKey().getTitle(), e.getValue(), e.getKey().getPrice()
            ))
            .sorted(Comparator
                .comparing((BooksInfo bi)->bi.title)
                .thenComparingInt(bi->bi.quantity)
            )
            .collect(Collectors.toList());
    }
    public List<CategoryAuthorWithCount> categoryAndAuthorWithCount() {
        // group by category then author using ordered maps
        Map<String,Map<String,Integer>> m = new TreeMap<>();
        for (Map.Entry<IBook,Integer> e: books.entrySet()) {
            String cat = e.getKey().getCategory();
            String auth = e.getKey().getAuthor();
            m.computeIfAbsent(cat, k-> new TreeMap<>())
             .merge(auth, e.getValue(), Integer::sum);
        }
        List<CategoryAuthorWithCount> list = new ArrayList<>();
        m.forEach((cat, authMap) ->
            authMap.forEach((auth, cnt) ->
                list.add(new CategoryAuthorWithCount(cat, auth, cnt))
            )
        );
        return list;
    }
}

public class Main {
    public static void main(String[] args) {
        int passed=0, total=0;

        // Sample0
        {
            ILibrarySystem lib = new LibrarySystem();
            addSample0(lib);

            total++;
            if (lib.calculateTotal()==21091) pass("Sample0 calculateTotal"); else fail("Sample0 calculateTotal",21091,lib.calculateTotal());

            total++;
            Map<String,Integer> expCat = new TreeMap<>();
            expCat.put("Category-1", 2884);
            expCat.put("Category-2", 16525);
            expCat.put("Category-4", 1682);
            if (lib.categoryTotalPrice().equals(expCat)) pass("Sample0 categoryTotalPrice");
            else fail("Sample0 categoryTotalPrice", expCat, lib.categoryTotalPrice());

            total++;
            List<BooksInfo> expInfo = Arrays.asList(
                new BooksInfo("Title-1",14,206),
                new BooksInfo("Title-2",23,527),
                new BooksInfo("Title-3",6,734),
                new BooksInfo("Title-4",29,58)
            );
            if (lib.booksInfo().equals(expInfo)) pass("Sample0 booksInfo");
            else fail("Sample0 booksInfo", expInfo, lib.booksInfo());

            total++;
            List<CategoryAuthorWithCount> expCA = Arrays.asList(
                new CategoryAuthorWithCount("Category-1","Author-1",29),
                new CategoryAuthorWithCount("Category-2","Author-2",29),
                new CategoryAuthorWithCount("Category-4","Author-1",14)
            );
            if (lib.categoryAndAuthorWithCount().equals(expCA)) pass("Sample0 categoryAndAuthorWithCount");
            else fail("Sample0 categoryAndAuthorWithCount", expCA, lib.categoryAndAuthorWithCount());
        }

        // Sample1
        {
            ILibrarySystem lib = new LibrarySystem();
            addSample1(lib);
            total++;
            if (lib.calculateTotal()==20726) pass("Sample1 calculateTotal"); else fail("Sample1 calculateTotal",20726,lib.calculateTotal());
        }

        System.out.printf("Tests passed: %d/%d%n", passed, total);
    }

    private static void addSample0(ILibrarySystem lib) {
        lib.addBook(makeBook(1,"Title-1","Author-1","Category-1",206),14);
        lib.addBook(makeBook(2,"Title-2","Author-2","Category-2",527),23);
        lib.addBook(makeBook(3,"Title-3","Author-2","Category-2",734),6);
        lib.addBook(makeBook(4,"Title-4","Author-1","Category-4",58),29);
    }
    private static void addSample1(ILibrarySystem lib) {
        lib.addBook(makeBook(1,"Title-1","Author-1","Category-3",672),4);
        lib.addBook(makeBook(2,"Title-2","Author-2","Category-4",59),29);
        lib.addBook(makeBook(3,"Title-3","Author-1","Category-3",563),29);
    }
    private static Book makeBook(int id, String t, String a, String c, int p) {
        Book b = new Book(); b.setId(id); b.setTitle(t);
        b.setAuthor(a); b.setCategory(c); b.setPrice(p);
        return b;
    }

    private static void pass(String name) {
        System.out.printf("[PASS] %s%n", name);
        // increment passed inside call site
    }
    private static <T> void fail(String name, T exp, T act) {
        System.out.printf("[FAIL] %s – expected: %s, actual: %s%n", name, exp, act);
    }
}
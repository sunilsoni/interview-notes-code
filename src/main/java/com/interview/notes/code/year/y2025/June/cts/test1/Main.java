package com.interview.notes.code.year.y2025.June.cts.test1;

import java.io.*;
import java.util.*;

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
}

class Book implements IBook {
    private int id;
    private String title;
    private String author;
    private String category;
    private int price;

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }
    public void setAuthor(String author) { this.author = author; }
    public String getAuthor() { return author; }
    public void setCategory(String category) { this.category = category; }
    public String getCategory() { return category; }
    public void setPrice(int price) { this.price = price; }
    public int getPrice() { return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IBook)) return false;
        return id == ((IBook)o).getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

class LibrarySystem implements ILibrarySystem {
    private Map<IBook, Integer> _books = new HashMap<>();

    public void addBook(IBook book, int quantity) {
        _books.put(book, _books.getOrDefault(book, 0) + quantity);
    }

    public void removeBook(IBook book, int quantity) {
        if (!_books.containsKey(book)) return;
        int q = _books.get(book) - quantity;
        if (q <= 0) _books.remove(book);
        else _books.put(book, q);
    }

    public int calculateTotal() {
        int total = 0;
        for (Map.Entry<IBook,Integer> e : _books.entrySet()) {
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }

    public Map<String, Integer> categoryTotalPrice() {
        Map<String,Integer> m = new TreeMap<>();
        for (Map.Entry<IBook,Integer> e : _books.entrySet()) {
            String cat = e.getKey().getCategory();
            int v = e.getKey().getPrice() * e.getValue();
            m.put(cat, m.getOrDefault(cat,0) + v);
        }
        return m;
    }

    public List<BooksInfo> booksInfo() {
        List<BooksInfo> list = new ArrayList<>();
        for (Map.Entry<IBook,Integer> e : _books.entrySet()) {
            IBook b = e.getKey();
            list.add(new BooksInfo(b.getTitle(), e.getValue(), b.getPrice()));
        }
        list.sort(Comparator
            .comparing((BooksInfo bi) -> bi.title)
            .thenComparingInt(bi -> bi.quantity));
        return list;
    }

    public List<CategoryAuthorWithCount> categoryAndAuthorWithCount() {
        Map<String,Map<String,Integer>> m = new TreeMap<>();
        for (Map.Entry<IBook,Integer> e : _books.entrySet()) {
            String cat = e.getKey().getCategory();
            String auth = e.getKey().getAuthor();
            int q = e.getValue();
            m.computeIfAbsent(cat, k->new TreeMap<>())
             .put(auth, m.get(cat).getOrDefault(auth,0) + q);
        }
        List<CategoryAuthorWithCount> list = new ArrayList<>();
        for (Map.Entry<String,Map<String,Integer>> e : m.entrySet()) {
            for (Map.Entry<String,Integer> e2 : e.getValue().entrySet()) {
                list.add(new CategoryAuthorWithCount(e.getKey(), e2.getKey(), e2.getValue()));
            }
        }
        return list;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ILibrarySystem lib = new LibrarySystem();
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            String title = sc.next();
            String author = sc.next();
            String category = sc.next();
            int price = sc.nextInt();
            int qty = sc.nextInt();
            Book b = new Book();
            b.setId(id);
            b.setTitle(title);
            b.setAuthor(author);
            b.setCategory(category);
            b.setPrice(price);
            lib.addBook(b, qty);
        }
        System.out.println("Book Info:");
        for (BooksInfo bi : lib.booksInfo()) {
            System.out.printf("Book Name:%s, Quantity:%d, Price:%d%n",
                bi.title, bi.quantity, bi.price);
        }
        System.out.println("Category Total Price:");
        for (Map.Entry<String,Integer> e : lib.categoryTotalPrice().entrySet()) {
            System.out.printf("Category:%s, Total Price:%d%n",
                e.getKey(), e.getValue());
        }
        System.out.println("Category And Author With Count:");
        for (CategoryAuthorWithCount ca : lib.categoryAndAuthorWithCount()) {
            System.out.printf("Category:%s, Author:%s, Count:%d%n",
                ca.category, ca.author, ca.count);
        }
        System.out.printf("Total Price:%d%n", lib.calculateTotal());
    }
}
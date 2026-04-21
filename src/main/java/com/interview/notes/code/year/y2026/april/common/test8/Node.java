package com.interview.notes.code.year.y2026.april.common.test8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

interface IProduct {
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    double getPrice();
    void setPrice(double price);
}

interface ICategory {
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    List<IProduct> getProducts();
    void setProducts(List<IProduct> products);
    void addProduct(IProduct product);
}

interface ICompany {
    String getTopCategoryNameByProductCount();
    List<IProduct> getProductsBelongsToMultipleCategory();
    Node getTopCategoryBySumOfProductPrices();
    List<Node> getCategoriesWithSumOfTheProductPrices();
}

class Node {
    String categoryName;
    double totalValue;
    Node(String categoryName, double totalValue) {
        this.categoryName = categoryName;
        this.totalValue = totalValue;
    }
}

class Product implements IProduct {
    private int id;
    private String name;
    private double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

class Category implements ICategory {
    private int id;
    private String name;
    private List<IProduct> products = new ArrayList<>();

    Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<IProduct> getProducts() { return products; }
    public void setProducts(List<IProduct> products) { this.products = products; }
    public void addProduct(IProduct product) { products.add(product); }
}

class Company implements ICompany {
    private final int id;
    private final String name;
    private final List<ICategory> categories = new ArrayList<>();

    Company(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addCategory(ICategory category) { categories.add(category); }

    public String getTopCategoryNameByProductCount() {
        return categories.stream()
                .max(Comparator.comparingInt(c -> c.getProducts().size()))
                .map(ICategory::getName).orElse("");
    }

    public List<IProduct> getProductsBelongsToMultipleCategory() {
        Map<Integer, Long> counts = categories.stream()
                .flatMap(c -> c.getProducts().stream())
                .collect(Collectors.groupingBy(IProduct::getId, Collectors.counting()));
        
        return categories.stream()
                .flatMap(c -> c.getProducts().stream())
                .filter(p -> counts.get(p.getId()) > 1)
                .distinct()
                .toList();
    }

    public Node getTopCategoryBySumOfProductPrices() {
        return getCategoriesWithSumOfTheProductPrices().stream()
                .max(Comparator.comparingDouble(n -> n.totalValue)).orElse(null);
    }

    public List<Node> getCategoriesWithSumOfTheProductPrices() {
        return categories.stream()
                .map(c -> new Node(c.getName(), c.getProducts().stream().mapToDouble(IProduct::getPrice).sum()))
                .toList();
    }
}
package com.interview.notes.code.year.y2025.july.hackerank.test2;

/**
 * Here's a combined and clear textual description of the problem statement and requirements extracted from your screenshots:
 * <p>
 * ---
 * <p>
 * ## 🛒 **E-commerce Category and Product Analysis System**
 * <p>
 * ### ✅ **Objective:**
 * <p>
 * Build a system to generate the following reports for an e-commerce company:
 * <p>
 * 1. The **category with the most products**.
 * 2. **Sum of product prices** within each category.
 * 3. **Products that belong to multiple categories**.
 * 4. **Category with the highest total product value**.
 * <p>
 * ---
 * <p>
 * ### 🔧 **Required Classes and Interfaces**
 * <p>
 * #### 1. `ICategory` Interface
 * <p>
 * Represents a product category.
 * <p>
 * ```java
 * interface ICategory {
 * int getId();
 * void setId(int id);
 * String getName();
 * void setName(String name);
 * List<IProduct> getProducts();
 * void setProducts(List<IProduct> products);
 * void addProduct(IProduct product);
 * }
 * ```
 * <p>
 * #### 2. `IProduct` Interface
 * <p>
 * Represents a product.
 * <p>
 * ```java
 * interface IProduct {
 * int getId();
 * void setId(int id);
 * String getName();
 * void setName(String name);
 * double getPrice();
 * void setPrice(double price);
 * }
 * ```
 * <p>
 * #### 3. `ICompany` Interface
 * <p>
 * Represents the company managing categories and products.
 * <p>
 * ```java
 * interface ICompany {
 * String getTopCategoryNameByProductCount();
 * List<IProduct> getProductsBelongsToMultipleCategory();
 * Node getTopCategoryBySumOfProductPrices();
 * List<Node> getCategoriesWithSumOfTheProductPrices();
 * }
 * ```
 * <p>
 * ---
 * <p>
 * ### 📦 **Helper Class**
 * <p>
 * ```java
 * class Node {
 * String categoryName;
 * double totalValue;
 * <p>
 * Node(String categoryName, double totalValue) {
 * this.categoryName = categoryName;
 * this.totalValue = totalValue;
 * }
 * }
 * ```
 * <p>
 * ---
 * <p>
 * ### 🧱 **You Must Implement the Following Classes**
 * <p>
 * `Category implements ICategory`
 * `Product implements IProduct`
 * `Company implements ICompany`
 * <p>
 * ---
 * <p>
 * ### 🔢 **Input Format**
 * <p>
 * 1. **n** – number of products
 * 2. Next `n` lines: product info → `ProductId ProductName ProductPrice`
 * 3. **m** – number of categories
 * 4. Next `m` lines: category info → `CategoryId CategoryName`
 * 5. **k** – number of category-product assignments
 * 6. Next `k` lines: relations → `CategoryId ProductId`
 * <p>
 * ---
 * <p>
 * ### 📤 **Output Requirements**
 * <p>
 * The system must output the following:
 * <p>
 * 1. **Top category**: Name of category with most products
 * 2. **Common products**: List of products that appear in more than one category
 * 3. **Most valuable category**: Category with highest sum of product prices
 * 4. **Each category's value** in the format:
 * <p>
 * ```
 * CategoryName TotalPrice
 * ```
 * <p>
 * ---
 * <p>
 * ### 🧪 **Sample Output Example**
 * <p>
 * ```
 * Top category: Category2
 * Common products:
 * Product2
 * Product3
 * Product4
 * Most valuable category: Category2 80.0
 * Category1 55.0
 * Category2 80.0
 * ```
 * <p>
 * ---
 * <p>
 * Let me know if you’d like the full Java implementation of the `Category`, `Product`, and `Company` classes.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

// Interfaces
interface ICategory {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    List<IProduct> getProducts();

    void setProducts(List<IProduct> products);

    void addProduct(IProduct product);
}

interface IProduct {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    double getPrice();

    void setPrice(double price);
}

interface ICompany {
    String getTopCategoryNameByProductCount();

    List<IProduct> getProductsBelongsToMultipleCategory();

    Node getTopCategoryBySumOfProductPrices();

    List<Node> getCategoriesWithSumOfTheProductPrices();
}

// Node class representing category name and total value
class Node {
    String categoryName;
    double totalValue;

    Node(String categoryName, double totalValue) {
        this.categoryName = categoryName;
        this.totalValue = totalValue;
    }
}

// Implementations
class Category implements ICategory {
    private int id;
    private String name;
    private List<IProduct> products = new ArrayList<>();

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<IProduct> getProducts() {
        return products;
    }

    @Override
    public void setProducts(List<IProduct> products) {
        this.products = products;
    }

    @Override
    public void addProduct(IProduct product) {
        this.products.add(product);
    }
}

class Product implements IProduct {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}

class Company implements ICompany {
    private final List<ICategory> categories = new ArrayList<>();

    public Company(int id, String name) {
        // id and name not used here
    }

    public void addCategory(ICategory category) {
        this.categories.add(category);
    }

    @Override
    public String getTopCategoryNameByProductCount() {
        return categories.stream()
                .max(Comparator.comparingInt(c -> c.getProducts().size()))
                .map(ICategory::getName)
                .orElse("");
    }

    @Override
    public List<IProduct> getProductsBelongsToMultipleCategory() {
        Map<IProduct, Long> countMap = new LinkedHashMap<>();
        for (ICategory cat : categories) {
            for (IProduct prod : cat.getProducts()) {
                countMap.merge(prod, 1L, Long::sum);
            }
        }
        return countMap.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Node getTopCategoryBySumOfProductPrices() {
        return categories.stream()
                .map(c -> new Node(
                        c.getName(),
                        c.getProducts().stream().mapToDouble(IProduct::getPrice).sum()))
                .max(Comparator.comparingDouble(n -> n.totalValue))
                .orElse(new Node("", 0));
    }

    @Override
    public List<Node> getCategoriesWithSumOfTheProductPrices() {
        return categories.stream()
                .map(c -> new Node(
                        c.getName(),
                        c.getProducts().stream().mapToDouble(IProduct::getPrice).sum()))
                .collect(Collectors.toList());
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        String input1 =
                "7\n" +
                        "1 Product1 35\n" +
                        "2 Product2 175\n" +
                        "3 Product3 117\n" +
                        "4 Product4 30\n" +
                        "5 Product5 198\n" +
                        "6 Product6 171\n" +
                        "7 Product7 123\n" +
                        "3\n" +
                        "1 Category1\n" +
                        "2 Category2\n" +
                        "3 Category3\n" +
                        "13\n" +
                        "1 2\n" +
                        "1 4\n" +
                        "1 5\n" +
                        "1 6\n" +
                        "2 1\n" +
                        "2 2\n" +
                        "2 3\n" +
                        "2 5\n" +
                        "2 6\n" +
                        "2 7\n" +
                        "3 1\n" +
                        "3 3\n" +
                        "3 4\n";

        String expected1 =
                "Top category:Category2\n" +
                        "Common products:\n" +
                        "Product2\n" +
                        "Product4\n" +
                        "Product5\n" +
                        "Product6\n" +
                        "Product1\n" +
                        "Product3\n" +
                        "Most valuable category:Category2 819.0\n" +
                        "Category1 574.0\n" +
                        "Category2 819.0\n" +
                        "Category3 182.0\n";

        String input2 =
                "6\n" +
                        "1 Product1 96\n" +
                        "2 Product2 15\n" +
                        "3 Product3 145\n" +
                        "4 Product4 164\n" +
                        "5 Product5 26\n" +
                        "6 Product6 195\n" +
                        "3\n" +
                        "1 Category1\n" +
                        "2 Category2\n" +
                        "3 Category3\n" +
                        "9\n" +
                        "1 1\n" +
                        "1 2\n" +
                        "1 4\n" +
                        "1 5\n" +
                        "1 6\n" +
                        "2 2\n" +
                        "3 3\n" +
                        "3 4\n" +
                        "3 5\n";

        String expected2 =
                "Top category:Category1\n" +
                        "Common products:\n" +
                        "Product2\n" +
                        "Product4\n" +
                        "Product5\n" +
                        "Most valuable category:Category1 496.0\n" +
                        "Category1 496.0\n" +
                        "Category2 15.0\n" +
                        "Category3 335.0\n";

        runAndCheck("Sample Case 1", input1, expected1);
        runAndCheck("Sample Case 0", input2, expected2);
    }

    private static void runAndCheck(String label, String input, String expected) throws Exception {
        InputStream origIn = System.in;
        PrintStream origOut = System.out;

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        solve();

        System.setIn(origIn);
        System.setOut(origOut);

        String actual = baos.toString();
        boolean pass = actual.equals(expected);
        System.out.println(label + ": " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("Expected output:\n" + expected);
            System.out.println("Actual output:\n" + actual);
        }
    }

    private static void solve() {
        Scanner sc = new Scanner(System.in);

        int numProducts = sc.nextInt();
        Map<Integer, IProduct> productMap = new HashMap<>();
        for (int i = 0; i < numProducts; i++) {
            int pid = sc.nextInt();
            String name = sc.next();
            double price = sc.nextDouble();
            productMap.put(pid, new Product(pid, name, price));
        }

        int numCategories = sc.nextInt();
        Map<Integer, Category> categoryMap = new LinkedHashMap<>();
        Company company = new Company(0, "");
        for (int i = 0; i < numCategories; i++) {
            int cid = sc.nextInt();
            String cname = sc.next();
            Category cat = new Category(cid, cname);
            categoryMap.put(cid, cat);
            company.addCategory(cat);
        }

        int numMappings = sc.nextInt();
        for (int i = 0; i < numMappings; i++) {
            int cid = sc.nextInt();
            int pid = sc.nextInt();
            Category cat = categoryMap.get(cid);
            IProduct prod = productMap.get(pid);
            if (cat != null && prod != null) {
                cat.addProduct(prod);
            }
        }

        System.out.println("Top category:" + company.getTopCategoryNameByProductCount());
        System.out.println("Common products:");
        company.getProductsBelongsToMultipleCategory()
                .forEach(p -> System.out.println(p.getName()));

        Node topVal = company.getTopCategoryBySumOfProductPrices();
        System.out.println("Most valuable category:" + topVal.categoryName + " " + topVal.totalValue);
        company.getCategoriesWithSumOfTheProductPrices()
                .forEach(node -> System.out.println(node.categoryName + " " + node.totalValue));
        sc.close();
    }
}
package com.interview.notes.code.year.y2025.july.hackerank.test1;

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
    private List<IProduct> products;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
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
        // Maintain insertion order
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
                .max(Comparator.comparingDouble(node -> node.totalValue))
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of product entries
        int numProducts = sc.nextInt();
        Map<Integer, IProduct> productMap = new HashMap<>();
        for (int i = 0; i < numProducts; i++) {
            int pid = sc.nextInt();
            String pname = sc.next();
            double price = sc.nextDouble();
            productMap.put(pid, new Product(pid, pname, price));
        }

        // Number of category entries
        int numCategories = sc.nextInt();
        Map<Integer, Category> categoryMap = new LinkedHashMap<>();
        Company company = new Company();
        for (int i = 0; i < numCategories; i++) {
            int cid = sc.nextInt();
            String cname = sc.next();
            Category cat = new Category(cid, cname);
            categoryMap.put(cid, cat);
            company.addCategory(cat);
        }

        // Number of category-product mappings
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

        // 1) Category with most products
        System.out.println("Top category:"
                + company.getTopCategoryNameByProductCount());

        // 2) Products in multiple categories
        System.out.println("Common products:");
        company.getProductsBelongsToMultipleCategory()
                .forEach(p -> System.out.println(p.getName()));

        // 3) Category with highest total price
        Node topValue = company.getTopCategoryBySumOfProductPrices();
        System.out.println("Most valuable category:"
                + topValue.categoryName + " " + topValue.totalValue);

        // 4) Sum of prices in each category
        company.getCategoriesWithSumOfTheProductPrices()
                .forEach(node ->
                        System.out.println(node.categoryName + " " + node.totalValue)
                );

        sc.close();
    }
}
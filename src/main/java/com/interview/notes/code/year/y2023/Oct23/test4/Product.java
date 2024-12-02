package com.interview.notes.code.year.y2023.Oct23.test4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Product {
    private int price;
    private int quantity;
    private String name;

    // Constructor
    public Product(int price, int quantity, String name) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }

    public static void main(String[] args) {
        List<Product> list = Arrays.asList(
                new Product(100, 5, "Apple"),
                new Product(150, 3, "Banana"),
                new Product(200, 7, "Cherry")
        );

        List<Product> sortedList = list.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        System.out.println(sortedList);

        List<Product> distinctProducts = list.stream()
                .distinct()
                .collect(Collectors.toList());

        Map<Integer, List<Product>> map = list.stream()
                .collect(Collectors.groupingBy(Product::getPrice));

    }

    // Getters
    public int getPrice() {
        return price;
    }

    // Setters
    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" + "price=" + price + ", quantity=" + quantity + ", name='" + name + '\'' + '}';
    }
}

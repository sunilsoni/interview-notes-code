package com.interview.notes.code.months.may24.test10;

import java.util.Objects;

public class Product {
    private String productId;
    private String name;
    private double price;
    private int quantity;

    public Product(String productId, String name, double price) {
        this.productId = Objects.requireNonNull(productId, "Product ID cannot be null");
        this.name = Objects.requireNonNull(name, "Product name cannot be null");
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
        this.quantity = 0;  // New products start with no stock
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("Product ID: %s, Name: %s, Price: %.2f, Quantity: %d", productId, name, price, quantity);
    }
}

package com.interview.notes.code.year.y2024.may24.test10;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<String, Product> inventory;

    public Inventory() {
        inventory = new ConcurrentHashMap<>();
    }

    public boolean addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product already exists.");
            return false;
        } else {
            inventory.put(product.getProductId(), product);
            return true;
        }
    }

    public boolean restockProduct(String productId, int quantity) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
            return true;
        } else {
            System.out.println("Product does not exist.");
            return false;
        }
    }

    public boolean sellProduct(String productId, int quantity) {
        Product product = inventory.get(productId);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            return true;
        } else {
            System.out.println("Product does not exist or not enough stock.");
            return false;
        }
    }

    public void displayInventory() {
        inventory.values().forEach(System.out::println);
    }
}

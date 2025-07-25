package com.interview.notes.code.year.y2025.july.amazon.test9;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupermarketStorage {
    // Store product batches with their details
    private Map<String, List<Batch>> productBatches = new HashMap<>();

    // Track first order time for each product
    private Map<String, LocalDateTime> firstOrderTime = new HashMap<>();

    public static void main(String[] args) {
        SupermarketStorage storage = new SupermarketStorage();

        // Test 1: Basic purchase and remove
        storage.purchase("apple", LocalDate.now().plusDays(5), 30);
        boolean removeResult = storage.remove("apple", LocalDate.now().plusDays(5), 15);
        System.out.println("Test 1: " + (removeResult ? "PASS" : "FAIL"));

        // Test 2: Reorder check
        // Add delay between purchases to ensure different timestamps
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        storage.purchase("banana", LocalDate.now().plusDays(10), 15);
        String reorderProduct = storage.reorder();
        System.out.println("Test 2: " + ("banana".equals(reorderProduct) ? "PASS" : "FAIL"));

        // Test 3: Large data test
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        // Add many products with sufficient stock
        for (int i = 0; i < 1000; i++) {
            storage.purchase("product" + i, LocalDate.now().plusDays(i), 25);
        }

        // Add low stock product last
        storage.purchase("lowStock", LocalDate.now(), 10);
        reorderProduct = storage.reorder();
        System.out.println("Test 3: " + ("banana".equals(reorderProduct) ? "PASS" : "FAIL"));

        // Additional test to verify quantities
        System.out.println("Banana quantity: " + storage.productBatches.get("banana").stream()
                .mapToInt(b -> b.quantity).sum());
        System.out.println("LowStock quantity: " + storage.productBatches.get("lowStock").stream()
                .mapToInt(b -> b.quantity).sum());
    }

    public void purchase(String productId, LocalDate expirationDate, int quantity) {
        // Create new batch
        Batch newBatch = new Batch(productId, expirationDate, quantity);

        // Record first order time for new products
        firstOrderTime.putIfAbsent(productId, newBatch.orderTime);

        // Add batch to product list
        productBatches.computeIfAbsent(productId, k -> new ArrayList<>()).add(newBatch);
    }

    public boolean remove(String productId, LocalDate expirationDate, int quantity) {
        List<Batch> batches = productBatches.get(productId);
        if (batches == null) return false;

        // Find and update matching batch
        for (Batch batch : batches) {
            if (batch.expirationDate.equals(expirationDate)) {
                if (batch.quantity >= quantity) {
                    batch.quantity -= quantity;
                    return true;
                }
            }
        }
        return false;
    }

    public String reorder() {
        String urgentProduct = null;
        LocalDateTime earliestOrder = null;

        // Calculate total quantities and find products below threshold
        for (Map.Entry<String, List<Batch>> entry : productBatches.entrySet()) {
            String productId = entry.getKey();
            int totalQuantity = entry.getValue().stream()
                    .mapToInt(b -> b.quantity)
                    .sum();

            // Check if product needs reordering (quantity < 20)
            if (totalQuantity < 20) {
                LocalDateTime productFirstOrder = firstOrderTime.get(productId);

                // Update urgent product if this is first found or has earlier order time
                if (urgentProduct == null || productFirstOrder.isBefore(earliestOrder)) {
                    urgentProduct = productId;
                    earliestOrder = productFirstOrder;
                }
            }
        }
        return urgentProduct;
    }

    static class Batch {
        String productId;
        LocalDate expirationDate;
        int quantity;
        LocalDateTime orderTime;

        Batch(String productId, LocalDate expDate, int qty) {
            this.productId = productId;
            this.expirationDate = expDate;
            this.quantity = qty;
            this.orderTime = LocalDateTime.now();
        }
    }
}

package com.interview.notes.code.year.y2025.july.amazon.test10;

// Import required Java libraries

import java.time.LocalDate;
import java.util.*;

public class SupermarketStorageSystem {

    // Main inventory storage: maps product IDs to their batches
    private final Map<Integer, List<Batch>> inventory;  // Store products and their batches
    private long timestampCounter = 0;                  // Counter for generating unique timestamps
    // Initialize empty inventory
    public SupermarketStorageSystem() {
        inventory = new HashMap<>();                    // Create empty inventory map
    }

    // Test method to verify functionality
    public static void main(String[] args) {
        // Various test cases to verify system behavior
        // Each test checks different scenarios like:
        // - No products below threshold
        // - Products below threshold after removal
        // - Multiple products below threshold
        // - Large scale test with many products
    }

    // Add new products to inventory
    public void purchase(int productId, LocalDate expirationDate, int quantity) {
        // Get or create list of batches for this product
        List<Batch> batches = inventory.computeIfAbsent(
                productId, k -> new ArrayList<>()           // Create new batch list if none exists
        );
        // Add new batch with current timestamp
        batches.add(new Batch(expirationDate, quantity, timestampCounter++));
    }

    // Remove products from a specific batch
    public void remove(int productId, LocalDate expirationDate, int quantity) {
        List<Batch> batches = inventory.get(productId); // Get batches for this product
        if (batches == null) return;                    // If product doesn't exist, do nothing

        // Look through batches to find matching expiration date
        for (Iterator<Batch> it = batches.iterator(); it.hasNext(); ) {
            Batch b = it.next();
            if (b.expirationDate.equals(expirationDate)) {
                int newQty = b.getQuantity() - quantity;  // Calculate remaining quantity
                if (newQty > 0) {
                    b.setQuantity(newQty);                // Update quantity if some remain
                } else {
                    it.remove();                          // Remove batch if empty
                }
                break;                                    // Exit loop after updating
            }
        }

        // Remove product if no batches left
        if (batches.isEmpty()) {
            inventory.remove(productId);
        }
    }

    // Find product that needs reordering (below 20 items and oldest)
    public int reorder() {
        return inventory.entrySet().stream()              // Process all products
                // Filter products with total quantity < 20
                .filter(entry -> entry.getValue().stream()
                        .mapToInt(Batch::getQuantity)
                        .sum() < 20
                )
                // Find product with oldest batch
                .min(Comparator.comparing(entry ->
                        entry.getValue().stream()
                                .mapToLong(Batch::getTimestamp)
                                .min()
                                .orElse(Long.MAX_VALUE)
                ))
                .map(Map.Entry::getKey)                       // Get product ID
                .orElse(-1);                                  // Return -1 if no product needs reorder
    }

    // Internal class to represent a batch of products with expiration date and quantity
    private static class Batch {
        private final LocalDate expirationDate;         // When this batch expires
        private final long timestamp;                   // When this batch was added (for ordering)
        private int quantity;                           // How many items are in this batch

        // Constructor to create a new batch
        public Batch(LocalDate expirationDate, int quantity, long timestamp) {
            this.expirationDate = expirationDate;      // Set when this batch expires
            this.quantity = quantity;                  // Set initial quantity
            this.timestamp = timestamp;                // Set when batch was created
        }

        // Get current quantity in batch
        public int getQuantity() {
            return quantity;                           // Return current quantity
        }

        // Update quantity in batch
        public void setQuantity(int quantity) {
            this.quantity = quantity;                  // Set new quantity
        }

        // Get batch creation timestamp
        public long getTimestamp() {
            return timestamp;                          // Return when batch was created
        }
    }
}
